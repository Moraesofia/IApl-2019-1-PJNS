package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Premio;
import com.github.moraesofia.pjns.entities.enums.CategoriaEnum;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremiosRepository {

    public List<Premio> getAll() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        final List<Premio> premios = new ArrayList<>();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,categoria," +
                    "id_premiacao,id_vencedor,id_filme FROM Premio");
            final ResultSet r = s.executeQuery();
            while (r.next()) {
                final Integer id = (Integer) r.getObject("id");
                final String categoria = (String) r.getObject("categoria");
                final Integer idPremiacao = (Integer) r.getObject("id_premiacao");
                final Integer idVencedor = (Integer) r.getObject("id_vencedor");
                final Integer idFilme = (Integer) r.getObject("id_filme");

                final Premio premio = new Premio();
                premio.setId(id);
                premio.setCategoria(CategoriaEnum.fromText(categoria));
                premio.setIdPremiacao(idPremiacao);
                premio.setIdVencedor(idVencedor);
                premio.setIdFilme(idFilme);
                premios.add(premio);
            }

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        return premios;
    }

    public boolean add(Premio premio) throws ClassNotFoundException, SQLException,
            InstantiationException,
            IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO Premio(id,categoria,id_premiacao," +
                            "id_vencedor," +
                            "id_filme), VALUES (?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, premio.getId(), Types.INTEGER);
            s.setObject(2, premio.getCategoria(), Types.VARCHAR);
            s.setObject(3, premio.getIdPremiacao(), Types.INTEGER);
            s.setObject(3, premio.getIdVencedor(), Types.INTEGER);
            s.setObject(3, premio.getIdFilme(), Types.INTEGER);
            s.executeUpdate();
            return true;
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public boolean add(List<Premio> premios) throws ClassNotFoundException, SQLException,
            InstantiationException, IOException, IllegalAccessException {
        boolean added = false;
        for (final Premio premio : premios) {
            added |= add(premio);
        }
        return added;
    }

    public void deleteAll() {
        // TODO
    }

}
