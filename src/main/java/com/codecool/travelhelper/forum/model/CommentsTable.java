package com.codecool.travelhelper.forum.model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID userId;
    private String commentTex;
    private String commentImage;
    private String country;
    private String city;
    private String commentDateTime;

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostTable post;


    public CommentsTable(String commentTex, String commentImage, String country, String city) {
        this.userId = UUID.randomUUID();
        this.commentTex = commentTex;
        this.commentImage = commentImage;
        this.country = country;
        this.city = city;
        this.commentDateTime = getCurrentTime();
    }

    private String getCurrentTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(myFormatObj);
    }

}
