package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity(name = "PostTable")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class PostTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long Id;

    private String topic;
    private String postText;
    private String postImage;
    private String postDateTime;

//---------------------------------------------------


//    // liked by user to user
//    @ManyToMany
//    @JoinTable(name = "liked_posts_by_user",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "post_id"))
//    private Set<MyUserTable> likedPostByUsers = new HashSet<>();

//---------------------------------------------------

    // comments to post
    @OneToMany(mappedBy = "post")
    private List<CommentsTable> comments;

//----------------------------------------------------------------------

    // post to user
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;

    //---------------------------------------------------


    public PostTable(String topic, String postText, String postImage, MyUserTable myUserTable ) {
        this.topic = topic;
        this.postText = postText;
        this.postImage = postImage;
        this.postDateTime = getCurrentTime();
        this.myUserTable = myUserTable;
    }

    private String getCurrentTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateTime.format(myFormatObj);
    }
}



