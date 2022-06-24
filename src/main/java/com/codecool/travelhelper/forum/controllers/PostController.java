package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
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
}
