package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.services.CommentService;
import com.codecool.travelhelper.forum.webclients.CommentImpl;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

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
        System.out.println("POST method");

        comment.getAndSaveComments(commentsTable);
    }

    // send list of comments to frontend
    @GetMapping("/comments")
    public List<CommentsTable> getComments() {
        Long userId = loginImpl.getCurrentUserId();
        System.out.println("Get method");
        return commentService.findAll(userId);
    }

    @GetMapping("/comment_user")
    public List<MyUserTable> getUserForComments(){
//        Long userId = loginImpl.getCurrentUserId();
        return userRepository.findAll();
    }

//    // get user by id
//    @GetMapping("/user/{id}")
//    public ResponseEntity<MyUserTable> getUserById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
//        return commentService.findUserById(id);
//    }
//
//    // update comment by id
//    @PutMapping("/comment/{id}")
//    public ResponseEntity<CommentsTable> updateCommentById(@PathVariable(value = "id") Long id, @RequestBody CommentsTable commentsTable)
//            throws ResourceNotFoundException {
//        return commentService.updateComment(id, commentsTable);
//    }
//    // delete comment by id
//    @DeleteMapping("/comment/{id}")
//    public ResponseEntity<CommentsTable> deleteCommentById(@PathVariable(value = "id") Long id, @RequestBody CommentsTable commentsTable)
//            throws ResourceNotFoundException {
//        return commentService.deleteComment(id, commentsTable);
//    }
}
