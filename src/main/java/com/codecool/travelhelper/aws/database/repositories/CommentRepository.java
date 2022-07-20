package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<CommentsTable, Long> {
    List<CommentsTable> findAllByMyUserTableId(Long userId);
    List<CommentsTable> findAllByCountryOrderByCommentDateTimeAsc(String country);
    List<CommentsTable> findAllByCountryOrderByCommentDateTimeDesc(String country);
    CommentsTable findCommentsTableById(Long id);
//    List<CommentsTable> findAllByOrderByCommentDateTimeAsc(String country);

    @EntityGraph(value = "likedByUser")
    public CommentsTable getCommentsTableById(Long id);

//    @Query("SELECT c.post.id FROM CommentsTable AS c GROUP BY c.post.id ORDER BY c.post.id ASC")
//    List<Object[]> getListOfPostId();

//    private String commentText;
//    private String commentImage;
//    private String country;
//    private String city;
//    private String commentDateTime;
//    private String userName;

//    void deleteCommentsTableByPostTableId
//    void deleteAllByPostId(Long Id);


}
