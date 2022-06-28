package com.codecool.travelhelper.aws.database.models.repositories;

import com.codecool.travelhelper.aws.database.models.PostTable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostTable, Long> {
}
