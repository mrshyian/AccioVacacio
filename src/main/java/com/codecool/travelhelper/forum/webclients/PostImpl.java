package com.codecool.travelhelper.forum.webclients;


import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.aws.imagestore.controllers.S3PostController;
import com.codecool.travelhelper.forum.controllers.PostController;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.*;

@Setter
@Component
public class PostImpl {



    private List<PostTable> posts = new ArrayList<>();

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private S3PostController s3PostController;

    @Autowired
    private PostController postController;

    @Autowired
    private LoginImpl loginImpl;

    @Autowired
    private CommentImpl commentImpl;

    @Autowired
    private PostRepository postRepository;

    public void saveNewPost(String post) {
        JsonParser jsonParser = new JsonParser();
        JsonObject commentJsonObject = (JsonObject)jsonParser.parse(post);

        String topic = commentJsonObject.get("topic").getAsString();
        String postText = commentJsonObject.get("postText").getAsString();


        Long userId = loginImpl.getCurrentUserId();
        MyUserTable myUserTable = userRepository.findMyUserTableById(userId);

        postRepository.save(new PostTable(
                    topic,
                    postText,
                    null,
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

        s3PostController.deletePostImage(postRepository.findPostTableById(Long.valueOf(idPost)).getPostImage());
        postRepository.deleteAllById(Long.parseLong(idPost));

    }

    public void sortPosts(List<CommentsTable> comments){
        setPosts(new ArrayList<>());
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
