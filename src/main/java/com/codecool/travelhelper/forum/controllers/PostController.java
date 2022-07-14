package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.services.PostService;
import com.codecool.travelhelper.forum.webclients.PostImpl;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private PostImpl post;

    @Autowired
    private LoginImpl loginImpl;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private PostRepository postRepository;

    // get new posts from frontend
    @PostMapping("/posts")
    public void getNewPost(@RequestBody String postTable ) {
        post.saveNewPost(postTable);
    }

    // send posts to frontend
    @GetMapping("/posts")
    public List<PostTable> getPosts(){
        return postService.findAll();
    }

    // add like to selected post
    @PostMapping("/add_like_to_post")
    public void getLikePost(@RequestBody String likePost) {
        post.addLikeToPost(likePost);
    }

    // send sorted posts to frontend
    @GetMapping("/get_sorted_posts")
    public List<PostTable> getSortedComments(){
        return post.getSortedPosts();
    }

    // send list of favourite comments to frontend
    @GetMapping("/favouriteCommentsPosts")
    public List<PostTable> getUserFavouritePostsByComments(){
        List<CommentsTable> likedComments= new ArrayList<>();
        List<CommentsTable> userComments = commentRepository.findAll();
        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        for (CommentsTable comment: userComments) {
            if(comment.getLikedByUsers().contains(myUserTable)){
                likedComments.add(comment);
            }
        }
        return postService.getUserPostsByComments(likedComments);
    }

    // send list of user posts selected by comments to frontend
    @GetMapping("/myCommentsPosts")
    public List<PostTable> getUserComments(){
        List<CommentsTable> userComments = commentRepository.findAllByMyUserTableId(loginImpl.getCurrentUserId());
        List<PostTable> posts= new ArrayList<>();
        for (CommentsTable comment: userComments) {
            if(postRepository.findById(comment.getPost().getId()).isPresent()){
                if(!posts.contains(comment.getPost())){
                    posts.add(comment.getPost());
                }
            }
        }
        return posts;
    }

    // send sorted posts to frontend
    @GetMapping("/sort_by")
    public List<PostTable> sortPosts() {
        return post.getSortedPosts();
    }

    //delete selected post
    @PutMapping("/delete_post")
    public void deletePost(@RequestBody String postId) {
        post.deletePost(postId);
    }

    // edit selected post
    @PutMapping("/post_edit")
    public void editPost(@RequestBody String postDetails){
        post.editPosts(postDetails);
    }

}
