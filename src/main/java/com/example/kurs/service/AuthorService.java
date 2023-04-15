package com.example.kurs.service;

import com.example.kurs.domain.entity.Author;
import com.example.kurs.dto.AuthorDto;
import com.example.kurs.dto.BookDto;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class AuthorService {
    private final AuthorBookService authorBookService;
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorService(AuthorBookService authorBookService, AuthorRepository authorRepository) {
        this.authorBookService = authorBookService;
        this.authorRepository = authorRepository;
    }

    public List<AuthorDto> getAll() {
        return authorRepository.findAll().stream()
                .map(author -> CommonMapper.map(author, AuthorDto.class))
                .collect(toList());
    }

    public List<BookDto> getBooksByAuthorId(Integer authorId) {
        return authorBookService.getBooksByAuthorId(authorId);
    }

    @Transactional
    public AuthorDto create(AuthorDto authorDtoToCreate) {
        return Optional.ofNullable(authorDtoToCreate)
                .map(authorDto -> CommonMapper.map(authorDto, Author.class))
                .map(authorRepository::save)
                .map(author -> CommonMapper.map(author, AuthorDto.class))
                .orElse(null);
    }

    @Transactional
    public AuthorDto edit(AuthorDto authorDtoToEdit) {
        if (ObjectUtils.isEmpty(authorDtoToEdit.getId())) {
            return null;
        }
        return create(authorDtoToEdit);
    }

    @Transactional
    public void deleteById(Integer authorId) {
        Optional.ofNullable(authorId)
                .ifPresent(authorRepository::deleteById);
    }
}
