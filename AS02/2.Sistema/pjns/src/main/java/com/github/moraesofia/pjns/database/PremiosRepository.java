package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Premio;
import com.github.moraesofia.pjns.entities.enums.CategoriaEnum;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PremiosRepository {

    private final Connection connection;

    public PremiosRepository() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        this.connection = DatabaseConnection.connect();
    }

    public PremiosRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Premio> getAll() throws SQLException {
        final List<Premio> premios = new ArrayList<>();
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
        return premios;
    }

    public void add(Premio premio) throws SQLException {
        PreparedStatement s;
        s = connection.prepareStatement("INSERT INTO Premio(id,categoria,id_premiacao," +
                        "id_vencedor,id_filme) VALUES (?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        s.setObject(1, premio.getId(), Types.INTEGER);
        s.setObject(2, premio.getCategoria().getText(), Types.VARCHAR);
        s.setObject(3, premio.getIdPremiacao(), Types.INTEGER);
        s.setObject(4, premio.getIdVencedor(), Types.INTEGER);
        s.setObject(5, premio.getIdFilme(), Types.INTEGER);
        s.executeUpdate();
    }

    public void add(List<Premio> premios) throws SQLException {
        for (final Premio premio : premios) {
            add(premio);
        }
    }

    public void deleteAll() throws SQLException {
        PreparedStatement s;
        s = connection.prepareStatement("DELETE FROM Premio");
        s.executeUpdate();
    }

}
