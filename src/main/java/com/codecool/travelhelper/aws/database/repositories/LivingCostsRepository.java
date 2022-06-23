package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.LivingCostsTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivingCostsRepository  extends JpaRepository<LivingCostsTable, Long> {
}
