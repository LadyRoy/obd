package com.example.kurs.repository;

import com.example.kurs.domain.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StorageRepository extends JpaRepository<Storage, Integer> {

    Optional<Storage> findByLibraryLocation(String libraryLocation);
}
