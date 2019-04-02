package com.github.moraesofia.pjns.ui;

import com.github.moraesofia.pjns.files.ArquivoJns;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Menu principal em interface de linha de comando.
 */
public class Menu {

    public void show() {
        System.out.println();
        System.out.println("Bem-vindo(a) ao Prêmios JNS.");
        showOptions();
    }

    private void showOptions() {
        System.out.println();
        System.out.println("Escolha a operação:");
        System.out.println(" 1) Inserir arquivo com novos dados");
        System.out.println(" 2) Salvar arquivo com dados atuais");
        System.out.println(" 3) Sair");
        System.out.print("Operação: ");
        Scanner scanner = new Scanner(System.in);
        String operation = scanner.nextLine();
        if (operation.equals("1")) {
            showInserir();
        } else if (operation.equals("2")) {
            showSalvar();
        } else if (operation.equals("3")) {
            showQuit();
        } else {
            System.err.println("Opção inválida.");
            showOptions();
        }
    }

    private void showInserir() {
        System.out.println();

        // Seleciona o arquivo.
        System.out.println("Selecione o arquivo com os dados a serem inseridos...");
        FileDialog fileDialog = new FileDialog();
        File file = fileDialog.showOpenFile();
        if (file == null) {
            System.out.println("Inserção de dados cancelada.");
            showOptions();
            return;
        }

        // Obtém os dados do o arquivo.
        System.out.println("Lendo dados do arquivo...");
        try (FileReader fileReader = new FileReader(file);
             BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            // TODO Change this!
            ArquivoJns data = null;
        } catch (IOException e) {
            System.err.println("Erro ao inserir dados.");
            e.printStackTrace();
            System.exit(1);
        }

        // TODO Deleta os dados atuais e insere os novos dados.
        // ...

        System.out.println("Dados inseridos com sucesso.");
        showOptions();
    }

    private void showSalvar() {
        System.out.println();

        // TODO

        System.out.println("Dados obtidos com sucesso.");
        showOptions();
    }

    private void showQuit() {
        System.out.println();
        System.out.println("Encerrando o Prêmios JNS...");
    }
}
