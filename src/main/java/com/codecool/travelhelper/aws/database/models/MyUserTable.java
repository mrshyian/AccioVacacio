package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;
import java.lang.annotation.Repeatable;
import java.util.List;

@Entity(name = "MyUserTable")
@Getter
@ToString
@NoArgsConstructor
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
    @OneToMany(mappedBy = "myUserTable")
    @JsonIgnoreProperties("myUserTable")
    private List<CommentsTable> comments;

//---------------------------------------------------

    // post to user
    @JsonIgnore
    @OneToMany(mappedBy = "myUserTable")
    @JsonIgnoreProperties("myUserTable")
    private List<PostTable> posts;

//---------------------------------------------------

    //note to user
    @OneToOne(mappedBy = "myUserTable", fetch = FetchType.LAZY)
    @JsonIgnoreProperties("myUserTable")
    private NoteTable noteTable;

//---------------------------------------------------

    // albums to user
    @JsonIgnore
    @OneToMany(mappedBy = "myUserTable")
    @JsonIgnoreProperties("myUserTable")
    List<AlbumFromTripsTable> albumsFromTripsTable;

//---------------------------------------------------

    // placesWantToGo to user
    @JsonIgnore
    @OneToMany(mappedBy = "myUserTable")
    @JsonIgnoreProperties("myUserTable")
    List<PlacesWantToGoTable> placesWantToGoTable;

//---------------------------------------------------

    // visitedPlaces to user
    @JsonIgnore
    @OneToMany(mappedBy = "myUserTable")
    @JsonIgnoreProperties("myUserTable")
    List<VisitedPlaceTable> visitedPlacesTable;

//---------------------------------------------------

    // trips to user
    @JsonIgnore
    @OneToMany(mappedBy = "myUserTable")
    @JsonIgnoreProperties("myUserTable")
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
}
