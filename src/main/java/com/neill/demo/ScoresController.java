package com.neill.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
public class ScoresController {
    private final Database scoresDB = new Database();



    @GetMapping("/scores")
    public ArrayList<HighScore> getScores() {
        try {
            return scoresDB.getHighScores();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/scores")
    public String postNewScore(@RequestParam String username, @RequestParam int score) {
        try {
            scoresDB.insertHiScore(username, score);
            return "ok inserted high score";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}