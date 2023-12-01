package org.example.enums;

public enum ServiceMsg {

    LIST_EMPTY("The list of games is empty !!!"),
    ACC_NF("The account was not found !!!"),
    MONEY ("Money in the account:"),
    NO_MONEY("Not enough money to buy the game:"),
    TO_REFILL("To replenish your account you must pass verification !"),
    CHOICE_CARD("Please choice type card:" + "\n"
                        + "1. VISA" + "\n"
                        + "2. MasterCard"),
    USER_ADD("User added successfully"),
    ENTER_PASS("Please enter the password:"),
    GAME_BUYING("What games do you want buy ?"),
    ENTER_LOGIN("Please enter the login (nickname):"),
    ENTER_BIRTHDATE("Entering the date of birth (in the format YYYY-MM-DD)"),
    ENTER_NAME("Enter your name:"),
    AMOUNT_MONEY("Please enter the amount of money do you want replenish:"),
    ALREADY_GAME("You already own this game !"),
    TOP_UP("How much do you want to top up?"),
    HEADER_STARTMENU("!========HELLO========!"),
    GREETINGS("===Greetings==="),
    USER_NF("User is not found !"),

    INCORRECT ("Incorrect input, try again !"),
    EXIT_APP("!==========GOOD BYE==========!"),
    SEPARATION("-------------------------"),
    LIST_GAMES("List of all games: "),
    PURCHASE_OK("Purchase successful."),
    ACCOUNT_MENU("!========ACCOUNT MENU========!"),
    SELECT ("--Select option--"),
    CREATE_USER ("Create new user"),
    LOGIN ("Sign in"),
    VIEW_GAMES ("Show all games"),
    BUY_GAME ("Buy the game"),
    DEPOSIT ("Refill"),
    EXIT ("Exit"),
    TO_HOME("To home page"),
    CREATE_USER_PARAMS("To create a new user, enter the required parameters:");

    private final String description;

    ServiceMsg (String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }


}
