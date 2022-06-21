package com.codecool.travelhelper.forum.webclient;

import com.codecool.travelhelper.aws.database.repositories.forum.CommentRepository;
import com.codecool.travelhelper.aws.database.tables.user_page_tables.CommentsTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class CommentImpl {

    @Autowired
    CommentRepository commentRepository;

    public CommentsTable getAllComments(){

        String comment = "coś tam sobie działa";
        String commentImg = "nie ma";
        String country = "Poland";
        String city = "Poznan";
        UUID uuid = UUID.randomUUID();

        commentRepository.save( new CommentsTable(
                    comment,
                commentImg,
                country,
                city,
                uuid
                )
        );

        String response = new ResponseEntity<List<CommentsTable>>(commentRepository.findAll(), HttpStatus.OK).getBody().toString();
        System.out.println(response);

        int begin = response.indexOf("(") + 1;
        int end = response.lastIndexOf(")");
        response = response.substring(begin, end);
        String[] responseList = response.split(", ");

        Long responseIdLong = Long.parseLong(responseList[0].split("=")[1]);
        UUID responseId = UUID.fromString(responseList[1].split("=")[1]);
        String responseText = (responseList[2].split("=")[1]);
        String responseImg = (responseList[3].split("=")[1]);
        String responseCountry = (responseList[4].split("=")[1]);
        String responseCity = (responseList[5].split("=")[1]);
        String responseDatetime = (responseList[6].split("=")[1]);
        UUID responseUUID = UUID.fromString(responseList[7].split("=")[1]);
        return CommentsTable.builder()
                .id(responseIdLong)
                .userId(responseId)
                .commentTex(String.valueOf(responseText))
                .commentImage(String.valueOf(responseImg))
                .country(String.valueOf(responseCountry))
                .city(String.valueOf(responseCity))
                .commentDateTime(responseDatetime)
                .likedByUser(responseUUID)
                .build();
    }

}
