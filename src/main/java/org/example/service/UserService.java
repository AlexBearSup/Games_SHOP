package org.example.service;
import org.example.model.Game;
import org.example.model.Account;
import org.example.model.User;
import org.example.enums.ServiceMsg;
import org.example.repository.AccountRepositoryImpl;
import org.example.repository.PurchaseRepositoryImpl;
import org.example.repository.UserRepositoryImpl;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class UserService {
    private MenuService menuService;
    private Scanner scanner;
    private UserRepositoryImpl userRepository;
    private AccountRepositoryImpl accountRepository;
    private PurchaseRepositoryImpl purchaseRepository;
    private GameService gameService;

    public UserService(Scanner scanner,
                       UserRepositoryImpl userRepository,
                       AccountRepositoryImpl accountRepository,
                       PurchaseRepositoryImpl purchaseRepository,
                       GameService gameService, MenuService menuService) {
        this.scanner = scanner;
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
        this.purchaseRepository = purchaseRepository;
        this.gameService = gameService;
        this.menuService = menuService;
    }

    public boolean logIn (String login, String password){

        userRepository.get(login, password).ifPresent(user -> {
            System.out.println(
                    ServiceMsg.GREETINGS.getDescription() + "\n" +
                            ServiceMsg.SEPARATION.getDescription() + "\n" +
                            user.getName()
            );
        });
        if (userRepository.get(login,password).isEmpty()) {
            System.out.println(ServiceMsg.USER_NF.getDescription());
            return false;
        }
        return true;
    }

    public boolean create(User newUser, Account newAcc) {

        userRepository.create(newUser);
        newAcc.setUserId(newUser.getId());
        accountRepository.create(newAcc);

        System.out.println(ServiceMsg.USER_ADD.getDescription());
        return true;
    }

    public User newUser (){
        return User.builder()
                .name(enteringName())
                .nickname(enteringLogin())
                .birthday(enteringBirthDate())
                .password(enteringPass())
                .build();
    }
    public Account newAccount (User user){
        return Account.builder()
                .type(enteringCard())
                .amount(0)
                .userId(user.getId())
                .build();
    }




//    public boolean create (){
//        System.out.println(ServiceMsg.CREATE_USER_PARAMS.getDescription());
//
//        User newUser = User.builder()
//                .name(enteringName())
//                .nickname(enteringLogin())
//                .birthday(enteringBirthDate())
//                .password(enteringPass())
//                .build();
//
//        userRepository.create(newUser);
//
//        Account newAcc = Account.builder()
//                .type(enteringCard())
//                .amount(0)
//                .userId(newUser.getId())
//                .build();
//
//        accountRepository.create(newAcc);
//
//        System.out.println(ServiceMsg.USER_ADD.getDescription());
//        return true;
//    }



    public void buyGame (){
        menuService.displayGames();
        System.out.println(ServiceMsg.GAME_BUYING.getDescription());
        Game selected = gameService.getOne(enteringNumber());
        scanner.nextLine();

        String password = enteringPass();

        if (purchaseRepository.exists(getUserId(password), selected.getId())) {
            System.out.println(ServiceMsg.ALREADY_GAME.getDescription());
            return;
        }
        accountRepository.get(getUserId(password)).ifPresent(account -> {
            System.out.println(ServiceMsg.MONEY.getDescription() + "\n"
                    + account.getAmount());

            if (account.getAmount() < selected.getCost()) {
                System.out.println(ServiceMsg.NO_MONEY.getDescription());
            } else {
                int newBalance = account.getAmount() - selected.getCost();

                accountRepository.update(newBalance, getUserId(password));
                purchaseRepository.add(getUserId(password), selected.getId());
                System.out.println(ServiceMsg.PURCHASE_OK.getDescription() + "\n"
                        + ServiceMsg.MONEY.getDescription() + newBalance);
            }
        });
    }
    public int enteringNumber (){
        while (!scanner.hasNextInt()) {
            System.out.println(ServiceMsg.INCORRECT.getDescription());
            scanner.nextLine();
        }
        return scanner.nextInt();
    }

    public int enteringAmount (){
        System.out.println(ServiceMsg.AMOUNT_MONEY.getDescription());
        while (!scanner.hasNextInt()) {
            System.out.println(ServiceMsg.INCORRECT.getDescription());
            scanner.nextLine();
        }
        return scanner.nextInt();
    }



    public String enteringCard (){
        int selectedCard = 0;
        while (selectedCard != 1 && selectedCard != 2) {
            System.out.println(ServiceMsg.CHOICE_CARD.getDescription());
            selectedCard = enteringNumber();
            if (selectedCard != 1 && selectedCard != 2) {
                System.out.println(ServiceMsg.INCORRECT.getDescription());
            }
        }
        return (selectedCard == 1) ? "VISA" : "MasterCard";
    }
    public String enteringPass (){
        String password;
        System.out.println(ServiceMsg.ENTER_PASS.getDescription());
        password = scanner.nextLine().trim();
        return password;
    }
    public String enteringLogin (){
        String login;
        System.out.println(ServiceMsg.ENTER_LOGIN.getDescription());
        login = scanner.nextLine();
        return login;
    }
    public String enteringName (){
        System.out.println(ServiceMsg.ENTER_NAME.getDescription());
        return scanner.nextLine();
    }
    public LocalDate enteringBirthDate (){
        LocalDate newBirthDate = null;
        while (newBirthDate == null) {
            System.out.println(ServiceMsg.ENTER_BIRTHDATE.getDescription());
            try {
                newBirthDate = LocalDate.parse(scanner.nextLine());
            } catch (DateTimeParseException e) {
                System.out.println(ServiceMsg.INCORRECT.getDescription());
            }
        }
        return  newBirthDate;
    }
    public int getUserId(String pass){
        return userRepository.get(pass).map(User::getId).orElse(-1);
    }

}
