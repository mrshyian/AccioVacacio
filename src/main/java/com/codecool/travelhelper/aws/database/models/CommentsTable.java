package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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
@NamedEntityGraph(name = "likedByUser", attributeNodes = {@NamedAttributeNode("myUserTable")})
public class CommentsTable implements Comparable<CommentsTable>{

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
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "liked_comments_by_user",
            joinColumns = @JoinColumn(name = "comment_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties({ "comments", "posts", "noteTable", "albumsFromTripsTable", "placesWantToGoTable",
            "visitedPlacesTable", "tripsTable", "fullName", "nickName", "birthday", "userEMail", "password",
            "avatar", "instagram", "facebook", "aboutMe", "role", "privateAccount"})
    private Set<MyUserTable> likedByUsers;

//---------------------------------------------------

    // comments to user
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({ "comments", "posts", "noteTable", "albumsFromTripsTable",
            "placesWantToGoTable", "visitedPlacesTable", "tripsTable",
            "fullName", "nickName", "birthday", "userEMail", "password",
            "avatar", "instagram", "facebook", "aboutMe", "role",
            "privateAccount"})
    private MyUserTable myUserTable;

//---------------------------------------------------

    @ManyToOne
    @JoinColumn(name="post_id")
    @JsonIgnoreProperties({ "likedPostByUsers", "comments", "myUserTable",
            "topic", "postText", "postImage", "postDateTime", "userName"})
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

    public Set<MyUserTable> AddUserToLikedByUser(MyUserTable userTable){
        this.likedByUsers.add(userTable);
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

    @Override
    public int compareTo(CommentsTable o) {
        if (getCommentDateTime() == null || o.getCommentDateTime() == null) {
            return 0;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return LocalDateTime.parse(getCommentDateTime(), formatter).compareTo(LocalDateTime.parse(o.getCommentDateTime(), formatter));
    }
}
