package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Premiacao;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremiacoesRepository {

    public List<Premiacao> getAll() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        final List<Premiacao> premiacoes = new ArrayList<>();
        try {
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

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return premiacoes;
    }

    public boolean add(final Premiacao premiacao) throws ClassNotFoundException,
            SQLException, InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO Premiacao(id,nome,ano), VALUES (?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, premiacao.getId(), Types.INTEGER);
            s.setObject(2, premiacao.getNome(), Types.VARCHAR);
            s.setObject(3, premiacao.getAno(), Types.INTEGER);
            s.executeUpdate();
            return true;
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public boolean add(List<Premiacao> premiacoes) throws ClassNotFoundException,
            SQLException, InstantiationException, IOException, IllegalAccessException {
        boolean added = false;
        for (final Premiacao premiacao : premiacoes) {
            added |= add(premiacao);
        }
        return added;
    }

    public void deleteAll() {
        // TODO
    }

}
