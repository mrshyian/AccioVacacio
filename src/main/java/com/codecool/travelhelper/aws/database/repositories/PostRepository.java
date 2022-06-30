package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.CommentsTable;
import com.codecool.travelhelper.aws.database.models.PostTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostTable, Long> {
//    PostTable updatePost(Long id, PostTable postTable);
//    CommentsTable deleteCommentsTablesById(Long id);
}
