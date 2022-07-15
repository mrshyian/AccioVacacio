package com.codecool.travelhelper.userPage.controllers;

import com.codecool.travelhelper.userPage.webclients.NoteImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class NotesController {

    @Autowired
    private NoteImpl noteImpl;

    @PostMapping("/notes")
    public void setNote(@RequestBody String noteText ) {
        noteImpl.setNoteTextByUserId(noteText);
    }

    @GetMapping("/notes")
    public String getNoteText(){
        return noteImpl.getNote();
    }
}
