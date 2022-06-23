package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity(name = "PostTable")
@Getter
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

    // comments to post
    @OneToMany(mappedBy = "post")
    private List<CommentsTable> comments;

//----------------------------------------------------------------------

    // post to user
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;

    //---------------------------------------------------


    public PostTable(String topic, String postText, String postImage, String postDateTime) {
        this.topic = topic;
        this.postText = postText;
        this.postImage = postImage;
        this.postDateTime = postDateTime;
    }
}



