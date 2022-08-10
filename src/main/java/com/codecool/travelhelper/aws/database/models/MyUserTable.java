package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MyUserTable")
@Getter
@NoArgsConstructor
@Setter
public class MyUserTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String fullName;
    private String nickName;
    private String birthday;
    private String userEMail;
    private String password;
    private String avatar;
    private String instagram;
    private String facebook;
    private String aboutMe;
    private String role;
    private boolean privateAccount;

    public MyUserTable(String userEMail, String password) {
        this.userEMail = userEMail;
        this.password = password;
    }

//----------------------------------------------------------------------

    // comments to user
    @JsonIgnore
    @JsonIgnoreProperties({"myUserTable", "likedByUsers", "post", "commentText", "commentImage",
            "country", "city", "commentDateTime", "userName"})
    @OneToMany(mappedBy = "myUserTable")
    private List<CommentsTable> comments;

//---------------------------------------------------

    // post to user
    @JsonIgnore
    @JsonIgnoreProperties({"myUserTable", "likedPostByUsers", "comments" ,
            "topic", "postText", "postImage", "postDateTime", "userName"})// everything except MyUserTable
    @OneToMany(mappedBy = "myUserTable")
    private List<PostTable> posts;

//---------------------------------------------------

    //note to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToOne(mappedBy = "myUserTable")
    private NoteTable noteTable;

//---------------------------------------------------

    // albums to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AlbumFromTripsTable> albumsFromTripsTable;

//---------------------------------------------------

    // placesWantToGo to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlacesWantToGoTable> placesWantToGoTable;

//---------------------------------------------------

    // visitedPlaces to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VisitedPlaceTable> visitedPlacesTable;

//---------------------------------------------------

    // trips to user
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TripTable> tripsTable;

//---------------------------------------------------

    // friends
    @JsonIgnore
    @ManyToMany
    private List<MyUserTable> friends;

//---------------------------------------------------

    @JsonIgnore
    @JsonIgnoreProperties("fromUser")
    @OneToMany(mappedBy = "fromUser",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageTable> messagesList;

    @JsonIgnore
    @JsonIgnoreProperties("toUser")
    @OneToMany(mappedBy = "toUser",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MessageTable> messagesList2;

//---------------------------------------------------

    public MyUserTable(String fullName, String nickName, String birthday, String userEMail, String password) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.birthday = birthday;
        this.userEMail = userEMail;
        this.password = password;
        this.avatar = "";
        this.instagram = "https://www.instagram.com";
        this.facebook = "https://www.facebook.com/";
        this.aboutMe = "";
        this.privateAccount = false;
        this.role = "USER";
    }

    public void addFriend(MyUserTable newFriend){
        this.friends.add(newFriend);
    }

    @Override
    public String toString() {
        return "MyUserTable{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", nickName='" + nickName + '\'' +
                ", birthday='" + birthday + '\'' +
                ", userEMail='" + userEMail + '\'' +
                ", password='" + password + '\'' +
                ", avatar='" + avatar + '\'' +
                ", instagram='" + instagram + '\'' +
                ", facebook='" + facebook + '\'' +
                ", aboutMe='" + aboutMe + '\'' +
                ", role='" + role + '\'' +
                ", privateAccount=" + privateAccount +
                '}';
    }
}
