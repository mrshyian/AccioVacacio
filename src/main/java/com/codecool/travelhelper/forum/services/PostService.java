package com.codecool.travelhelper.forum.services;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import com.codecool.travelhelper.aws.database.repositories.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional(readOnly = true)
    public List<PostTable> findAll(){
        return this.postRepository.findAll();
    }
}
