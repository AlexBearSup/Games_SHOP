package service;

import connetct.H2Connector;
import org.example.model.Account;
import org.example.model.User;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.PurchaseRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
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
import java.time.LocalDate;
import java.util.Scanner;

public class TestUserService {

    private Connection connection;
    private UserService userService;
    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        userService = new UserService ( new Scanner(System.in),
                new UserRepositoryImpl(connection),
                new AccountRepositoryImpl(connection),
                new PurchaseRepositoryImpl(connection),
                new GameService(new GameRepositoryImpl(connection)),
                new MenuService());
    }
    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }

    @Test
    public void testUserId(){
        String pass = "123";
        int result = userService.getUserId(pass);
        Assert.assertEquals(1,result);
    }

    @Test
    public void testLogIn(){
        String login = "alexis";
        String password = "123";

        boolean result = userService.logIn(login,password);
        Assert.assertTrue(result);

    }

    @Test
    public void testCreate(){

        User testUser = User.builder()
                            .name("alex")
                            .nickname("bear")
                            .birthday(LocalDate.of(2020,2,1))
                            .password("12345")
                            .build();

        Account testAccount = Account.builder()
                                    .type("visa")
                                    .amount(0)
                                    .userId(6)
                                    .build();

        boolean result =  userService.create(testUser,testAccount);

        Assert.assertTrue(result);

    }

}
