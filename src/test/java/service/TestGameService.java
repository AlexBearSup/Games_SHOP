package service;

import connetct.H2Connector;
import org.example.model.Account;
import org.example.model.Game;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.DAO.AccountRepository;
import org.example.repository.DAO.GameReposytory;
import org.example.repository.GameRepositoryImpl;
import org.example.service.GameService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class TestGameService {

    private Connection connection;
    private GameService gameService;
    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        gameService = new GameService(new GameRepositoryImpl(connection));
    }
    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }

    @Test
    public void testGetAll () {
        List<Game> allGames = gameService.getAll();
        Assert.assertFalse(allGames.isEmpty());
        Assert.assertEquals(5, allGames.size());

    }

    @Test
    public void testGetOne () {
        Game game = new Game(2, "Game 2", LocalDate.of(2022, 2, 1), 3, 40, "Description of Game 2");
        int choice = 2;
        Game choiseGame = gameService.getOne(choice);
        Assert.assertEquals(game,choiseGame);
    }
}
