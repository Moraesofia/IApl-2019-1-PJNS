package com.github.moraesofia.pjns.ui;

import java.util.Scanner;

/**
 * Menu principal em interface de linha de comando.
 */
public class Menu {

    public void show() {
        System.out.println("Bem-vindo(a) ao Prêmios JNS.");
        showOptions();
    }

    private void showOptions() {
        System.out.println("");
        System.out.println("Escolha a operação:");
        System.out.println(" 1) Inserir arquivo com novos dados");
        System.out.println(" 2) Obter arquivo com dados atuais");
        System.out.println(" 3) Sair");
        System.out.print("Operação: ");
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        if (operation.equals("1")) {
            showInserir();
        } else if (operation.equals("2")) {
            showObter();
        } else if (operation.equals("3")) {
            showQuit();
        } else {
            System.out.println("");
            System.err.println("Opção inválida.");
            showOptions();
        }
    }

    private void showInserir() {
        System.out.println("");
        System.out.println("Dados inseridos com sucesso.");
        showOptions();
    }

    private void showObter() {
        System.out.println("");
        System.out.println("Dados obtidos com sucesso.");
        showOptions();
    }

    private void showQuit() {
        System.out.println("");
        System.out.println("Encerrando o Prêmios JNS...");
    }
}
