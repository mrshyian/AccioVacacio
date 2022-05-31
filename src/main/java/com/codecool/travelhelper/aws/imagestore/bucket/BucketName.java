package com.codecool.travelhelper.aws.imagestore.bucket;

public enum BucketName {

    PROFILE_IMAGE("travel-helper-server707");

    private final String bucketName;

    BucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
