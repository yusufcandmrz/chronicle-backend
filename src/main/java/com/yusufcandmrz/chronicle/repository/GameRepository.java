package com.yusufcandmrz.chronicle.repository;

import com.yusufcandmrz.chronicle.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
}
