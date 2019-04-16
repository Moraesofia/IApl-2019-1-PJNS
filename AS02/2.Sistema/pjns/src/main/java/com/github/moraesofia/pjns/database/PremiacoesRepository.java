package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Premiacao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremiacoesRepository {

    private final Connection connection;

    public PremiacoesRepository() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        this.connection = DatabaseConnection.connect();
    }

    public PremiacoesRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Premiacao> getAll() throws SQLException {
        final List<Premiacao> premiacoes = new ArrayList<>();
        final PreparedStatement s = connection.prepareStatement("SELECT id,nome,ano FROM " +
                "Premiacao");
        final ResultSet r = s.executeQuery();
        while (r.next()) {
            final Integer id = (Integer) r.getObject("id");
            final String nome = (String) r.getObject("nome");
            final Integer ano = (Integer) r.getObject("ano");

            final Premiacao premiacao = new Premiacao();
            premiacao.setId(id);
            premiacao.setNome(nome);
            premiacao.setAno(ano);
            premiacoes.add(premiacao);
        }
        return premiacoes;
    }

    public void add(final Premiacao premiacao) throws SQLException {
        PreparedStatement s;
        s = connection.prepareStatement("INSERT INTO Premiacao(id,nome,ano) VALUES (?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        s.setObject(1, premiacao.getId(), Types.INTEGER);
        s.setObject(2, premiacao.getNome(), Types.VARCHAR);
        s.setObject(3, premiacao.getAno(), Types.INTEGER);
        s.executeUpdate();
    }

    public void add(List<Premiacao> premiacoes) throws SQLException {
        for (final Premiacao premiacao : premiacoes) {
            add(premiacao);
        }
    }

    public void deleteAll() throws SQLException {
        PreparedStatement s;
        s = connection.prepareStatement("DELETE FROM Premiacao");
        s.executeUpdate();
    }

}
