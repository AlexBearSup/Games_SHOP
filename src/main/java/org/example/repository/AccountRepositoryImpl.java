package org.example.repository;
import org.example.model.Account;
import org.example.enums.RepositoryMsg;
import org.example.repository.DAO.AccountRepository;
import java.sql.*;
import java.util.Optional;

public class AccountRepositoryImpl implements AccountRepository {
    private final Connection connection;
    private static final String update =
            """
                    UPDATE public.accounts
                    SET amount=?
                    WHERE  user_id = ?;
                                        
            """;
    private static final String select =
            """
                  SELECT * FROM accounts 
                  WHERE user_id = ?
            """;
    private static final String save =
            """
                    INSERT INTO public.accounts(
                    amount, type, user_id)
                    VALUES (?, ?, ?)
            """;
    public AccountRepositoryImpl(Connection connection) {
        this.connection = connection;
    }
    @Override
    public void update(int deposit, int userID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(update)) {
            preparedStatement.setInt(1, deposit);
            preparedStatement.setInt(2, userID);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(RepositoryMsg.NOT_UPDATE_ACC.getDescription());
        }
    }
    @Override
    public Optional<Account> get(int userID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(select)) {
            preparedStatement.setInt(1, userID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                return Optional.of(Account.builder()
                        .id(resultSet.getInt("id"))
                        .amount(resultSet.getInt("amount"))
                        .type(resultSet.getString("type"))
                        .build());
            }
        } catch (SQLException ex) {
            System.out.println(RepositoryMsg.NOT_GET_ACC.getDescription());
        }
        return Optional.empty();
    }
    @Override
    public void create (Account account ) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(save, PreparedStatement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, 0);
            preparedStatement.setString(2, account.getType());
            preparedStatement.setInt(3, account.getUserId());
            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            generatedKeys.next();
            account.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            System.out.println(RepositoryMsg.NOT_SAVE_ACC.getDescription());
        }
    }
}
