package com.codecool.travelhelper.userPage.webclients;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.NoteTable;
import com.codecool.travelhelper.aws.database.models.repositories.NoteRepository;
import com.codecool.travelhelper.aws.database.models.repositories.UserRepository;
import com.codecool.travelhelper.loginAndRegistration.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class NoteImpl {

    @Autowired
    NoteRepository noteRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginImpl loginImpl;

    public void setNoteTextByUserId(String noteText) {
        NoteTable updatedResponseObject;

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable userFromDB = userRepository.findAllById(userId);

        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(noteText);
        String newNoteText = commentJsonObject.get("noteText").getAsString();

        NoteTable newNoteTable = new NoteTable(newNoteText, userFromDB);

        Optional<NoteTable> response = noteRepository.findAllByMyUserTableId(userId);

        if (response.isPresent()){
            updatedResponseObject = response.get();
            updateResponseObject(newNoteTable, updatedResponseObject);
        } else {
            updatedResponseObject = newNoteTable;
        }

        noteRepository.save(updatedResponseObject);

    }


    private void updateResponseObject(NoteTable newObject, NoteTable objectFromDB){
        objectFromDB.setNoteText(newObject.getNoteText());
    }

    public String getNote() {
        Long userId = loginImpl.getCurrentUserId();
        return noteRepository.findAllByMyUserTableId(userId).get().getNoteText();
    }
}
