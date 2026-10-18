package com.yusufcandmrz.chronicle.controller;

import com.yusufcandmrz.chronicle.dto.CreateGameRequest;
import com.yusufcandmrz.chronicle.dto.ReadGameResponse;
import com.yusufcandmrz.chronicle.dto.UpdateGameRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GameController {

    void createGame(CreateGameRequest request);

    ResponseEntity<ReadGameResponse> readGameById(Long id);

    ResponseEntity<List<ReadGameResponse>> readAllGames();

    void updateGameById(Long id, UpdateGameRequest request);

    void deleteGameById(Long id);
}
