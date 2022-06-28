package com.codecool.travelhelper.forum.webclients;

import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.models.repositories.PostRepository;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PostImpl {


    @Autowired
    PostRepository postRepository;

    public void getComments(String post) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(post);
        String topic = commentJsonObject.get("topic").getAsString();
        String postText = commentJsonObject.get("postText").getAsString();

        String postImage = "https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE";

        postRepository.save(new PostTable(
                    topic,
                    postText,
                    postImage
                )
        );
    }
}
