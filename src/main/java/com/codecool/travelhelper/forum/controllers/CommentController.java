package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.exceptions.ResourceNotFoundException;
import com.codecool.travelhelper.forum.services.CommentService;
import com.codecool.travelhelper.forum.webclients.CommentImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private CommentService commentService;

//    @Autowired
//    private CommentRepository commentRepository;
//
//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private CommentImpl comment;

    // get comment from frontend
    @PostMapping("/comments")
    public void getNewComments(@RequestBody String commentsTable) {
        comment.getComments(commentsTable);
    }

    // send list of comments to frontend
    @GetMapping("/comments")
    public List<CommentsTable> getComments() {
        return commentService.findAll();
    }
//
//    // get user by id
//    @GetMapping("/user/{id}")
//    public MyUserTable getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
//        return userRepository.findMyUserTableById(id);
//    }
//
//    // update comment by id
//    @PutMapping("/comment/{id}")
//    public ResponseEntity<CommentsTable> updateCommentById(@PathVariable(value = "id") Long id, @RequestBody CommentsTable commentsTable)
//            throws ResourceNotFoundException {
//        return commentService.updateComment(id, commentsTable);
//    }
//
//    // delete comment by id
//    @DeleteMapping("/comment/{id}")
//    public CommentsTable deleteCommentById(@PathVariable(value = "id") Long id)
//            throws ResourceNotFoundException {
//        return commentRepository.deleteCommentsTablesById(id);
//    }
}
