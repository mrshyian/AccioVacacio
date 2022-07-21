package com.codecool.travelhelper.aws.imagestore.controllers;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
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
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class S3PostController {
    private final S3Service s3Service;

    Long thisPostId = 0L;


    @Autowired
    LoginImpl loginImpl;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    public S3PostController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping(
            path = "/image/upload/post/{topic}/{postText}",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,// get data from path (our frontend)
            produces = MediaType.APPLICATION_JSON_VALUE// produces json from given value in frontend
    )
    public void uploadPostProfileImage(@RequestParam("file") MultipartFile file, @PathVariable String topic,
                                       @PathVariable String postText) {
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "Posts");
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);
        s3Service.uploadFileToStorage( path, filename, file);
        PostTable postTable = new PostTable(
                topic,
                postText,
                filename,
                myUserTable,
                new HashSet<>());
        postRepository.save(postTable);
        thisPostId = postTable.getId();
    }

    @GetMapping("/image/download/post/profile/{postId}")
    public byte[] downloadPostProfileImage(@PathVariable String postId) {
        PostTable postTable = postRepository.findPostTableById(Long.valueOf(postId));
        MyUserTable myUserTable = postTable.getMyUserTable();
        String filename = myUserTable.getAvatar();
        String path = String.format("%s/%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "userAlbum", myUserTable.getId());
        return s3Service.downloadFileFromStorage(path, filename);
    }

    @GetMapping("/image/download/post/{postId}")
    public byte[] downloadPostImage(@PathVariable String postId) {
        PostTable postTable = postRepository.findPostTableById(Long.valueOf(postId));
        String filename = postTable.getPostImage();
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "Posts");
        return s3Service.downloadFileFromStorage(path, filename);
    }

    public void deletePostImage(String filename){
        AmazonS3 s3 = AmazonS3ClientBuilder.defaultClient();
        try {
            s3.deleteObject(BucketName.PROFILE_IMAGE.getBucketName(), filename);
        } catch(AmazonServiceException e){
            System.err.println(e.getErrorMessage());
        }
    }


}
