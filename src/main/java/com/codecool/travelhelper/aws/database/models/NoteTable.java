package com.codecool.travelhelper.aws.database.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity(name = "note_table")
@Getter
@Setter
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
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private MyUserTable myUserTable;

//---------------------------------------------------

    public NoteTable(String noteText, MyUserTable myUserTable) {
        this.noteText = noteText;
        this.myUserTable=myUserTable;
    }
}
