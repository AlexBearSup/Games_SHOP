package org.example.service;
import org.example.ConnectionSingletone;
import org.example.enums.ServiceMsg;
import org.example.model.User;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.GameRepositoryImpl;
import org.example.repository.PurchaseRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import java.sql.SQLException;
import java.util.Scanner;

public class MenuService {
    private Scanner scanner = new Scanner(System.in);
    private AccountRepositoryImpl accountRepository = new AccountRepositoryImpl(ConnectionSingletone.getConnection());
    private GameRepositoryImpl gameRepository = new GameRepositoryImpl(ConnectionSingletone.getConnection());
    private UserRepositoryImpl userRepository = new UserRepositoryImpl(ConnectionSingletone.getConnection());
    private PurchaseRepositoryImpl purchaseRepository = new PurchaseRepositoryImpl(ConnectionSingletone.getConnection());
    private GameService gameService = new GameService(gameRepository);
    private UserService userService;
    private AccountService accountService;

    public MenuService() throws SQLException {
        this.userService = new UserService(scanner, userRepository, accountRepository, purchaseRepository, gameService, this);
        this.accountService = new AccountService(accountRepository, userService);
    }


    public void displayAccMenu() throws SQLException {
        int selection;
        do {
            System.out.println(ServiceMsg.SEPARATION.getDescription());
            accountMenu();

            selection = userService.enteringNumber();

            scanner.nextLine();
            switch (selection) {
                case 1 -> displayGames();
                case 2 -> {
                    System.out.println(ServiceMsg.TO_REFILL.getDescription());
                    accountService.replenish(userService.enteringPass(), userService.enteringAmount());
                }
                case 3 -> userService.buyGame();
                case 4 -> displayStartMenu();
                case 5 -> {
                    System.out.println(ServiceMsg.EXIT_APP.getDescription());
                    System.exit(0);
                }
            }
        } while (selection != 5);
        scanner.close();
        ConnectionSingletone.getConnection().close();
    }

    public void displayStartMenu() throws SQLException {
        int choice;
        do {
            startMenu();
            choice = userService.enteringNumber();
            scanner.nextLine();
            switch (choice) {
                case 1 -> {
                    if(!userService.logIn(userService.enteringLogin(), userService.enteringPass())){
                        continue;
                    }
                    displayAccMenu();
                }
                case 2 -> {
                    System.out.println(ServiceMsg.CREATE_USER_PARAMS.getDescription());
                    User newUser = userService.newUser();
                    userService.create(newUser,userService.newAccount(newUser));
                    displayAccMenu();
                }
                case 3 -> {
                    System.out.println(ServiceMsg.EXIT_APP.getDescription());
                    System.exit(0);
                }
                default -> System.out.println(ServiceMsg.INCORRECT.getDescription());
            }
        } while (choice != 3);
        scanner.close();
        ConnectionSingletone.getConnection().close();
    }
    public void startMenu(){
        System.out.println(ServiceMsg.HEADER_STARTMENU.getDescription()
                + "\nPlease enter in your account or create new"
                + "\n1." + ServiceMsg.LOGIN.getDescription()
                + "\n2." + ServiceMsg.CREATE_USER.getDescription()
                + "\n3." + ServiceMsg.EXIT.getDescription()
                + "\n" + ServiceMsg.SEPARATION.getDescription()
                + "\n" + ServiceMsg.SELECT.getDescription());
    }
    public void accountMenu(){
        System.out.println(ServiceMsg.ACCOUNT_MENU.getDescription()
                + "\n1." + ServiceMsg.VIEW_GAMES.getDescription()
                + "\n2." + ServiceMsg.DEPOSIT.getDescription()
                + "\n3." + ServiceMsg.BUY_GAME.getDescription()
                + "\n4." + ServiceMsg.TO_HOME.getDescription()
                + "\n5." + ServiceMsg.EXIT.getDescription()
                + "\n" + ServiceMsg.SEPARATION.getDescription()
                + "\n" + ServiceMsg.SELECT.getDescription());
    }
    public void displayGames (){
        System.out.println(ServiceMsg.LIST_GAMES.getDescription());
        gameService.getAll().
                forEach(g -> System.out.println(g.getId() + ". "
                        + g.getName() + " -  cost: "
                        + g.getCost() + "$ - "
                        + g.getRating() + " points - "
                        + g.getDescription()));
    }

}
