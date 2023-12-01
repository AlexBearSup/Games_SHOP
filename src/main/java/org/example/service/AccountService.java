package org.example.service;
import org.example.model.Account;
import org.example.enums.ServiceMsg;
import org.example.repository.AccountRepositoryImpl;
public class AccountService {
    private AccountRepositoryImpl accountRepository;
    private UserService userService;

    public AccountService(AccountRepositoryImpl accountRepository,
                          UserService userService) {
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public int amountMoney (int userId){
        return accountRepository.get(userId)
                .map(Account::getAmount)
                .orElseGet(() -> {
                    System.out.println(ServiceMsg.ACC_NF.getDescription());
                    return -1;
                });
    }
    public int replenish (String password, int money){

        int userId = userService.getUserId(password);

        if (amountMoney(userId) == -1){
            System.out.println(ServiceMsg.ACC_NF.getDescription());;
        } else {

            int deposit = money + amountMoney(userId);

            accountRepository.update(deposit, userId);

            System.out.println(ServiceMsg.MONEY.getDescription() +"\n"
                    + amountMoney(userId));
        }
        return amountMoney(userId);
    }
}