package com.codecool.travelhelper.forum.repositories;

import com.codecool.travelhelper.aws.database.tables.user_page_tables.CommentsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentsTable, Long> {


}
