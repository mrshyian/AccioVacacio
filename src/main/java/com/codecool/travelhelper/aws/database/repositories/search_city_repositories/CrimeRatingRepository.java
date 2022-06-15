package com.codecool.travelhelper.aws.database.repositories.search_city_repositories;

import com.codecool.travelhelper.aws.database.tables.search_city_tables.CrimeRatingTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CrimeRatingRepository  extends JpaRepository<CrimeRatingTable, Long> {

    List<CrimeRatingTable> findByCityNameAndCountryName(String cityName, String countryName);
}
