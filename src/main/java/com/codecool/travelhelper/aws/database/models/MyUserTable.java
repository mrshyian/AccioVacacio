package com.codecool.travelhelper.aws.database.models;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
    private String eMail;
    private String password;
    private String avatar;
    private String instagram;
    private String facebook;
    private String aboutMe;
    private String role;
    private boolean privateAccount;


//----------------------------------------------------------------------

    // comments to user
    @OneToMany(mappedBy = "myUserTable")
    private List<CommentsTable> comments;

//---------------------------------------------------

    // post to user
    @OneToMany(mappedBy = "myUserTable")
    private List<PostTable> posts;

//---------------------------------------------------

    //note to user
    @OneToOne(mappedBy = "myUserTable")
    private NoteTable noteTable;

//---------------------------------------------------

    // albums to user
    @OneToMany(mappedBy = "myUserTable")
    List<AlbumFromTripsTable> albumsFromTripsTable;

//---------------------------------------------------

    // placesWantToGo to user
    @OneToMany(mappedBy = "myUserTable")
    List<PlacesWantToGoTable> placesWantToGoTable;

//---------------------------------------------------

    // visitedPlaces to user
    @OneToMany(mappedBy = "myUserTable")
    List<VisitedPlaceTable> visitedPlacesTable;

//---------------------------------------------------

    // trips to user
    @OneToMany(mappedBy = "myUserTable")
    List<TripTable> tripsTable;

//---------------------------------------------------

    public MyUserTable(String fullName, String nickName, String birthday, String eMail, String password, String avatar, String instagram, String facebook, String aboutMe, boolean privateAccount) {
        this.fullName = fullName;
        this.nickName = nickName;
        this.birthday = birthday;
        this.eMail = eMail;
        this.password = password;
        this.avatar = avatar;
        this.instagram = instagram;
        this.facebook = facebook;
        this.aboutMe = aboutMe;
        this.privateAccount = privateAccount;
        this.role = "User";
    }
}
