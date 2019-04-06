package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.enums.CargoEnum;
import com.github.moraesofia.pjns.entities.enums.GeneroEnum;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PessoasRepository {

    private final Connection connection;

    public PessoasRepository() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        this.connection = DatabaseConnection.connect();
    }

    public PessoasRepository(Connection connection) {
        this.connection = connection;
    }

    public List<Pessoa> getAll() throws SQLException {
        final List<Pessoa> pessoas = new ArrayList<>();
        final PreparedStatement s = connection.prepareStatement("SELECT id,nome,cargo," +
                "nascimento,genero " +
                "FROM Pessoa");
        final ResultSet r = s.executeQuery();
        while (r.next()) {
            final Integer id = (Integer) r.getObject("id");
            final String nome = (String) r.getObject("nome");
            final String cargo = (String) r.getObject("cargo");

            final Date nasc = (Date) r.getObject("nascimento");
            final SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            final String nascimento = formatter.format(nasc);
            final String genero = (String) r.getObject("genero");

            final Pessoa pessoa = new Pessoa();
            pessoa.setId(id);
            pessoa.setNome(nome);
            pessoa.setCargo(CargoEnum.fromText(cargo));
            pessoa.setNascimento(nascimento);
            pessoa.setGenero(GeneroEnum.fromText(genero));
            pessoas.add(pessoa);
        }
        return pessoas;
    }

    public void add(final Pessoa pessoa) throws SQLException, ParseException {
        PreparedStatement s;
        s = connection.prepareStatement("INSERT INTO Pessoa(id,nome,cargo,nascimento,genero) " +
                "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        s.setObject(1, pessoa.getId(), Types.INTEGER);
        s.setObject(2, pessoa.getNome(), Types.VARCHAR);
        s.setObject(3, pessoa.getCargo().getText(), Types.VARCHAR);

        String data = pessoa.getNascimento();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        java.util.Date date = sdf.parse(data);
        java.sql.Date birthdate = new java.sql.Date(date.getTime());

        s.setObject(4, birthdate, Types.DATE);
        s.setObject(5, pessoa.getGenero().getText(), Types.VARCHAR);
        s.executeUpdate();
    }

    public void add(List<Pessoa> pessoas) throws SQLException, ParseException {
        for (final Pessoa pessoa : pessoas) {
            add(pessoa);
        }
    }

    public void deleteAll() throws SQLException {
        PreparedStatement s;
        s = connection.prepareStatement("DELETE FROM Pessoa");
        s.executeUpdate();
    }

}
