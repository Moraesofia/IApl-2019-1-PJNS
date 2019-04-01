package com.github.moraesofia.pjns;

import java.net.HttpURLConnection;
import java.net.URL;

public class Main {

    public static void main(String[] args) {

        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(
                    "https://remotemysql.com/phpmyadmin").openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {
                System.out.println("Servidor Disponível");
            }

            String expectedStatus = "Connection established";
            Conexao.getConnection();

            if (Conexao.status.equals(expectedStatus)) {
                System.out.println("Conexão Estabelecida");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
