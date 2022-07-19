package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.PostTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository<PostTable, Long> {
//    List<PostTable> findAllByMyUserTableId(Long id);
    void deleteAllById(Long id);
    PostTable findPostTableById(Long id);
    List<PostTable> findAllByOrderByPostDateTimeDesc();
    Set<PostTable> findAllByOrderByCommentsDesc();
}

