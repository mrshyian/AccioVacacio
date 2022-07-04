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

    @PostMapping("/posts")
    public void getNewPost(@RequestBody String postTable ) {
        post.getComments(postTable);
    }

    @GetMapping("/posts")
    public List<PostTable> getPosts(){
        return postService.findAll();
    }

    @PostMapping("/add_like_to_post")
    public void getLikePost(@RequestBody String likePost) {
        post.addLikeToPost(likePost);
    }

    @PostMapping("/sort_by")
    public void sortPost(@RequestBody String countryAndCity) {
        post.setPosts(new ArrayList<>());
        post.sortPost(countryAndCity);
    }

    @GetMapping("/sort_by")
    public List<PostTable> sortPosts() {
        System.out.println("sort by");
        return post.getSortedPosts();
    }

    @PutMapping("/delete_post")
    public void deletePost(@RequestBody String postId) {
        post.deletePost(postId);
    }

}
