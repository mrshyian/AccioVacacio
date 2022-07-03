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
    private CommentService commentService;

    @Autowired
    private PostService postService;


    @Autowired
    private CommentRepository commentRepository;


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
        Long userId = loginImpl.getCurrentUserId();
        System.out.println("Get method");
        return commentService.findAll(userId);
    }

    @GetMapping("/myComments")
    public List<CommentsTable> getUserComments(){
        return commentRepository.findAllByMyUserTableId(loginImpl.getCurrentUserId());
    }

    @GetMapping("/favouriteComments")
    public List<PostTable> getUserFavouriteComments(){
        List<CommentsTable> likedComments= new ArrayList<>();
        List<CommentsTable> userComments = commentRepository.findAllByMyUserTableId(loginImpl.getCurrentUserId());
        for (CommentsTable comment: userComments) {
            if(comment.getLikedByUsers().size() > 0){
                likedComments.add(comment);
            }
        }
        return postService.getUserPostsByComments(likedComments);

    }

    @PostMapping("/add_like_to_comment")
    public void getLikeComment(@RequestBody String likeComment) {
        comment.addLikeToComment(likeComment);
    }


    @PutMapping("/delete_comment")
    public void deleteComment(@RequestBody String commentId) {
        comment.deleteComment(commentId);
    }

}
