package com.github.moraesofia.pjns.files;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.GsonBuilder;

public class ArquivoJnsSaver {

    /**
     * Salva um Json com todos os dados de um objeto ArquivoJns.
     *
     * @param dados ArquivoJns com os dados a serem salvos.
     * @param file File no qual será salvo o json gerado.
     * @throws IOException
     */
    public void save(ArquivoJns dados, File file) {

        try {

            FileWriter fw = new FileWriter(file);
            new GsonBuilder().setPrettyPrinting().create().toJson(dados, fw);
            fw.flush();
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
