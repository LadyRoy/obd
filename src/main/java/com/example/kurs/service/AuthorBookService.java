package com.example.kurs.service;

import com.example.kurs.domain.entity.AuthorBook;
import com.example.kurs.dto.AuthorBookDto;
import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.AuthorBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class AuthorBookService {
    private final AuthorBookRepository authorBookRepository;

    @Autowired
    public AuthorBookService(AuthorBookRepository authorBookRepository) {
        this.authorBookRepository = authorBookRepository;
    }

    public List<AuthorBookDto> getAll() {
        return authorBookRepository.findAll().stream()
                .map(authorBook -> CommonMapper.map(authorBook, AuthorBookDto.class))
                .collect(toList());
    }

    public List<BookDto> getBooksByAuthorId(Integer authorId) {
        return authorBookRepository.findAllByAuthorId(authorId).stream()
                .map(authorBook -> CommonMapper.map(authorBook, AuthorBookDto.class))
                .map(AuthorBookDto::getBook)
                .collect(toList());
    }

    @Transactional
    public void create(AuthorDto authorDto, BookDto bookDto) {
        var savedAuthorBookDto = getByAuthorAndBookIds(authorDto.getId(), bookDto.getId());

        if (ObjectUtils.isEmpty(savedAuthorBookDto)) {
            savedAuthorBookDto = new AuthorBookDto(null, authorDto, bookDto);

        } else {
            savedAuthorBookDto.setAuthor(authorDto);
            savedAuthorBookDto.setBook(bookDto);
        }
        authorBookRepository.save(CommonMapper.map(savedAuthorBookDto, AuthorBook.class));
    }

    public AuthorBookDto getByAuthorAndBookIds(Integer authorId, Integer bookId) {
        return Optional.ofNullable(authorBookRepository.findByAuthorIdAndBookId(authorId, bookId))
                .map(authorBook -> CommonMapper.map(authorBook, AuthorBookDto.class))
                .orElse(null);
    }
}
