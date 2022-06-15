package com.codecool.travelhelper.aws.database.repositories.user_page_repositories;

import com.codecool.travelhelper.aws.database.tables.user_page_tables.MyUserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<MyUserTable, Long> {

}
