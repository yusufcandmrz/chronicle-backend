package com.yusufcandmrz.chronicle.controller.impl;

import com.yusufcandmrz.chronicle.controller.GameController;
import com.yusufcandmrz.chronicle.dto.CreateGameRequest;
import com.yusufcandmrz.chronicle.dto.ReadGameResponse;
import com.yusufcandmrz.chronicle.dto.UpdateGameRequest;
import com.yusufcandmrz.chronicle.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/chronicle/game")
@RequiredArgsConstructor
public class GameControllerImpl implements GameController {

    private final GameService gameService;

    @Override
    @PostMapping()
    public void createGame(@RequestBody CreateGameRequest request) {
        gameService.createGame(request);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ReadGameResponse> readGameById(@PathVariable Long id) {
        return ResponseEntity.ok(gameService.readGameById(id));
    }

    @Override
    @GetMapping()
    public ResponseEntity<List<ReadGameResponse>> readAllGames() {
        return ResponseEntity.ok(gameService.readAllGames());
    }

    @Override
    @PutMapping("/{id}")
    public void updateGameById(@PathVariable Long id, @RequestBody UpdateGameRequest request) {
        gameService.updateGameById(id, request);
    }

    @Override
    @DeleteMapping("/{id}")
    public void deleteGameById(@PathVariable Long id) {
        gameService.deleteGameById(id);
    }
}
