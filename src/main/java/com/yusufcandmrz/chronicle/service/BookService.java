package com.yusufcandmrz.chronicle.service;

import com.yusufcandmrz.chronicle.dto.CreateBookRequest;
import com.yusufcandmrz.chronicle.dto.PageResponse;
import com.yusufcandmrz.chronicle.dto.ReadBookResponse;
import com.yusufcandmrz.chronicle.dto.UpdateBookRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {

    void createBook(CreateBookRequest request);

    ReadBookResponse readBookById(Long id);

    PageResponse<ReadBookResponse> readAllBooks(
            String title,
            String authorName,
            String publisherName,
            String translatorName,
            Pageable pageable
    );

    void updateBookById(Long id, UpdateBookRequest request);

    void deleteBookById(Long id);
}
