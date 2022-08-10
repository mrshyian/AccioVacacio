package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Entity(name = "PostTable")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostTable  implements Comparable<PostTable>{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    private String topic;
    private String postText;
    private String postImage;
    private String postDateTime;
    private String userName;

//---------------------------------------------------


    // liked by user to user
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "liked_posts_by_user",
            joinColumns = @JoinColumn(name = "post_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @JsonIgnoreProperties({ "comments", "posts", "noteTable", "albumsFromTripsTable",
            "placesWantToGoTable", "visitedPlacesTable", "tripsTable",
            "fullName", "nickName", "birthday", "userEMail", "password",
            "avatar", "instagram", "facebook", "aboutMe", "role",
            "privateAccount"})
    private Set<MyUserTable> likedPostByUsers;

//---------------------------------------------------

    // comments to post
    @JsonIgnore
    @OneToMany(mappedBy = "post", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties({ "likedByUsers", "post", "myUserTable", "commentText", "commentImage",
            "country", "city", "commentDateTime", "userName"})
    private List<CommentsTable> comments;

//----------------------------------------------------------------------

    // post to user
    @ManyToOne
    @JoinColumn(name="user_id")
    @JsonIgnoreProperties({"posts", "comments", "noteTable", "albumsFromTripsTable", "placesWantToGoTable",
            "visitedPlacesTable", "tripsTable", "fullName", "nickName", "birthday", "userEMail", "password",
            "avatar", "instagram", "facebook", "aboutMe", "role", "privateAccount"})
    private MyUserTable myUserTable;

    //---------------------------------------------------


    public PostTable(String topic, String postText, String postImage, MyUserTable myUserTable, Set<MyUserTable> likedPostByUsers) {
        this.topic = topic;
        this.postText = postText;
        this.postImage = postImage;
        this.postDateTime = getCurrentTime();
        this.myUserTable = myUserTable;
        this.likedPostByUsers = likedPostByUsers;
        this.userName = myUserTable.getFullName();
    }


    private String getCurrentTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(myFormatObj);
    }

    public Set<MyUserTable> AddUserToLikedByUser(MyUserTable myUserTable){
        this.likedPostByUsers.add(myUserTable);
        return this.getLikedPostByUsers();

    }

    @Override
    public int compareTo(PostTable o) {
        if (getPostDateTime() == null || o.getPostDateTime() == null) {
            return 0;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        return LocalDateTime.parse(getPostDateTime(), formatter).compareTo(LocalDateTime.parse(o.getPostDateTime(), formatter));
    }
}



