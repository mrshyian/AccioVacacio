package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.forum.services.CommentService;
import com.codecool.travelhelper.forum.webclients.CommentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
