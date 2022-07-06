package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<PostTable, Long> {

//    PostTable findPostTableById(Long id);
//    PostTable findAllById(Long id);


//    List<PostTable> findPostTableByMyUserTableId(Long id);

//    @Override
//    List<PostTable> findAll();


    //    PostTable updatePost(Long id, PostTable postTable);
//    CommentsTable deleteCommentsTablesById(Long id);
}
