package com.codecool.travelhelper.aws.imagestore.controllers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.aws.imagestore.bucket.BucketName;
import com.codecool.travelhelper.aws.imagestore.service.S3Service;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class CommentsController {
    private final S3Service s3Service;

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    public CommentsController(S3Service s3Service) {
        this.s3Service = s3Service;
    }


    @PostMapping(
            path = "/image/upload/comment/{commentText}/{postId}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,// get data from path (our frontend)
            produces = MediaType.APPLICATION_JSON_VALUE// produces json from given value in frontend
    )
    public void uploadCommentProfileImage(@RequestParam("file") MultipartFile file, @PathVariable String postId,
                                       @PathVariable String commentText) {
        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "Comments");
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        Optional<PostTable> postTable = postRepository.findById(Long.parseLong(postId));
        String country = "Poland";
        String city = "Poznan";
        postTable.ifPresent(table -> commentRepository.save(new CommentsTable(
                commentText,
                        filename,
                        country,
                        city,
                        myUserTable,
                        table,
                        new HashSet<>()
                )
        ));
        s3Service.uploadFileToStorage( path, filename, file);
    }


    @GetMapping("/image/download/comment/profile/{commentId}")
    public byte[] downloadCommentProfileImage(@PathVariable String commentId) {
        CommentsTable commentsTable = commentRepository.findCommentsTableById(Long.valueOf(commentId));
        MyUserTable myUserTable = commentsTable.getMyUserTable();
        String filename = myUserTable.getAvatar();
        String path = String.format("%s/%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "userAlbum", myUserTable.getId());
        return s3Service.downloadFileFromStorage(path, filename);
    }


    @GetMapping("/image/download/comment/{commentId}")
    public byte[] downloadCommentImage(@PathVariable String commentId) {
        CommentsTable commentsTable = commentRepository.getCommentsTableById(Long.valueOf(commentId));
        String filename = commentsTable.getCommentImage();
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "Comments");
        return s3Service.downloadFileFromStorage(path, filename);
    }

    public void deleteCommentsImage(String filename){
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.deleteObject(BucketName.PROFILE_IMAGE.getBucketName(), filename);
        } catch(AmazonServiceException e){
            System.err.println(e.getErrorMessage());
        }
    }

}
