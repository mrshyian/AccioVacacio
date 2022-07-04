package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.forum.services.PostService;
import com.codecool.travelhelper.forum.webclients.PostImpl;
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

    // get new posts from frontend
    @PostMapping("/posts")
    public void getNewPost(@RequestBody String postTable ) {
        post.getComments(postTable);
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

    // sort posts by provided parameters
    @PostMapping("/sort_by")
    public void sortPost(@RequestBody String countryAndCity) {
        post.setPosts(new ArrayList<>());
        post.sortPost(countryAndCity);
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

    @PutMapping("/post_edit")
    public void editPost(@RequestBody String postDetails){
        post.editPosts(postDetails);
    }

}
