package com.codecool.travelhelper.forum.services;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepo;
    private final UserRepository userRepository;

    public CommentService(CommentRepository commentRepo, UserRepository userRepository) {
        this.commentRepo = commentRepo;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<CommentsTable> findAll() {
        return this.commentRepo.findAll();
    }

    public ResponseEntity<MyUserTable> findUserById(Long id) throws ResourceNotFoundException {
        MyUserTable userTable =
                this.userRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("User not found for this id: " + id));
        return ResponseEntity.ok().body(userTable);
    }

    public ResponseEntity<CommentsTable> updateComment(Long id, CommentsTable comment)
            throws ResourceNotFoundException {
        CommentsTable commentsTable =
                this.commentRepo.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Comment not found for this id: " + id));
        commentsTable.setCommentText(comment.getCommentText());
        commentsTable.setCommentImage(comment.getCommentImage());
        commentsTable.setCity(comment.getCity());
        commentsTable.setCountry(comment.getCountry());
        return ResponseEntity.ok().body(commentsTable);
    }

    public ResponseEntity<CommentsTable> deleteComment(Long id, CommentsTable comment)
            throws ResourceNotFoundException {
        this.commentRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Comment not found for this id: " + id));
        commentRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
