package com.codecool.travelhelper.aws.database.repositories.search_city_repositories;

import com.codecool.travelhelper.aws.database.tables.search_city_tables.WorldNewsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorldNewsRepository  extends JpaRepository<WorldNewsTable, Long> {
}
