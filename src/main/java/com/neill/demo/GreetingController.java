package com.neill.demo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GreetingController {
    private final Database scoresDB = new Database();

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();


    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @GetMapping("/scores")
    public ArrayList<HighScore> getScores(@RequestParam(value = "name", defaultValue = "neill") String name) {
        try {
            return scoresDB.getHighScores();
        } catch (SQLException e) {
//            return new Greeting(counter.incrementAndGet(), "database problem");
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/scores")
    public String postNewScore(@RequestParam String username, @RequestParam int score) {
        try {
            scoresDB.insertHiScore(username, score);
            return "ok inserted high score";
        } catch (SQLException e) {
//            return new Greeting(counter.incrementAndGet(), "database problem");
            throw new RuntimeException(e);
        }
    }
}