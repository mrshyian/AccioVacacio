package com.codecool.travelhelper.forum.services;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@Service
@Transactional
public class CommentService {

    @Autowired
    private CommentRepository commentRepo;

    @Transactional(readOnly = true)
    public List<CommentsTable> findAll(Long userId) {
        return this.commentRepo.findAllById(Collections.singleton(userId));
    }

}
