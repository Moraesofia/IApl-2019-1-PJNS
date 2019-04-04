package com.github.moraesofia.pjns.database;

import com.github.moraesofia.pjns.database.connection.DatabaseConnection;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.enums.CargoEnum;
import com.github.moraesofia.pjns.entities.enums.GeneroEnum;

import java.io.IOException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PessoasRepository {

    public List<Pessoa> getAll() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        final List<Pessoa> pessoas = new ArrayList<>();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,nome,cargo," +
                    "nascimento,genero " +
                    "FROM Pessoa");
            final ResultSet r = s.executeQuery();
            while (r.next()) {
                final Integer id = (Integer) r.getObject("id");
                final String nome = (String) r.getObject("nome");
                final String cargo = (String) r.getObject("cargo");

                final LocalDate nasc = ((Date) r.getObject("nascimento")).toLocalDate();
                String nascAux = nasc.toString();
                nascAux = nascAux.replace("-", "");

                final Integer nascimento = Integer.valueOf(nascAux);
                final String genero = (String) r.getObject("genero");

                final Pessoa pessoa = new Pessoa();
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setCargo(CargoEnum.fromText(cargo));
                pessoa.setNascimento(nascimento);
                pessoa.setGenero(GeneroEnum.fromText(genero));
                pessoas.add(pessoa);
            }
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return pessoas;
    }

    public boolean add(final Pessoa pessoa) throws ClassNotFoundException, SQLException,
            InstantiationException,
            IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO Pessoa(id,nome,cargo,nascimento,genero) " +
                    "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, pessoa.getId(), Types.INTEGER);
            s.setObject(2, pessoa.getNome(), Types.VARCHAR);
            s.setObject(3, pessoa.getCargo(), Types.VARCHAR);

            String data = Integer.toString(pessoa.getNascimento());
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date date = sdf.parse(data);
            java.sql.Date birthdate = new java.sql.Date(date.getTime());

            s.setObject(4, birthdate, Types.DATE);
            s.setObject(5, pessoa.getGenero(), Types.VARCHAR);
            s.executeUpdate();
            return true;
        } catch (final SQLException | ParseException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public boolean add(List<Pessoa> pessoas) throws ClassNotFoundException, SQLException,
            InstantiationException, IOException, IllegalAccessException {
        boolean added = false;
        for (final Pessoa pessoa : pessoas) {
            added |= add(pessoa);
        }
        return added;
    }

    public void deleteAll() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("DELETE FROM Pessoa");
            s.executeUpdate();
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

}
