package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@Setter
@ToString
public class MessageTable implements Comparable<MessageTable>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String messageText;
    private String dateTimeOfSending;


    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "fromUserId")
    private MyUserTable fromUser;

    @ManyToOne
    @MapsId("id")
    @JoinColumn(name = "toUserId")
    private MyUserTable toUser;

    public MessageTable(String messageText, MyUserTable fromUser, MyUserTable toUser) {
        this.messageText = messageText;
        this.dateTimeOfSending = LocalDate.now().toString();
        this.fromUser = fromUser;
        this.toUser = toUser;
    }

    @Override
    public int compareTo(MessageTable o) {
        if (getDateTimeOfSending() == null || o.getDateTimeOfSending() == null) {
            return 0;
        }
        return getDateTimeOfSending().compareTo(o.getDateTimeOfSending());
    }
}
