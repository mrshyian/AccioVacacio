package com.codecool.travelhelper.forum.controllers;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.webclients.CommentImpl;
import com.codecool.travelhelper.login_registration_logout.webclients.LoginImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class CommentController {

    @Autowired
    private LoginImpl loginImpl;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    private CommentImpl commentImpl;

    // get comment from frontend
    @PostMapping("/comments")
    public void addNewComment(@RequestBody String commentsTable) {
        commentImpl.saveComments(commentsTable);
    }

    // send list of comments to frontend
    @GetMapping("/comments")
    public List<CommentsTable> getComments() {
        return commentRepository.findAll();
    }

    @GetMapping("/token")
    public String getTokenCsrf(HttpServletRequest request) {
        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
        System.out.println("weszło po token");
        return token.getToken();
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
        List<CommentsTable> userComments = commentRepository.findAll();
        MyUserTable myUserTable = userRepository.findMyUserTableById(loginImpl.getCurrentUserId());
        for (CommentsTable comment: userComments) {
            if(comment.getLikedByUsers().contains(myUserTable)){
                likedComments.add(comment);
            }
        }
        return likedComments;
    }

    // get sorted criteria
    @PostMapping("/sort_by")
    public void sortPost(@RequestBody String countryAndCity) {
        commentImpl.sortComments(countryAndCity);
    }

    // send sorted comments to frontend
    @GetMapping("/get_sorted_comments")
    public List<CommentsTable> getSortedComments(){
        return commentImpl.getSortedComments();
    }


    // add like to selected comment
    @PostMapping("/add_like_to_comment")
    public void getLikeComment(@RequestBody String likeComment) {
        commentImpl.addLikeToComment(likeComment);
    }

    // delete selected comment
    @PutMapping("/delete_comment")
    public void deleteComment(@RequestBody String commentId) {
        commentImpl.deleteComment(commentId);
    }

    // edit selected comment
    @PutMapping("/comment_edit")
    public void getCommentToEdit(@RequestBody String commentDetails) {
        commentImpl.editComment(commentDetails);
    }
}
