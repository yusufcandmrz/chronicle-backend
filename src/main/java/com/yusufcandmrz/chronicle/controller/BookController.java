package com.yusufcandmrz.chronicle.controller;

import com.yusufcandmrz.chronicle.dto.CreateBookRequest;
import com.yusufcandmrz.chronicle.dto.PageResponse;
import com.yusufcandmrz.chronicle.dto.ReadBookResponse;
import com.yusufcandmrz.chronicle.dto.UpdateBookRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface BookController {

    void createBook(CreateBookRequest request);

    ResponseEntity<ReadBookResponse> readBookById(Long id);

    ResponseEntity<PageResponse<ReadBookResponse>> readAllBooks(
            String title, String author, String publisher, String translator, Pageable pageable
    );

    void updateBookById(Long id, UpdateBookRequest request);

    void deleteBookById(Long id);
}
