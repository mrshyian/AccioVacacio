package com.codecool.travelhelper.forum.model;

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
@Table(name = "post_table")
public class PostTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private MyUserTable myUserTable;

    private String topic;
    private String postText;
    private String postImage;
    private String postDateTime;

    @OneToMany(mappedBy = "post")
    private List<CommentsTable> comments;

}



