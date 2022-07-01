package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.forum.services.PostService;
import com.codecool.travelhelper.forum.webclients.PostImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

//    // get user by id
//    @GetMapping("/user/{id}")
//    public ResponseEntity<MyUserTable> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
//        return commentService.findUserById(id);
//    }
//
//    // update post by id
//    @PutMapping("/posts/{id}")
//    public ResponseEntity<PostTable> updatePostById(@PathVariable(value = "id") Long id, @RequestBody PostTable postTable)
//            throws ResourceNotFoundException {
//        return postService.updatePost(id, postTable);
//    }
//
//    // delete post by id
//    @DeleteMapping("/posts/{id}")
//    public ResponseEntity<PostTable> deletePostById(@PathVariable(value = "id") Long id)
//            throws ResourceNotFoundException {
//        return postService.deletePost(id);
//    }
}
