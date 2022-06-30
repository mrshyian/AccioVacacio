package com.codecool.travelhelper.forum.webclients;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentImpl {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    UserRepository userRepository;

    public void getComments(String comments) {
        System.out.println("string");
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(comments);
        String comment = commentJsonObject.get("name").getAsString();
        String postId = commentJsonObject.get("postId").getAsString();



        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);
        Optional<PostTable> postTable = postRepository.findById(Long.parseLong(postId));
        String commentImg = "https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE";
        String country = "Poland";
        String city = "Poznan";
        postTable.ifPresent(table -> commentRepository.save(new CommentsTable(
                        comment,
                        commentImg,
                        country,
                        city,
                        myUserTable,
                        table
                )
        ));





    }

}
