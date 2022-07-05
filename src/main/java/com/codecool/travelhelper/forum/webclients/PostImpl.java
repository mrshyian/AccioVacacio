package com.codecool.travelhelper.forum.webclients;


import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.controllers.PostController;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Setter
@Component
public class PostImpl {


    List<PostTable> posts = new ArrayList<>();

    @Autowired
    UserRepository userRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    PostController postController;

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    PostRepository postRepository;

    public void saveNewPost(String post) {
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
            postTable.get().setTopic(postTable.get().getTopic());
            postTable.get().setPostText(postTable.get().getPostText());
            postTable.get().setPostImage(postTable.get().getPostImage());
            postTable.get().setMyUserTable(myUserTable);
            postTable.get().AddUserToLikedByUser(myUserTable);
            postRepository.save(postTable.get());

        }

    }


    @Transactional
    public void deletePost(String postId){
        JsonParser jsonParser = new JsonParser();
        JsonObject postToDeleteId = (JsonObject)jsonParser.parse(postId);

        String idPost = postToDeleteId.get("postId").getAsString();
//        commentRepository.deleteAllByPostId(Long.valueOf(idPost));
        postRepository.deleteAllById(Long.parseLong(idPost));

    }




    public void sortPosts(String countryAndCity){

        JsonParser jsonParser = new JsonParser();
        JsonObject postToDeleteId = (JsonObject)jsonParser.parse(countryAndCity);

        String country = postToDeleteId.get("country").getAsString();
//        String city = postToDeleteId.get("city").getAsString();
        String time = postToDeleteId.get("time").getAsString();

        List<CommentsTable> comments;

        if(time.equals("Latest")){
            comments = commentRepository.findAllByCountryOrderByCommentDateTimeAsc(country);
        }else{
            comments = commentRepository.findAllByCountryOrderByCommentDateTimeDesc(country);
        }

        for (CommentsTable comm: comments) {
            if(!posts.contains(comm.getPost())){
                posts.add(comm.getPost());
            }
        }


    }

    public List<PostTable> getSortedPosts(){
        return posts;
    }


    public void editPosts(String postDetails){
        JsonParser jsonParser = new JsonParser();
        JsonObject postToEdit = (JsonObject)jsonParser.parse(postDetails);

        String postText = postToEdit.get("postText").getAsString();
        String postId = postToEdit.get("postId").getAsString();

        Optional<PostTable> postTable = postRepository.findById(Long.valueOf(postId));

        if(postTable.isPresent()){
            postTable.get().setPostText(postText);
            postRepository.save(postTable.get());
        }
    }



}
