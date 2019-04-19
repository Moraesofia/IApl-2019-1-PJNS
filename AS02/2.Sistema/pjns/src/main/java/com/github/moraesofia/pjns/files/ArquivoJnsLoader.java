package com.github.moraesofia.pjns.files;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class ArquivoJnsLoader {

    /**
     * Obtém um objeto ArquivoJns a partir de um arquivo Json.
     *
     * @return ArquivoJns com os dados obtidos do Json lido.
     * @throws FileNotFoundException Caso o arquivo não seja encontrado.
     */
    public ArquivoJns load(File file) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(file));
        return gson.fromJson(reader, ArquivoJns.class);
    }

}
