package com.codecool.travelhelper.aws.imagestore.controllers;

import com.codecool.travelhelper.aws.database.models.AlbumFromTripsTable;
import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PhotosFromTripsTable;
import com.codecool.travelhelper.aws.database.repositories.AlbumsFromTripsRepository;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PhotosFromTripsRepository;
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
public class AlbumsFromTripController {
    private final S3Service s3Service;

    String albumName = "";

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    AlbumsFromTripsRepository albumsFromTripsRepository;

    @Autowired
    PhotosFromTripsRepository photosFromTripsRepository;



    public AlbumsFromTripController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping(
            path = "{albumId}/{albumName}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,// get data from path (our frontend)
            produces = MediaType.APPLICATION_JSON_VALUE// produces json from given value in frontend
    )
    public void uploadAlbumImage(@RequestParam("file") MultipartFile file, @PathVariable String albumId,
                                          @PathVariable String albumName) {
//        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        this.albumName = albumName;
        System.out.println(albumName);
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), albumName);
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
//        Optional<PostTable> postTable = postRepository.findById(Long.parseLong(postId));
        AlbumFromTripsTable albumFromTripsTable = albumsFromTripsRepository.findAlbumFromTripsTableById(Long.valueOf(albumId));
        s3Service.uploadFileToStorage( path, filename, file);
        PhotosFromTripsTable photosFromTripsTable = new PhotosFromTripsTable(filename, albumFromTripsTable);
        photosFromTripsRepository.save(photosFromTripsTable);

    }


//    @GetMapping("/image/download/album/profile/{albumId}")
//    public byte[] downloadCommentProfileImage(@PathVariable String albumId) {
//        PhotosFromTripsTable photosFromTripsTable = photosFromTripsRepository.findPhotosFromTripsTableById(Long.valueOf(albumId));
//        MyUserTable myUserTable = commentsTable.getMyUserTable();
//        String filename = myUserTable.getAvatar();
//        String path = String.format("%s/%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "userAlbum", myUserTable.getId());
//        return s3Service.downloadFileFromStorage(path, filename);
//    }


    @GetMapping("/image/download/photo/{photoId}/{albumId}")
    public byte[] downloadCommentImage(@PathVariable String photoId, @PathVariable String albumId) {
        System.out.println(photoId);
//        CommentsTable commentsTable = commentRepository.getCommentsTableById(Long.valueOf(commentId));
        PhotosFromTripsTable photosFromTripsTable = photosFromTripsRepository.findPhotosFromTripsTableById(Long.valueOf(photoId));
        String filename = photosFromTripsTable.getLinkToPhoto();
        AlbumFromTripsTable albumFromTripsTable = albumsFromTripsRepository.findAlbumFromTripsTableById(Long.valueOf(albumId));
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), albumFromTripsTable.getAlbumName());
        return s3Service.downloadFileFromStorage(path, filename);
    }
}
