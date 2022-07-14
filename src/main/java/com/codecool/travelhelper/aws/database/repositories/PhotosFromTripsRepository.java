package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.PhotosFromTripsTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotosFromTripsRepository extends JpaRepository<PhotosFromTripsTable, Long> {
    PhotosFromTripsTable findPhotosFromTripsTableById(Long id);
//    Optional<List<PhotosFromTripsTable>> findAllByMyUserTableId(Long id);
}
