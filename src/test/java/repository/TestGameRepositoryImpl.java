package repository;

import connetct.H2Connector;
import org.example.model.Game;
import org.example.repository.DAO.GameReposytory;
import org.example.repository.DAO.PurchaseRepository;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.PurchaseRepositoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TestGameRepositoryImpl {

    private Connection connection;
    private GameReposytory gameReposytory;

    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        gameReposytory = new GameRepositoryImpl(connection);
    }

    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }


    @Test
    public void testGetAll (){

        Game game = new Game(2, "Game 2", LocalDate.of(2022, 2, 1), 3, 40, "Description of Game 2");

        List<Game> allGames = gameReposytory.getAll().get();

        Assert.assertEquals(5,allGames.size());
        Assert.assertEquals(game,allGames.get(1));

    }

}
