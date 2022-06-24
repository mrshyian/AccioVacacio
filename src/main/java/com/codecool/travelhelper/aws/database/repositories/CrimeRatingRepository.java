package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.CrimeRatingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CrimeRatingRepository  extends JpaRepository<CrimeRatingTable, Long> {

    Optional<CrimeRatingTable> findAllByCityNameAndCountryName(String cityName, String countryName);

    void deleteByCityNameAndCountryName(String cityName, String countryName);
}
