package com.yusufcandmrz.chronicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies")
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Getter
    private Long id;

    @Column(name = "title", nullable = false)
    @Getter
    @Setter
    private String title;

    @Column(name = "release_year", nullable = false)
    @Getter
    @Setter
    private Integer releaseYear;

    @OneToMany(mappedBy = "movie")
    @Getter
    @Setter
    private Set<MoviePerson> moviePeople = new HashSet<>();
}
