package com.codecool.travelhelper.aws.imagestore.controllers;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.aws.imagestore.bucket.BucketName;
import com.codecool.travelhelper.aws.imagestore.service.S3Service;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.UUID;

@RestController
@CrossOrigin("*")
public class UserProfileController {

    private final S3Service s3Service;

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    UserRepository userRepository;

    public UserProfileController(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping(
            path = "/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,// get data from path (our frontend)
            produces = MediaType.APPLICATION_JSON_VALUE// produces json from given value in frontend
    )
    public void uploadUserProfileImage(@RequestParam("file") MultipartFile file) {
        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "album");
        String filename = String.format("%s-%s", file.getOriginalFilename(), UUID.randomUUID());
        myUserTable.setAvatar(filename);
        userRepository.save(myUserTable);
        s3Service.uploadFileToStorage( path, filename, file);
    }

    @GetMapping("/image/download")
    public byte[] downloadUserProfileImage() {
        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        String filename = myUserTable.getAvatar();
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "album");
        return s3Service.downloadFileFromStorage(path, filename);
    }


    @GetMapping("/image/download/user/{userId}")
    public byte[] downloadPostImage(@PathVariable String userId) {
        MyUserTable myUserTable = userRepository.findMyUserTableById(Long.valueOf(userId));
        String filename = myUserTable.getAvatar();
        String path = String.format("%s/%s", BucketName.PROFILE_IMAGE.getBucketName(), "album");
        return s3Service.downloadFileFromStorage(path, filename);
    }

    @GetMapping("/user-profile")
    public MyUserTable getUser(){
        return userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
    }
}
