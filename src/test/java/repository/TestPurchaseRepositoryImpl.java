package repository;

import connetct.H2Connector;
import org.example.repository.DAO.PurchaseRepository;
import org.example.repository.PurchaseRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class TestPurchaseRepositoryImpl {
    private Connection connection;
    private PurchaseRepository purchaseRepository;

    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        purchaseRepository = new PurchaseRepositoryImpl(connection);
    }

    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }

    @Test
    public void testExists (){
        int userId = 1;
        int gameId = 3;
        boolean exam = purchaseRepository.exists(userId,gameId);
        Assert.assertTrue(exam);
    }

    @Test
    public void testAdd (){
        int userId = 4;
        int gameId = 4;
        purchaseRepository.add(userId,gameId);

        boolean exam = purchaseRepository.exists(userId,gameId);
        Assert.assertTrue(exam);
    }



}
