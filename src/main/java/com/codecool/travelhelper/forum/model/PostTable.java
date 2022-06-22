package com.codecool.travelhelper.forum.model;

import com.codecool.travelhelper.aws.database.tables.user_page_tables.MyUserTable;
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

    // post to comments
    @OneToOne
    @JoinColumn(name="comment_id")
    private CommentsTable comment;
//----------------------------------------------------------------------

    // post to user
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;

    // user to post
    @OneToOne(mappedBy = "postTable")
    private MyUserTable userTable;

    //---------------------------------------------------


    public PostTable(String topic, String postText, String postImage, String postDateTime, List<CommentsTable> comments, CommentsTable comment, MyUserTable myUserTable, MyUserTable userTable) {
        this.topic = topic;
        this.postText = postText;
        this.postImage = postImage;
        this.postDateTime = postDateTime;
        this.comments = comments;
        this.comment = comment;
        this.myUserTable = myUserTable;
        this.userTable = userTable;
    }
}



