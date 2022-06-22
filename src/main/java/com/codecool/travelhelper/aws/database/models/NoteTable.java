package com.codecool.travelhelper.aws.database.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "Note_table")
@Getter
@ToString
@NoArgsConstructor
public class NoteTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    private Long id;

    private String noteText;

//---------------------------------------------------

    //note to user
    @OneToOne
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;


    //user to note
    @OneToOne(mappedBy = "note")
    private MyUserTable userTable;

//---------------------------------------------------

    public NoteTable(String noteText) {
        this.noteText = noteText;
    }
}
