package com.codecool.travelhelper.forum.controller;

import com.codecool.travelhelper.aws.database.tables.user_page_tables.CommentsTable;
import com.codecool.travelhelper.forum.webclient.CommentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentImpl forumComment;

    @GetMapping("/comment")
    public CommentsTable getComments() {
        return forumComment.getAllComments();
    }
}
