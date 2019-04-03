package com.github.moraesofia.pjns.files;

import com.github.moraesofia.pjns.entities.Filme;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.Premiacao;
import com.github.moraesofia.pjns.entities.Premio;

import com.github.moraesofia.pjns.database.DatabaseConnection;
import com.github.moraesofia.pjns.entities.enums.CargoEnum;
import com.github.moraesofia.pjns.entities.enums.GeneroEnum;

import java.sql.Date;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.io.IOException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ArquivoJns {

    private List<Pessoa> pessoas = new ArrayList<>();

    private List<Filme> filmes = new ArrayList<>();

    private List<Premiacao> premiacoes = new ArrayList<>();

    private List<Premio> premios = new ArrayList<>();

    public List<Pessoa> getPessoas() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,nome,cargo,nascimento,genero " +
                    "FROM pessoa");
            final ResultSet r = s.executeQuery();
            while (r.next()) {
                final Integer id = (Integer) r.getObject("id");
                final String nome = (String) r.getObject("nome");
                final String cargo = (String) r.getObject("cargo");
                final Integer nascimento = (Integer) r.getObject("nascimento");
                final String genero = (String) r.getObject("genero");

                final Pessoa pessoa = new Pessoa();
                pessoa.setId(id);
                pessoa.setNome(nome);
                pessoa.setCargo(CargoEnum.valueOf(cargo));
                pessoa.setNascimento(nascimento);
                pessoa.setGenero(GeneroEnum.valueOf(genero));
                pessoas.add(pessoa);
            }
        } catch (SQLException sqlE) {
            sqlE.printStackTrace();
        }
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    public List<Premiacao> getPremiacoes() {
        return premiacoes;
    }

    public void setPremiacoes(List<Premiacao> premiacoes) {
        this.premiacoes = premiacoes;
    }

    public List<Premio> getPremios() {
        return premios;
    }

    public void setPremios(List<Premio> premios) {
        this.premios = premios;
    }

    /**
     * Remove entidades inválidas (IDs duplicados, campos obrigatórios vazios,
     * chaves estrangeiras apontando pra IDs inexistentes etc).
     */
    public void validate(boolean printProblems) {
        // Remove entidades com IDs duplicados. Só funciona porque elas tiveram
        // o "equals()" modificado pra comparar com base no ID.
        pessoas = pessoas.stream().distinct().collect(Collectors.toList());
        filmes = filmes.stream().distinct().collect(Collectors.toList());
        premiacoes = premiacoes.stream().distinct().collect(Collectors.toList());
        premios = premios.stream().distinct().collect(Collectors.toList());

        // TODO Checa campos obrigatórios

        // TODO Checa chaves estrangeiras
    }

    private boolean hasPessoaWithSameId(Pessoa other) {
        for (Pessoa pessoa : pessoas) {
            if (pessoa != other && pessoa.getId() == other.getId())
                return true;
        }
        return false;
    }
}
