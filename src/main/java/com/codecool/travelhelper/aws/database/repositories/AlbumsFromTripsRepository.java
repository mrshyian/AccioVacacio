package com.codecool.travelhelper.aws.database.repositories;

import com.codecool.travelhelper.aws.database.models.AlbumFromTripsTable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlbumsFromTripsRepository extends JpaRepository<AlbumFromTripsTable, Long> {
    Optional<AlbumFromTripsTable> findAllByMyUserTableIdAndAlbumName(Long userId, String albumName);
}
