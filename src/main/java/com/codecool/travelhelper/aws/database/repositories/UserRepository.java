package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.tables.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

}
