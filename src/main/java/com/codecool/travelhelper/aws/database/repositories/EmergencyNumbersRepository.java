package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.EmergencyNumbersTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmergencyNumbersRepository  extends JpaRepository<EmergencyNumbersTable, Long> {
}
