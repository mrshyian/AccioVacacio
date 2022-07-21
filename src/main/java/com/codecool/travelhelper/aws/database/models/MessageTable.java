package com.codecool.travelhelper.aws.database.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class MessageTable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageTitle;
    private String messageText;
    private String dateTimeOfSending;
    private boolean wasSeen;


    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "fromUserId")
    private MyUserTable fromUser;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "toUserId")
    private MyUserTable toUser;

    public MessageTable(String messageTitle, String messageText, MyUserTable fromUser, MyUserTable toUser) {
        this.messageTitle = messageTitle;
        this.messageText = messageText;
        this.dateTimeOfSending = LocalDate.now().toString();
        this.wasSeen = false;
        this.fromUser = fromUser;
        this.toUser = toUser;
    }
}
