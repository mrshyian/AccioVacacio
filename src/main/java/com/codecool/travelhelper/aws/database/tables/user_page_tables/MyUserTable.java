package com.codecool.travelhelper.aws.database.tables.user_page_tables;

import com.codecool.travelhelper.forum.model.CommentsTable;
import com.codecool.travelhelper.forum.model.PostTable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity(name = "MyUserTable")
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class MyUserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;



    @OneToMany(mappedBy = "myUserTable")
    private List<CommentsTable> comments;



    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentsTable commentsTable;

    // post connected to user
    @OneToMany(mappedBy = "myUserTable")
    private List<PostTable> posts;

    // connected user to post
    @OneToOne
    @JoinColumn(name = "user_id")
    private PostTable postTable;


    private String fullName;
    private String nickName;
    private Date birthday;
    private String eMail;
    private String password;
    private String avatar;
    private String instagram;
    private String facebook;
    private String aboutMe;
    private String role;
    private boolean privateAccount;


    public MyUserTable(String fullName, String nickName, Date birthday, String eMail, String password, String avatar, String instagram, String facebook, String aboutMe, String role, boolean privateAccount) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.birthday = birthday;
        this.eMail = eMail;
        this.password = password;
        this.avatar = avatar;
        this.instagram = instagram;
        this.facebook = facebook;
        this.aboutMe = aboutMe;
        this.role = role;
        this.privateAccount = privateAccount;
    }
}
