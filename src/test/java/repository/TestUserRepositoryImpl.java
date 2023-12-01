package repository;

import connetct.H2Connector;
import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.DAO.AccountRepository;
import org.example.repository.DAO.UserRepository;
import org.example.repository.UserRepositoryImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Optional;

import static org.junit.Assert.assertEquals;

public class TestUserRepositoryImpl {

    private Connection connection;
    private UserRepository userRepository;



    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        userRepository = new UserRepositoryImpl(connection);
    }



    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }



    @Test
    public void testCreate (){
        User testUser = new User();
        testUser.setName("Alec");
        testUser.setNickname("bear");
        testUser.setBirthday(LocalDate.of(1990, 1, 1));
        testUser.setPassword("pas123");

        userRepository.create(testUser);
        testUser.getId();

        User getUser = userRepository.get("pas123").get();

        assertEquals("Alec", getUser.getName());
        assertEquals("bear", getUser.getNickname());
        assertEquals(LocalDate.of(1990, 1, 1), getUser.getBirthday());
        assertEquals("pas123", getUser.getPassword());

    }

    @Test
    public void testGet (){
        User expectUser = userRepository.get("alexis","123").get();

        User testUser = new User();
        testUser.setId(1);
        testUser.setName("Alex");
        testUser.setNickname("alexis");
        testUser.setBirthday(LocalDate.of(2000, 1, 1));
        testUser.setPassword("123");

        Assert.assertEquals(expectUser,testUser);


    }







}
