package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
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
    List<AlbumFromTripsTable> albumsFromTripsTable;

//---------------------------------------------------

    // placesWantToGo to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    List<PlacesWantToGoTable> placesWantToGoTable;

//---------------------------------------------------

    // visitedPlaces to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    List<VisitedPlaceTable> visitedPlacesTable;

//---------------------------------------------------

    // trips to user
    @JsonIgnore
    @JsonIgnoreProperties("myUserTable")
    @OneToMany(mappedBy = "myUserTable",  cascade = CascadeType.ALL, orphanRemoval = true)
    List<TripTable> tripsTable;

//---------------------------------------------------

    public MyUserTable(String fullName, String nickName, String birthday, String userEMail, String password) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.birthday = birthday;
        this.userEMail = userEMail;
        this.password = password;
        this.avatar = "";
        this.instagram = "";
        this.facebook = "";
        this.aboutMe = "";
        this.privateAccount = false;
        this.role = "User";
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
