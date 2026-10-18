package com.yusufcandmrz.chronicle.repository;

import com.yusufcandmrz.chronicle.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Optional<Book> findByIdAndAppUserId(Long id, Long currentUserId);

    Page<Book> findAllByAppUserId(Long currentUserId, Pageable pageable);
}
