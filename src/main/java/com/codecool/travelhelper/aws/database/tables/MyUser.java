package com.codecool.travelhelper.aws.database.tables;

import lombok.Getter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@ToString
public class MyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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


    public MyUser() {
    }

    public MyUser(String fullName, String nickName, Date birthday, String eMail, String password, String avatar, String instagram, String facebook, String aboutMe, String role, boolean privateAccount) {
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
