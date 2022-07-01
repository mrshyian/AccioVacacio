package com.codecool.travelhelper.forum.webclients;


import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Component
public class PostImpl {

    @Autowired
    UserRepository userRepository;

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    PostRepository postRepository;

    public void getComments(String post) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(post);
        String topic = commentJsonObject.get("topic").getAsString();
        String postText = commentJsonObject.get("postText").getAsString();

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);

        String postImage = "https://media-exp1.licdn.com/dms/image/C4D03AQGdyWRtTOqpUg/profile-displayphoto-shrink_200_200/0/1616239437610?e=1659571200&v=beta&t=pTuXFgcCY0aLZhgx3Q6zpsLhfS9fo69n__YaWFKOIEE";

        postRepository.save(new PostTable(
                    topic,
                    postText,
                    postImage,
                    myUserTable,
                    new HashSet<>()
                )
        );
    }

    public void addLikeToPost(String likePost){
        JsonParser jsonParser = new JsonParser();
        JsonObject likeForPost = (JsonObject)jsonParser.parse(likePost);

        String postId = likeForPost.get("postId").getAsString();

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);

        Optional<PostTable> postTable = postRepository.findById(Long.valueOf(postId));

        if(postTable.isPresent()){
//            postTable = Optional.of((new PostTable(
//                    postTable.get().getTopic(),
//                    postTable.get().getPostText(),
//                    postTable.get().getPostImage(),
//                    myUserTable,
//                    new HashSet<>(postTable.get().AddUserToLikedByUser(myUserTable))
//            )));
            postTable.get().setTopic(postTable.get().getTopic());
            postTable.get().setPostText(postTable.get().getPostText());
            postTable.get().setPostImage(postTable.get().getPostImage());
            postTable.get().setMyUserTable(myUserTable);
            postTable.get().AddUserToLikedByUser(myUserTable);
            postRepository.save(postTable.get());
//            postTable = Optional.empty();
        }

    }


}
