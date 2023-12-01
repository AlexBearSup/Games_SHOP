package org.example.enums;

public enum RepositoryMsg {


    NOT_UPDATE_ACC("Account not updated !!!"),
    NOT_SAVE_ACC("New entry (account) not added !!!"),
    NOT_GET_ACC("Account not taken from the database !!!"),
    NOT_GET_GAME("Games not taken from the database !!!"),
    NOT_SAVE_PURCHASE("Game purchase is not saved to the database !!!"),
    NOT_GET_PURCHASE("Could not get data about purchased games !!!"),
    NOT_SAVE_USER("New user not added !!!"),
    NOT_GET_USER("USer not taken from the database !!!");
    private final String description;

    RepositoryMsg(String description) {
        this.description = description;
    }
    public String getDescription() {
        return description;
    }
}
