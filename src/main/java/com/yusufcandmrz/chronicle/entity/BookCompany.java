package com.yusufcandmrz.chronicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "book_company")
@NoArgsConstructor
@AllArgsConstructor
public class BookCompany {

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
    @JoinColumn(name = "company_id")
    @Getter
    @Setter
    private Company company;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private BookCompanyRole bookCompanyRole;
}
