package org.example.repository;

import org.example.model.Game;
import org.example.enums.RepositoryMsg;
import org.example.repository.DAO.GameReposytory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameRepositoryImpl implements GameReposytory {
    private final Connection connection;
    public GameRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    private static final String pull = "SELECT id, name, release_date, rating, cost, description FROM games";

    @Override
    public Optional<List<Game>> getAll (){
        List<Game> all = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(pull);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                Game game = Game.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .releaseDate(resultSet.getDate("release_date").toLocalDate())
                        .rating(resultSet.getInt("rating"))
                        .cost(resultSet.getInt("cost"))
                        .description(resultSet.getString("description"))
                        .build();
                all.add(game);
            }
        } catch (SQLException e) {
            System.out.println(RepositoryMsg.NOT_GET_GAME.getDescription());
            return Optional.empty();
        }
        return Optional.of(all);
    }
}
