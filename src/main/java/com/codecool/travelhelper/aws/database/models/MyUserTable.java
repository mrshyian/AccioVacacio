package com.codecool.travelhelper.aws.database.models;

import lombok.*;

import javax.persistence.*;
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
    private Date birthday;
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

    // liked by user to user
    @ManyToOne
    @JoinColumn(name = "comment_id")
    private CommentsTable commentsTable;
//---------------------------------------------------

    // post to user
    @OneToMany(mappedBy = "myUserTable")
    private List<PostTable> posts;

    // user to post
    @OneToOne
    @JoinColumn(name = "post_id")
    private PostTable postTable;
//---------------------------------------------------

    //note to user
    @OneToOne(mappedBy = "myUserTable")
    private NoteTable noteTable;

    //user to note
    @OneToOne
    @JoinColumn(name = "note_id")
    private NoteTable note;

//---------------------------------------------------

    // albums to user
    @OneToMany(mappedBy = "myUserTable")
    List<AlbumFromTripsTable> albumsFromTripsTable;

    // user to album
    @OneToOne
    @JoinColumn(name = "album_id")
    private AlbumFromTripsTable albumFromTripsTable;

//---------------------------------------------------

    // placesWantToGo to user
    @OneToMany(mappedBy = "myUserTable")
    List<PlacesWantToGoTable> placesWantToGoTable;

    // user to placeWantToGo
    @OneToOne
    @JoinColumn(name = "place_want_to_go_id")
    private PlacesWantToGoTable placesWantToGo;

//---------------------------------------------------

    // visitedPlaces to user
    @OneToMany(mappedBy = "myUserTable")
    List<VisitedPlaceTable> visitedPlacesTable;

    // user to visitedPlace
    @OneToOne
    @JoinColumn(name = "visited_place_id")
    private VisitedPlaceTable visitedPlace;

//---------------------------------------------------

    // trips to user
    @OneToMany(mappedBy = "myUserTable")
    List<TripTable> tripsTable;

    // user to trip
    @OneToOne
    @JoinColumn(name = "trip_id")
    private TripTable tripTable;

//---------------------------------------------------

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
