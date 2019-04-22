package com.github.moraesofia.pjns.ui;

import com.github.moraesofia.pjns.database.ArquivoJnsRepository;
import com.github.moraesofia.pjns.files.ArquivoJns;
import com.github.moraesofia.pjns.files.ArquivoJnsLoader;
import com.github.moraesofia.pjns.files.ArquivoJnsSaver;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Menu principal em interface de linha de comando.
 */
public class Menu {

    public void show() throws IOException {
        System.out.println();
        System.out.println("Bem-vindo(a) ao Prêmios JNS.");
        showOptions();
    }

    private void showOptions() throws IOException {
        System.out.println();
        System.out.println("Escolha a operação:");
        System.out.println(" 1) Inserir XML com novos dados");
        System.out.println(" 2) Salvar XML com dados atuais");
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

    private void showInserir() throws IOException {
        System.out.println();

        // Seleciona o arquivo.
        System.out.println("Selecione o arquivo com os dados a serem inseridos...");
        FileDialog fileDialog = new FileDialog();
        File file = fileDialog.showOpenFile();
        if (file != null) {
            System.out.println("Selecionado: " + file.getAbsolutePath());
        } else {
            System.out.println("Inserção de dados cancelada.");
            showOptions();
            return;
        }

        // Obtém os dados do o arquivo.
        System.out.println("Lendo dados do arquivo...");
        ArquivoJns dados = null;
        try {
            dados = new ArquivoJnsLoader().load(file);
            System.out.println("Dados lidos.");
        } catch (Exception e) {
            System.err.println("Erro ao ler arquivo de dados.");
            e.printStackTrace();
            System.exit(1);
        }

        // Valida os dados do arquivo.
        System.out.println("Validando dados do arquivo...");
        dados.validate(true);
        System.out.println("Dados validados.");

        // Deleta dados atuais e insere os do arquivo.
        System.out.println("Substituindo dados do banco...");
        try {
            new ArquivoJnsRepository().set(dados);
            System.out.println("Dados inseridos com sucesso.");
        } catch (Exception e) {
            System.err.println("Erro ao substituir dados do banco.");
            e.printStackTrace();
            System.exit(1);
        }

        showOptions();
    }

    private void showSalvar() throws IOException {
        System.out.println();

        // Seleciona o arquivo.
        System.out.println("Selecione o diretório e digite um nome para salvar o arquivo...");
        FileDialog fileDialog = new FileDialog();
        File file = fileDialog.showSaveFile();
        if (file != null) {
            System.out.println("Selecionado: " + file.getAbsolutePath());
        } else {
            System.out.println("Operação cancelada");
            showOptions();
            return;
        }

        // Insere dados no arquivo
        ArquivoJnsSaver save = new ArquivoJnsSaver();
        ArquivoJns jns = new ArquivoJns();
        try {
            System.out.println("Obtendo dados...");
            jns = new ArquivoJnsRepository().get();
        } catch (Exception e) {
            System.err.println("Erro ao gerar arquivo de dados.");
            e.printStackTrace();
            System.exit(1);
        }

        // escreve arquivo
        System.out.println("Gerando arquivo...");
        save.save(jns, file);
        System.out.println("Dados obtidos com sucesso.");
        showOptions();
    }

    private void showQuit() {
        System.out.println();
        System.out.println("Encerrando o Prêmios JNS...");
        System.exit(0);
    }
}
