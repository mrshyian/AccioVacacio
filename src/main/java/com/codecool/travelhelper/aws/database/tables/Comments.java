package com.codecool.travelhelper.aws.database.tables;


import lombok.Getter;
import lombok.ToString;
import org.joda.time.DateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@ToString
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;
    private String commentTex;
    private String commentImage;
    private String country;
    private String city;
    private DateTime commentDateTime;
    private UUID likedByUser;

    public Comments() {
    }

    public Comments(UUID userId, String commentTex, String commentImage, String country, String city, DateTime commentDateTime, UUID likedByUser) {
        this.userId = userId;
        this.commentTex = commentTex;
        this.commentImage = commentImage;
        this.country = country;
        this.city = city;
        this.commentDateTime = commentDateTime;
        this.likedByUser = likedByUser;
    }
}
