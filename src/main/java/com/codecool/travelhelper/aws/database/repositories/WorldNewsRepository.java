package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.WorldNewsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WorldNewsRepository  extends JpaRepository<WorldNewsTable, Long> {
    Optional<WorldNewsTable> findAllByCityNameAndCountryName(String cityName, String countryName);

    void deleteByCityNameAndCountryName(String cityName, String countryName);
}
