package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "CommentsTable")
@Getter
@Setter
@NoArgsConstructor
public class CommentsTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    private String commentText;
    private String commentImage;
    private String country;
    private String city;
    private String commentDateTime;


//---------------------------------------------------

    // liked by user to user
//    @ManyToMany
//    @JoinTable(name = "liked_comments_by_user",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "comment_id"))
//    private Set<MyUserTable> likedByUsers = new HashSet<>();

//---------------------------------------------------

    // comments to user
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;
//---------------------------------------------------

    // comments to post
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="post_id")
    private PostTable postTable;

//------------------------------------------------------


    public CommentsTable(String commentTex, String commentImage, String country, String city, MyUserTable myUserTable, PostTable postTable) {
        this.commentText = commentTex;
        this.commentImage = commentImage;
        this.country = country;
        this.city = city;
        this.commentDateTime = getCurrentTime();
        this.myUserTable = myUserTable;
        this.postTable = postTable;
    }

    private String getCurrentTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(myFormatObj);
    }

    @Override
    public String toString() {
        return "CommentsTable{" +
                "id=" + id +
                ", commentText='" + commentText + '\'' +
                ", commentImage='" + commentImage + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", commentDateTime='" + commentDateTime + '\'' +
                '}';
    }
}
