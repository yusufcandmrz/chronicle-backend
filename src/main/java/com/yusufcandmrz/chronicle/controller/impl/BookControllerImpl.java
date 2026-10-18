package com.yusufcandmrz.chronicle.controller.impl;

import com.yusufcandmrz.chronicle.controller.BookController;
import com.yusufcandmrz.chronicle.dto.CreateBookRequest;
import com.yusufcandmrz.chronicle.dto.PageResponse;
import com.yusufcandmrz.chronicle.dto.ReadBookResponse;
import com.yusufcandmrz.chronicle.dto.UpdateBookRequest;
import com.yusufcandmrz.chronicle.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chronicle/book")
@RequiredArgsConstructor
public class BookControllerImpl implements BookController {

    private final BookService bookService;

    @Override
    @PostMapping()
    public void createBook(@Valid @RequestBody CreateBookRequest request) {
        bookService.createBook(request);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReadBookResponse> readBookById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.readBookById(id));
    }

    @Override
    @GetMapping()
    public ResponseEntity<PageResponse<ReadBookResponse>> readAllBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String publisher,
            @RequestParam(required = false) String translator,
            @PageableDefault(size = 20) Pageable pageable) {
        return ResponseEntity.ok(bookService.readAllBooks(title, author, publisher, translator, pageable));
    }

    @Override
    @PutMapping("/{id}")
    public void updateBookById(@PathVariable Long id, @Valid @RequestBody UpdateBookRequest request) {
        bookService.updateBookById(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }
}
