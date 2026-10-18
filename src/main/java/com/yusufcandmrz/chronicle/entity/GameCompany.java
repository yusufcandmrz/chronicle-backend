package com.yusufcandmrz.chronicle.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "game_company")
@NoArgsConstructor
@AllArgsConstructor
public class GameCompany {

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
    @JoinColumn(name = "company_id")
    @Getter
    @Setter
    private Company company;

    @Enumerated(EnumType.STRING)
    @Getter
    @Setter
    private GameCompanyRole gameCompanyRole;
}
