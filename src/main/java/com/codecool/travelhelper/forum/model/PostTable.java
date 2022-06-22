package com.codecool.travelhelper.forum.model;

import com.codecool.travelhelper.aws.database.tables.user_page_tables.AlbumsFromsTripsTable;
import com.codecool.travelhelper.aws.database.tables.user_page_tables.MyUserTable;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;

@Entity(name = "PostTable")
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long Id;


    // post connected to user
    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;

    @OneToOne(mappedBy = "photosFromTripsTable")
    AlbumsFromsTripsTable album;

    private String topic;
    private String postText;
    private String postImage;
    private String postDateTime;

    // comments connected to post
    @OneToMany(mappedBy = "post")
    private List<CommentsTable> comments;
//----------------------------------------------------------------------

    // connected post to comments
    @OneToOne
    @JoinColumn(name="comment_id")
    private CommentsTable comment;

    // connected user to post
    @OneToOne(mappedBy = "postTable")
    private MyUserTable userTable;
}



