package repository;

import connetct.H2Connector;
import org.example.model.Account;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.DAO.AccountRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Optional;

public class TestAccountRepositoryImpl {
    private Connection connection;
    private AccountRepository accountRepository;
    private final Account account = Account.builder()
            .type("VISA")
            .userId(5)
            .build();

    @Before
    public void initConnect() throws ClassNotFoundException, SQLException, IOException {
        connection = H2Connector.get();
        accountRepository = new AccountRepositoryImpl(connection);
    }

    @After
    public void closeConnect() throws SQLException {
        connection.close();
    }

    @Test
    public void testSave() {


        accountRepository.create(account);

        Account getAcc = accountRepository.get(account.getId()).get();

        Assert.assertEquals(account.getType(), getAcc.getType());

    }

    @Test
    public void testUpdate() {

        accountRepository.create(account);
        int accountId = account.getId();
        int deposit = 50;

        accountRepository.update(deposit, accountId);
        Account getAcc = accountRepository.get(accountId).get();
        Assert.assertEquals(deposit, getAcc.getAmount());

    }
}

















