package com.codecool.travelhelper.aws.database.tables.user_page_tables;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
