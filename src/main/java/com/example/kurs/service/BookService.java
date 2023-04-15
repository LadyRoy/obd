package com.example.kurs.service;

import com.example.kurs.domain.entity.Book;
import com.example.kurs.dto.BookDto;
import com.example.kurs.dto.BookStorageDto;
import com.example.kurs.dto.BookStorageReaderDto;
import com.example.kurs.mapper.BookDtoMapper;
import com.example.kurs.mapper.CommonMapper;
import com.example.kurs.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@Service
@Transactional(readOnly = true)
public class BookService {
    private final AuthorBookService authorBookService;
    private final BookStorageService bookStorageService;
    private final BookStorageReaderService bookStorageReaderService;
    private final StorageService storageService;
    private final BookRepository bookRepository;

    @Autowired
    public BookService(AuthorBookService authorBookService,
                       BookStorageService bookStorageService,
                       BookStorageReaderService bookStorageReaderService,
                       StorageService storageService,
                       BookRepository bookRepository) {

        this.authorBookService = authorBookService;
        this.bookStorageService = bookStorageService;
        this.bookStorageReaderService = bookStorageReaderService;
        this.storageService = storageService;
        this.bookRepository = bookRepository;
    }

    public List<BookDto> getAll() {
        return bookRepository.findAll().stream()
                .map(BookDtoMapper::mapToDto)
                .collect(toList());
    }

    public BookStorageDto getBookStorageDtoByBookId(Integer bookId) {
        return bookRepository.findById(bookId)
                .map(BookDtoMapper::mapToDto)
                .map(BookDto::getBookStorage)
                .orElse(null);
    }

    @Transactional
    public BookDto create(BookDto bookDtoToCreate) {
        return create(bookDtoToCreate, null);
    }

    @Transactional
    public BookDto create(BookDto bookDtoToCreate, String oldLibraryLocation) {
        return Optional.ofNullable(bookDtoToCreate)
                .map(bookDto -> CommonMapper.map(bookDto, Book.class))
                .map(bookRepository::save)
                .map(book -> CommonMapper.map(book, BookDto.class))
                .map(bookDto -> {
                    var bookStorage = bookDtoToCreate.getBookStorage();
                    var createdStorage = storageService.create(bookStorage.getStorage(), oldLibraryLocation);

                    bookStorage.setBook(bookDto);
                    bookStorage.setStorage(createdStorage);
                    bookStorageService.create(bookStorage);
                    authorBookService.create(bookDtoToCreate.getAuthor(), bookDto);
                    return bookDto;
                })
                .orElse(null);
    }

    @Transactional
    public BookDto edit(BookDto bookDtoToEdit, String oldLibraryLocation) {
        if (ObjectUtils.isEmpty(bookDtoToEdit.getId())) {
            return null;
        }
        return create(bookDtoToEdit, oldLibraryLocation);
    }

    @Transactional
    public void deleteById(Integer bookId) {
        Optional.ofNullable(bookId)
                .ifPresent(bookRepository::deleteById);
    }

    public List<BookStorageReaderDto> getAllClaimedBooks() {
        return bookStorageReaderService.getAll();
    }

    @Transactional
    public void claimBook(BookStorageReaderDto bookStorageReaderDtoToCreate) {
        bookStorageReaderService.create(bookStorageReaderDtoToCreate);
    }

    @Transactional
    public void editClaimedBook(BookStorageReaderDto bookStorageReaderDtoToCreate) {
        bookStorageReaderService.edit(bookStorageReaderDtoToCreate);
    }
}
