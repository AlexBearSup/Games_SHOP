package org.example.repository;

import org.example.enums.RepositoryMsg;
import org.example.repository.DAO.PurchaseRepository;

import java.sql.*;

public class PurchaseRepositoryImpl implements PurchaseRepository {
    Connection connection;
    private static final String save =
            """
                            INSERT INTO public.users_games(
                            
                            user_ID, game_ID)
                            VALUES (?, ?)
                    """;
    private static final String get =
            """
                            SELECT COUNT(*)
                            FROM public.users_games
                            WHERE user_id = ? AND game_id = ?;
                    """;

    public PurchaseRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void add (int userID, int gameID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(save);
            preparedStatement.setInt(1, userID);
            preparedStatement.setInt(2, gameID);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(RepositoryMsg.NOT_SAVE_PURCHASE.getDescription());
        }
    }
    public boolean exists(int userId, int gameId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(get)) {
            preparedStatement.setInt(1, userId);
            preparedStatement.setInt(2, gameId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count > 0;
            }
        } catch (SQLException e) {
            System.out.println(RepositoryMsg.NOT_GET_PURCHASE.getDescription());
        }
        return false;
    }
}