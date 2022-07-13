package com.codecool.travelhelper.aws.imagestore.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin("*")
public class UserProfileController {

    private final UserProfileService userProfileService;

    @Autowired
    public UserProfileController(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @GetMapping
    public List<UserProfile> getUserProfiles() {
        return userProfileService.getUserProfiles();
    }

    @PostMapping(
            path = "/api/v1/user-profile/{userProfileId}/{albumName}/image/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,// get data from path (our frontend)
            produces = MediaType.APPLICATION_JSON_VALUE// produces json from given value in frontend
    )
    public void uploadUserProfileImage(@PathVariable("userProfileId") Long userProfileId,
                                       @RequestParam("file") MultipartFile file, @PathVariable String albumName) {
        userProfileService.uploadUserProfileImage(userProfileId, albumName, file);
    }

    @GetMapping("/{userProfileId}/image/download")
    public byte[] downloadUserProfileImage(@PathVariable("userProfileId") Long userProfileId) {
        return userProfileService.downloadUserProfileImage(userProfileId);
    }

}
