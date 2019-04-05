package com.github.moraesofia.pjns;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.ui.Menu;

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
            // Verifica se o servidor do banco está disponível
            HttpURLConnection connection = null;
            connection = (HttpURLConnection) new URL("https://remotemysql.com/phpmyadmin").openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("Servidor do banco de dados disponível.");
            } else {
                throw new IOException(connection.getResponseMessage());
            }
        } catch (IOException e) {
            System.err.println("Erro ao acessar o servidor do banco de dados:");
            e.printStackTrace();
            return false;
        }

        try {
            // Tenta estabelecer a conexão com o banco
            DatabaseConnection.connect();
            System.out.println("Conexão estabelecida com o banco de dados.");
        } catch (ClassNotFoundException | IOException | SQLException | IllegalAccessException
                | InstantiationException e) {
            System.err.println("Erro ao acessar o servidor do banco de dados:");
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
