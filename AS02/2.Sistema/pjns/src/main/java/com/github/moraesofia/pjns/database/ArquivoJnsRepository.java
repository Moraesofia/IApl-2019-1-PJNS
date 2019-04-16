package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.files.ArquivoJns;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;

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
            InstantiationException, IOException, IllegalAccessException, ParseException {

        // Todas as operações são feitas na mesma transação, de modo que caso algo ocorra errado,
        // todas as operações sejam desfeitas. Para isso, é necessário parar os auto-commits da
        // conexão e apenas commitar no final, após inserir todas as operações a serem realizadas.
        Connection connection = DatabaseConnection.connect();
        connection.setAutoCommit(false);

        PessoasRepository pessoas = new PessoasRepository(connection);
        PremiacoesRepository premiacoes = new PremiacoesRepository(connection);
        PremiosRepository premios = new PremiosRepository(connection);
        FilmesRepository filmes = new FilmesRepository(connection);

        try {
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

            // Commita as operações no banco
            connection.commit();
        } catch (Exception ex) {
            connection.rollback();
            throw ex;
        } finally {
            connection.setAutoCommit(true);
            connection.close();
        }
    }

}
