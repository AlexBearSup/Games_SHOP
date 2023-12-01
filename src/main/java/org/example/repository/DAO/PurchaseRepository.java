package org.example.repository.DAO;

public interface PurchaseRepository {
    void add(int userID, int gameID);
    boolean exists (int userId, int gameId);
}
