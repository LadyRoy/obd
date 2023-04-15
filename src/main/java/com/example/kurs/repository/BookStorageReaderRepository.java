package com.example.kurs.repository;

import com.example.kurs.domain.entity.BookStorageReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookStorageReaderRepository extends JpaRepository<BookStorageReader, Integer> {

    Optional<BookStorageReader> findByBookStorageIdAndReaderId(Integer bookStorageId, Integer readerId);
}
