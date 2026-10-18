package com.yusufcandmrz.chronicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game_person")
@NoArgsConstructor
@AllArgsConstructor
public class GamePerson {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @ManyToOne
    @JoinColumn(name = "game_id")
    @Getter
    @Setter
    private Game game;

    @ManyToOne
    @JoinColumn(name = "person_id")
    @Getter
    @Setter
    private Person person;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private GamePersonRole gamePersonRole;
}
