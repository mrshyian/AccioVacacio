package com.codecool.travelhelper.aws.database.repositories.forum;

import com.codecool.travelhelper.aws.database.tables.search_city_tables.AirportDetailsTable;
import com.codecool.travelhelper.aws.database.tables.user_page_tables.CommentsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<CommentsTable, Long>{
}
