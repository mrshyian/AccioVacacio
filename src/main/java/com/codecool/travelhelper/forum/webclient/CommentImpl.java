package com.codecool.travelhelper.forum.webclient;

import com.codecool.travelhelper.forum.model.CommentsTable;
import com.codecool.travelhelper.forum.repository.CommentRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentImpl {

    @Autowired
    CommentRepository commentRepository;

    public void getComments(String comments) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(comments);
        String comment = commentJsonObject.get("name").getAsString();

        String commentImg = "https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE";
        String country = "Poland";
        String city = "Poznan";

        commentRepository.save(new CommentsTable(
                comment,
                commentImg,
                country,
                city
                )
        );
    }

}
