package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.services.CommentService;
import com.codecool.travelhelper.forum.services.PostService;
import com.codecool.travelhelper.forum.webclients.CommentImpl;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    @Autowired
    LoginImpl loginImpl;

    @Autowired
    PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private CommentImpl comment;


    // get comment from frontend
    @PostMapping("/comments")
    public void getNewComments(@RequestBody String commentsTable) {
        comment.getAndSaveComments(commentsTable);
    }

    // send list of comments to frontend
    @GetMapping("/comments")
    public List<CommentsTable> getComments() {
        return commentRepository.findAll();
    }

    // send list of user comments to frontend
    @GetMapping("/myComments")
    public List<CommentsTable> getUserComments(){
        return commentRepository.findAllByMyUserTableId(loginImpl.getCurrentUserId());
    }


    // send favourite comments to frontend
    @GetMapping("/favouriteComments")
    public List<CommentsTable> getUserFavouriteComments(){
        List<CommentsTable> likedComments= new ArrayList<>();
        List<CommentsTable> userComments = commentRepository.findAllByMyUserTableId(loginImpl.getCurrentUserId());
        for (CommentsTable comment: userComments) {
            if(comment.getLikedByUsers().size() > 0){
                likedComments.add(comment);
            }
        }
        return likedComments;
    }

    @GetMapping("/user")
    public List<MyUserTable> getUsers(){
        return userRepository.findAll();
    }

    // add like to selected comment
    @PostMapping("/add_like_to_comment")
    public void getLikeComment(@RequestBody String likeComment) {
        comment.addLikeToComment(likeComment);
    }

    // delete selected comment
    @PutMapping("/delete_comment")
    public void deleteComment(@RequestBody String commentId) {
        comment.deleteComment(commentId);
    }

    // edit selected comment
    @PutMapping("/comment_edit")
    public void getCommentToEdit(@RequestBody String commentDetails) {
        comment.editComment(commentDetails);
    }
}
