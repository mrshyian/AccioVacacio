package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.PostTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

public interface PostRepository extends JpaRepository<PostTable, Long> {
//    List<PostTable> findAllByMyUserTableId(Long id);
    void deleteAllById(Long id);
}

