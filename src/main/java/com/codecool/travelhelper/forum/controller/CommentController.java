package com.codecool.travelhelper.forum.controller;

import com.codecool.travelhelper.forum.model.CommentsTable;
import com.codecool.travelhelper.forum.service.CommentService;
import com.codecool.travelhelper.forum.webclient.CommentImpl;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentImpl comment;

    @PostMapping("/comments")
    public void getNewComments(@RequestBody String commentsTable ) {
        comment.getComments(commentsTable);
    }

    @GetMapping("/comments")
    public List<CommentsTable> getComments(){
        return commentService.findAll();
    }
}
