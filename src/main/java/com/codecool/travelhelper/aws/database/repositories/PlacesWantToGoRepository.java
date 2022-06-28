package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.PlacesWantToGoTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlacesWantToGoRepository extends JpaRepository<PlacesWantToGoTable, Long> {
    Optional<PlacesWantToGoTable> findByCountryAndCityAndMyUserTableId(String country, String city, Long userId);
}
