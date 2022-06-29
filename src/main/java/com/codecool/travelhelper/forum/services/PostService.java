package com.codecool.travelhelper.forum.services;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.MyUserTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import com.codecool.travelhelper.aws.database.repositories.UserRepository;
import com.codecool.travelhelper.forum.exceptions.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostService(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<PostTable> findAll(){
        return this.postRepository.findAll();
    }

    public ResponseEntity<MyUserTable> findUserById(Long id) throws ResourceNotFoundException {
        MyUserTable userTable =
                this.userRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("User not found for this id: " + id));
        return ResponseEntity.ok().body(userTable);
    }

    public ResponseEntity<PostTable> updatePost(Long id, PostTable postTable)
            throws ResourceNotFoundException {
        PostTable post =
                this.postRepository.findById(id).orElseThrow(() ->
                        new ResourceNotFoundException("Post not found for this id: " + id));
        post.setTopic(postTable.getTopic());
        post.setPostText(postTable.getPostText());
        post.setPostImage(postTable.getPostImage());
        return ResponseEntity.ok().body(post);
    }

    public ResponseEntity<PostTable> deletePost(Long id)
            throws ResourceNotFoundException {
        this.postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found for this id: " + id));
        postRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
