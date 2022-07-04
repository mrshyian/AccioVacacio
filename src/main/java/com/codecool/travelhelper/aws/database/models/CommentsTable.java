package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private String userName;


//---------------------------------------------------

    // liked by user to user
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "liked_comments_by_user",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private Set<MyUserTable> likedByUsers;

//---------------------------------------------------

    // comments to user
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;
//---------------------------------------------------

    // comments to post

    @ManyToOne
    @JoinColumn(name="post_id")
    private PostTable post;

//------------------------------------------------------


    public CommentsTable(String commentTex, String commentImage, String country, String city, MyUserTable myUserTable, PostTable postTable, Set<MyUserTable> likedCommentByUsers) {
        this.commentText = commentTex;
        this.commentImage = commentImage;
        this.country = country;
        this.city = city;
        this.commentDateTime = getCurrentTime();
        this.myUserTable = myUserTable;
        this.post = postTable;
        this.likedByUsers = likedCommentByUsers;
        this.userName = myUserTable.getFullName();
    }

    private String getCurrentTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(myFormatObj);
    }

    public Set<MyUserTable> AddUserToLikedByUser(MyUserTable myUserTable){
        this.likedByUsers.add(myUserTable);
        return this.getLikedByUsers();

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
