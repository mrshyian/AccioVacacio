package com.codecool.travelhelper.aws.imagestore.datastore;

import com.codecool.travelhelper.aws.imagestore.profile.UserProfile;
import lombok.Setter;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Setter
public class FakeUserProfileDataStore {

    private static List<UserProfile> USER_PROFILES = new ArrayList<>();

    static {
        USER_PROFILES.add(new UserProfile(16L, "Your name", null));
        }

    public List<UserProfile> getUserProfiles() {
        return USER_PROFILES;
    }

}
