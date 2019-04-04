package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.files.ArquivoJns;

import java.io.IOException;
import java.sql.SQLException;

public class ArquivoJnsRepository {

    /**
     * Obtém um ArquivoJns com todos os dados do banco.
     */
    public ArquivoJns get() throws ClassNotFoundException, SQLException, InstantiationException,
            IOException, IllegalAccessException {
        ArquivoJns jns = new ArquivoJns();
        jns.setPessoas(new PessoasRepository().getAll());
        jns.setPremiacoes(new PremiacoesRepository().getAll());
        jns.setPremios(new PremiosRepository().getAll());
        jns.setFilmes(new FilmesRepository().getAll());
        return jns;
    }

    /**
     * Deleta todos os dados do banco e adiciona os dados do arquivo.
     */
    public void set(ArquivoJns jns) throws ClassNotFoundException, SQLException,
            InstantiationException,
            IOException, IllegalAccessException {

        PessoasRepository pessoas = new PessoasRepository();
        PremiacoesRepository premiacoes = new PremiacoesRepository();
        PremiosRepository premios = new PremiosRepository();
        FilmesRepository filmes = new FilmesRepository();

        // Deleta dados antigos
        premios.deleteAll();
        filmes.deleteAll();
        pessoas.deleteAll();
        premiacoes.deleteAll();

        // Insere dados do arquivo
        premiacoes.add(jns.getPremiacoes());
        pessoas.add(jns.getPessoas());
        filmes.add(jns.getFilmes());
        premios.add(jns.getPremios());
    }

}
