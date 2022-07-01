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

import java.util.HashSet;
import java.util.Optional;

@Component
public class CommentImpl {

    @Autowired
    LoginImpl loginImpl;



    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;



    public void getAndSaveComments(String comments) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(comments);

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);

        String comment = commentJsonObject.get("name").getAsString();
        String postId = commentJsonObject.get("postId").getAsString();

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
                        table,
                        new HashSet<>()
                )
        ));

    }

    public void addLikeToComment(String likeComment){
        JsonParser jsonParser = new JsonParser();
        JsonObject likeForComment = (JsonObject)jsonParser.parse(likeComment);

        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);

        String commentId = likeForComment.get("commentId").getAsString();

        Optional<CommentsTable> commentsTable = commentRepository.findById(Long.valueOf(commentId));

        if(commentsTable.isPresent()){
            commentsTable.get().setCommentText(commentsTable.get().getCommentText());
            commentsTable.get().setCommentImage(commentsTable.get().getCommentImage());
            commentsTable.get().setCountry(commentsTable.get().getCountry());
            commentsTable.get().setCity(commentsTable.get().getCity());
            commentsTable.get().setMyUserTable(myUserTable);
            commentsTable.get().AddUserToLikedByUser(myUserTable);
            commentRepository.save(commentsTable.get());

        }
    }

    public void deleteComment(String commentId){
        JsonParser jsonParser = new JsonParser();
        JsonObject likeForPost = (JsonObject)jsonParser.parse(commentId);

//        Long userId = loginImpl.getCurrentUserId();
//        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);

        String idComment = likeForPost.get("commentId").getAsString();
        commentRepository.deleteById(Long.parseLong(idComment));

    }

}
