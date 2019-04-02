/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.github.moraesofia.pjns.files;

import com.github.moraesofia.pjns.entities.Entities;
import java.nio.file.Path;

public class ArquivoJnsParser {

    public static Entities load(Path path) {
        return new Entities();
    }

    public static void save(Entities entities, Path path) {

    }
}
