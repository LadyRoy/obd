package com.example.kurs.repository;

import com.example.kurs.domain.entity.BookStorage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStorageRepository extends JpaRepository<BookStorage, Integer> {

    Optional<BookStorage> findByBookIdAndStorageId(Integer bookId, Integer storageId);
}
