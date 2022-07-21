package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.PostTable;
import com.codecool.travelhelper.forum.models.CommentModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<PostTable, Long> {

    void deleteAllById(Long id);
    PostTable findPostTableById(Long id);
    List<PostTable> findAllByOrderByPostDateTimeDesc();
    Set<PostTable> findAllByOrderByCommentsAsc();

    @Query("SELECT new com.codecool.travelhelper.forum.models.CommentModel(c.id, c.topic)FROM PostTable AS c GROUP BY c.id ORDER BY size(c.comments) ASC")
    List<CommentModel> getListOfPostId(Pageable pageable);

}