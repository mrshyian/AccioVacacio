package com.codecool.travelhelper.aws.database.tables.user_page_tables;

import com.codecool.travelhelper.aws.database.repositories.user_page_repositories.UserRepository;
import lombok.Getter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
@Table(uniqueConstraints = @UniqueConstraint(columnNames = "eMail"))
public class MyUserTable {

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


    public MyUserTable() {
    }

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

//    @Autowired
//    UserRepository userRepository;


//            userRepository.save(
//                    new MyUserTable(
//                        "Seba",
//                                "Basket",
//                                new Date(),
//                        "seba@wp.pl",
//                                "1234",
//                                "Lol",
//                                "instagram",
//                                "facebook;",
//                                "Lubie placki",
//                                "Admin",
//                                true
//                                )
//                                );
}
