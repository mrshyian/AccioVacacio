package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.WeatherTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeatherRepository  extends JpaRepository<WeatherTable, Long> {
}
