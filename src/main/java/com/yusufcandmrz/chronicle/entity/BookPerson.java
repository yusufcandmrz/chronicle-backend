package com.yusufcandmrz.chronicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_person")
@NoArgsConstructor
@AllArgsConstructor
public class BookPerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "book_id")
    @Getter
    @Setter
    private Book book;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @Getter
    @Setter
    private Person person;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private BookPersonRole bookPersonRole;
}
