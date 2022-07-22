package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    @JoinColumn(name = "fromUserId")
    private MyUserTable fromUser;

    @ManyToOne
    @JoinColumn(name = "toUserId")
    private MyUserTable toUser;

    public MessageTable(String messageText, MyUserTable fromUser, MyUserTable toUser) {
        this.messageText = messageText;
        this.dateTimeOfSending = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
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
