package com.codecool.travelhelper.aws.imagestore.bucket;

public enum BucketName {

//    PROFILE_IMAGE("travel-helper-server707-v1");
    PROFILE_IMAGE("accio-vacacio");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
