/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.moraesofia.pjns.files;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;

public class ArquivoJnsParser {

    public static String parse(BufferedReader reader) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            resultStringBuilder.append(line).append("\n");
        }
        return resultStringBuilder.toString();
    }

    public static void save(ArquivoJns arquivo, Path path) {

    }
}
