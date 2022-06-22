package com.codecool.travelhelper.forum.repository;

import com.codecool.travelhelper.forum.model.CommentsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentsTable, Long> {


}
