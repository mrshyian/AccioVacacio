package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.MyUserTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<MyUserTable, Long> {
    MyUserTable findAllByUserEMail(String email);
    MyUserTable findMyUserTableById(Long id);
    MyUserTable findAllById(Long userId);

}
