package com.yusufcandmrz.chronicle.service.impl;

import com.yusufcandmrz.chronicle.dto.CreateGameRequest;
import com.yusufcandmrz.chronicle.dto.ReadGameResponse;
import com.yusufcandmrz.chronicle.dto.UpdateGameRequest;
import com.yusufcandmrz.chronicle.service.GameService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    @Override
    public void createGame(CreateGameRequest request) {

    }

    @Override
    public ReadGameResponse readGameById(Long id) {
        return null;
    }

    @Override
    public List<ReadGameResponse> readAllGames() {
        return List.of();
    }

    @Override
    public void updateGameById(Long id, UpdateGameRequest request) {

    }

    @Override
    public void deleteGameById(Long id) {

    }
}
