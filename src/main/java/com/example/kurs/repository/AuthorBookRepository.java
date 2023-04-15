package com.example.kurs.repository;

import com.example.kurs.domain.entity.AuthorBook;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorBookRepository extends JpaRepository<AuthorBook, Integer> {

    Optional<AuthorBook> findByAuthorIdAndBookId(Integer authorId, Integer bookId);

    List<AuthorBook> findAllByAuthorId(Integer authorId);
}
