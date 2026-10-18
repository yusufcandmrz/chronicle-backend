package com.yusufcandmrz.chronicle.service;

import com.yusufcandmrz.chronicle.dto.CreateGameRequest;
import com.yusufcandmrz.chronicle.dto.ReadGameResponse;
import com.yusufcandmrz.chronicle.dto.UpdateGameRequest;

import java.util.List;

public interface GameService {

    void createGame(CreateGameRequest request);

    ReadGameResponse readGameById(Long id);

    List<ReadGameResponse> readAllGames();

    void updateGameById(Long id, UpdateGameRequest request);

    void deleteGameById(Long id);
}
