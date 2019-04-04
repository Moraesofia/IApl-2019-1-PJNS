package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Filme;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmesRepository {

    public List<Filme> getAll() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        final List<Filme> filmes = new ArrayList<>();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,titulo,ano,genero," +
                    "id_diretor, " +
                    "id_ator,id_atriz FROM Filme");
            final ResultSet r = s.executeQuery();
            while (r.next()) {
                final Integer id = (Integer) r.getObject("id");
                final String titulo = (String) r.getObject("titulo");
                final String genero = (String) r.getObject("genero");
                final Integer idDiretor = (Integer) r.getObject("id_diretor");
                final Integer idAtor = (Integer) r.getObject("id_ator");
                final Integer idAtriz = (Integer) r.getObject("id_atriz");

                final Filme filme = new Filme();
                filme.setId(id);
                filme.setTitulo(titulo);
                filme.setGenero(genero);
                filme.setIdDiretor(idDiretor);
                filme.setIdAtor(idAtor);
                filme.setIdAtriz(idAtriz);
                filmes.add(filme);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return filmes;
    }

    public boolean add(final Filme filme) throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO Filme(id,titulo,ano,genero,id_diretor," +
                    "id_ator," +
                    "id_atriz), VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, filme.getId(), Types.INTEGER);
            s.setObject(2, filme.getTitulo(), Types.VARCHAR);
            s.setObject(3, filme.getAno(), Types.INTEGER);
            s.setObject(4, filme.getGenero(), Types.VARCHAR);
            s.setObject(5, filme.getIdDiretor(), Types.INTEGER);
            s.setObject(6, filme.getIdAtor(), Types.INTEGER);
            s.setObject(7, filme.getIdAtriz(), Types.INTEGER);
            s.executeUpdate();
            return true;
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public boolean add(List<Filme> filmes) throws ClassNotFoundException, SQLException,
            InstantiationException, IOException, IllegalAccessException {
        boolean added = false;
        for (final Filme filme : filmes) {
            added |= add(filme);
        }
        return added;
    }

    public void deleteAll() {
        // TODO
    }
}
