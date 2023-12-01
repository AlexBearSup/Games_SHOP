package service;

import connetct.H2Connector;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.PurchaseRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import org.example.service.AccountService;
import org.example.service.GameService;
import org.example.service.MenuService;
import org.example.service.UserService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class TestAccountService {

    private Connection connection;
    private AccountService accountService;
    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        accountService = new AccountService(new AccountRepositoryImpl(connection),
                new UserService(new Scanner(System.in),
                        new UserRepositoryImpl(connection),
                        new AccountRepositoryImpl(connection),
                        new PurchaseRepositoryImpl(connection),
                        new GameService(new GameRepositoryImpl(connection)),
                        new MenuService()));
    }
    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }


    @Test
    public void testAmountMoney(){
        int userId = 1;
        int result = accountService.amountMoney(userId);
        Assert.assertEquals(50,result);
    }

    @Test
    public void testReplenish() throws SQLException {

        UserService userService = new UserService ( new Scanner(System.in),
                new UserRepositoryImpl(connection),
                new AccountRepositoryImpl(connection),
                new PurchaseRepositoryImpl(connection),
                new GameService(new GameRepositoryImpl(connection)),
                new MenuService());

        String pass = "123";
        int money = 123;

        int expect = accountService.amountMoney(userService.getUserId(pass)) + money;

        Assert.assertEquals(expect,accountService.replenish(pass,money));




    }





}
