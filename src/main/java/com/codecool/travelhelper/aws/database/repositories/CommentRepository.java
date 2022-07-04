package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentsTable, Long> {
    List<CommentsTable> findAllByMyUserTableId(Long userId);
    List<CommentsTable> findAllByCountryOrderByCommentDateTimeAsc(String country);
    List<CommentsTable> findAllByCountryOrderByCommentDateTimeDesc(String country);


//    @Override
//    List<CommentsTable> findAll();

    //    CommentsTable updateComment(Long id, CommentsTable comment);
//    CommentsTable deleteCommentsTablesById(Long id);

}
