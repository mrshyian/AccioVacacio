package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.AirportDetailsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AirportDetailsRepository extends JpaRepository<AirportDetailsTable, Long>{
}
