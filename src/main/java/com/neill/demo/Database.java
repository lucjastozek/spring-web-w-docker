package com.neill.demo;

import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static final String DATABASE_URL = getEnvVarOrFail("DATABASE_URL");//"jdbc:postgresql://localhost:5432/demos";
    private Connection connection;

    public Database() {
        connect();
    }

    public void connect() {
        try {
            if (!DATABASE_URL.startsWith("jdbc:")){
                System.out.println("WARNING: DATABASE_URL doesn't start with 'jdbc:'.  That's probably a mistake.");
            }
            connection = DriverManager.getConnection(DATABASE_URL);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("DB ERROR on connect: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public ArrayList<HighScore> getHighScores() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM hiscores order by score desc limit 1000");
        return makeHighScoresFromResultSet(resultSet);
    }


    private static ArrayList<HighScore> makeHighScoresFromResultSet(ResultSet resultSet) throws SQLException {
        ArrayList<HighScore> scores = new ArrayList<>();
        while (resultSet.next()) {
            int score = resultSet.getInt("score");
            String name = resultSet.getString("username");
            scores.add(new HighScore(score, name));
        }
        return scores;
    }

    public void insertHiScore(String personName, int score) throws SQLException {
        String query = "INSERT INTO hiscores (username, score) VALUES (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);

        // Set the values for the placeholders
        preparedStatement.setString(1, personName);
        preparedStatement.setInt(2, score);

        // Execute the statement
        preparedStatement.executeUpdate();
    }



    private static String getEnvVarOrFail(String key) {
        String result = System.getenv(key);
        if (result != null) {
            return result;
        } else {
            throw new RuntimeException("Missing required env var: " + key);
        }
    }
}