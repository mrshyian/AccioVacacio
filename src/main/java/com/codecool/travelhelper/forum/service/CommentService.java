package com.codecool.travelhelper.forum.service;

import com.codecool.travelhelper.forum.model.CommentsTable;
import com.codecool.travelhelper.forum.repository.CommentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CommentService {
    private final CommentRepository commentRepo;

    public CommentService(CommentRepository commentRepo) {
        this.commentRepo = commentRepo;
    }

    @Transactional(readOnly = true)
    public List<CommentsTable> findAll(){
        return this.commentRepo.findAll();
    }
}
