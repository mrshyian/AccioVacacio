package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MyUserTable")
@Getter
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
    @OneToMany(mappedBy = "myUserTable", fetch = FetchType.LAZY)
    private List<CommentsTable> comments;

//---------------------------------------------------

    // post to user
    @OneToMany(mappedBy = "myUserTable", fetch = FetchType.LAZY)
    private List<PostTable> posts;

//---------------------------------------------------

    //note to user
    @OneToOne(mappedBy = "myUserTable", fetch = FetchType.LAZY)
    private NoteTable noteTable;

//---------------------------------------------------

    // albums to user
    @OneToMany(mappedBy = "myUserTable", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<AlbumFromTripsTable> albumsFromTripsTable;

//---------------------------------------------------

    // placesWantToGo to user
    @OneToMany(mappedBy = "myUserTable", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<PlacesWantToGoTable> placesWantToGoTable;

//---------------------------------------------------

    // visitedPlaces to user
    @OneToMany(mappedBy = "myUserTable", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    List<VisitedPlaceTable> visitedPlacesTable;

//---------------------------------------------------

    // trips to user
    @OneToMany(mappedBy = "myUserTable", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
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
