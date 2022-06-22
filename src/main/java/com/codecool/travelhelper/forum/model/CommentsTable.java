package com.codecool.travelhelper.forum.model;

import com.codecool.travelhelper.aws.database.tables.user_page_tables.MyUserTable;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.UUID;

@Entity(name = "CommentsTable")
@Getter
@ToString
@NoArgsConstructor
public class CommentsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String commentTex;
    private String commentImage;
    private String country;
    private String city;
    private String commentDateTime;


//---------------------------------------------------

    // liked by user to user
    @OneToMany(mappedBy = "commentsTable")
    private List<MyUserTable> likedByUsers;

//---------------------------------------------------

    // comments to user
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;
//---------------------------------------------------

    // comments to post

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostTable post;

    // post to comments
    @OneToOne(mappedBy = "comment")
    private PostTable posts;

//------------------------------------------------------

    public CommentsTable(String commentTex, String commentImage, String country, String city) {
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
