package com.codecool.travelhelper.aws.database.models.repositories;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentsTable, Long> {


}
