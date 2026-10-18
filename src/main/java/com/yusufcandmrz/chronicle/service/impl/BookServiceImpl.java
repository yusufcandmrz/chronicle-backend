package com.yusufcandmrz.chronicle.service.impl;

import com.yusufcandmrz.chronicle.dto.CreateBookRequest;
import com.yusufcandmrz.chronicle.dto.PageResponse;
import com.yusufcandmrz.chronicle.dto.ReadBookResponse;
import com.yusufcandmrz.chronicle.dto.UpdateBookRequest;
import com.yusufcandmrz.chronicle.entity.*;
import com.yusufcandmrz.chronicle.exception.BookNotFoundException;
import com.yusufcandmrz.chronicle.repository.BookRepository;
import com.yusufcandmrz.chronicle.repository.CompanyRepository;
import com.yusufcandmrz.chronicle.repository.PersonRepository;
import com.yusufcandmrz.chronicle.security.SecurityUtils;
import com.yusufcandmrz.chronicle.service.BookService;
import com.yusufcandmrz.chronicle.specification.BookSpecification;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final PersonRepository personRepository;
    private final CompanyRepository companyRepository;

    @Override
    @Transactional
    public void createBook(CreateBookRequest request) {
        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setReleaseYear(request.getReleaseYear());

        addPeople(book, request.getAuthorList(), BookPersonRole.AUTHOR);
        addPeople(book, request.getTranslatorList(), BookPersonRole.TRANSLATOR);
        addCompany(book, request.getPublisher(), BookCompanyRole.PUBLISHER);

        book.setAppUser(SecurityUtils.getCurrentUser());

        bookRepository.save(book);
    }

    private Person getOrCreatePerson(String name) {
        return personRepository.findByName(name)
                .orElseGet(() -> {
                    Person p = new Person();
                    p.setName(name);
                    return personRepository.save(p);
                });
    }

    private Company getOrCreateCompany(String name) {
        return companyRepository.findByName(name)
                .orElseGet(() -> {
                    Company c = new Company();
                    c.setName(name);
                    return companyRepository.save(c);
                });
    }

    private void addPerson(Book book, Person person, BookPersonRole bookPersonRole) {
        BookPerson bookPerson = new BookPerson();
        bookPerson.setBook(book);
        bookPerson.setPerson(person);
        bookPerson.setBookPersonRole(bookPersonRole);

        book.getBookPeople().add(bookPerson);
    }

    private void addPeople(Book book, List<String> personNames, BookPersonRole bookPersonRole) {
        for (String personName : personNames) {
            Person person = getOrCreatePerson(personName);
            addPerson(book, person, bookPersonRole);
        }
    }

    private void addCompany(Book book, String companyName, BookCompanyRole bookCompanyRole) {
        Company company = getOrCreateCompany(companyName);

        BookCompany bookCompany = new BookCompany();
        bookCompany.setBook(book);
        bookCompany.setCompany(company);
        bookCompany.setBookCompanyRole(bookCompanyRole);

        book.getBookCompanies().add(bookCompany);
    }

    @Override
    public ReadBookResponse readBookById(Long id) {
        Book book = findBookById(id);
        return mapToReadBookResponse(book);
    }

    @Override
    public PageResponse<ReadBookResponse> readAllBooks(
            String title, String authorName, String publisherName, String translatorName, Pageable pageable) {
        Specification<Book> specification = createBookSpecification(title, authorName, publisherName, translatorName);

        Long currentUserId = SecurityUtils.getCurrentUser().getId();

        Page<Book> books = bookRepository.findAll(specification.and((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.get("appUser").get("id"), currentUserId)), pageable);

        Page<ReadBookResponse> bookResponses = books.map(this::mapToReadBookResponse);

        return new PageResponse<>(
                bookResponses.getContent(),
                bookResponses.getNumber(),
                bookResponses.getSize(),
                bookResponses.getTotalElements(),
                bookResponses.getTotalPages(),
                bookResponses.isFirst(),
                bookResponses.isLast()
        );
    }

    private Specification<Book> createBookSpecification(
            String title, String authorName, String publisherName, String translatorName
    ) {
        Specification<Book> specification = Specification.unrestricted();

        if (title != null && !title.isBlank()) {
            specification = specification.and(BookSpecification.hasTitle(title));
        }

        if (authorName != null && !authorName.isBlank()) {
            specification = specification.and(BookSpecification.hasAuthorName(authorName));
        }

        if (publisherName != null && !publisherName.isBlank()) {
            specification = specification.and(BookSpecification.hasPublisherName(publisherName));
        }

        if (translatorName != null && !translatorName.isBlank()) {
            specification = specification.and(BookSpecification.hasTranslatorName(translatorName));
        }

        return specification;
    }

    private Book findBookById(Long id) {
        Long currentUserId = SecurityUtils.getCurrentUser().getId();
        return bookRepository.findByIdAndAppUserId(id, currentUserId)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    private ReadBookResponse mapToReadBookResponse(Book book) {
        ReadBookResponse readBookResponse = new ReadBookResponse();
        readBookResponse.setTitle(book.getTitle());
        readBookResponse.setReleaseYear(book.getReleaseYear());

        List<String> authorList = new ArrayList<>();
        List<String> translatorList = new ArrayList<>();

        for (BookPerson bookPerson : book.getBookPeople()) {
            String personName = bookPerson.getPerson().getName();
            BookPersonRole personRole = bookPerson.getBookPersonRole();
            if (personRole == BookPersonRole.AUTHOR) {
                authorList.add(personName);
            } else if (personRole == BookPersonRole.TRANSLATOR) {
                translatorList.add(personName);
            }
        }

        readBookResponse.setAuthorList(authorList);
        readBookResponse.setTranslatorList(translatorList);

        for (BookCompany bookCompany : book.getBookCompanies()) {
            if (bookCompany.getBookCompanyRole() == BookCompanyRole.PUBLISHER) {
                readBookResponse.setPublisher(bookCompany.getCompany().getName());
                break;
            }
        }

        return readBookResponse;
    }

    @Override
    @Transactional
    public void updateBookById(Long id, UpdateBookRequest request) {
        Book book = findBookById(id);

        book.setTitle(request.getTitle());
        book.setReleaseYear(request.getReleaseYear());

        book.getBookPeople().clear();
        addPeople(book, request.getAuthorList(), BookPersonRole.AUTHOR);
        addPeople(book, request.getTranslatorList(), BookPersonRole.TRANSLATOR);

        book.getBookCompanies().clear();
        addCompany(book, request.getPublisher(), BookCompanyRole.PUBLISHER);
    }

    @Override
    public void deleteBookById(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }
}
