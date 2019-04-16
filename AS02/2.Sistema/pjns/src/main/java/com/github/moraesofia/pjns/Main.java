package com.github.moraesofia.pjns;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.ui.Menu;

import java.io.IOException;
import java.sql.SQLException;

public class Main {

    private static Menu menu;

    public static void main(String[] args) {
        if (!establishConnection())
            System.exit(1);

        menu = new Menu();
        menu.show();
    }

    private static boolean establishConnection() {
        try {
            DatabaseConnection.connect();
            System.out.println("Conexão estabelecida com o banco de dados.");
            return true;
        } catch (ClassNotFoundException | IOException | SQLException | IllegalAccessException
                | InstantiationException e) {
            System.err.println("Erro ao acessar o servidor do banco de dados:");
            e.printStackTrace();
            return false;
        }
    }

}
