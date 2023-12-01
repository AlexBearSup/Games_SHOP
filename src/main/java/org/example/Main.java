package org.example;

import org.example.service.MenuService;

import java.sql.SQLException;

public class Main {
public static void main(String[] args) throws SQLException {

        MenuService menuService = new MenuService();

        menuService.displayStartMenu();

    }
}