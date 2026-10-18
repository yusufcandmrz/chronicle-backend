package com.yusufcandmrz.chronicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "movie_person")
@NoArgsConstructor
@AllArgsConstructor
public class MoviePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    @Getter
    @Setter
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @Getter
    @Setter
    private Person person;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private MoviePersonRole moviePersonRole;
}
