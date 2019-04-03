package com.github.moraesofia.pjns.files;

import com.github.moraesofia.pjns.entities.Filme;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.Premiacao;
import com.github.moraesofia.pjns.entities.Premio;

import com.github.moraesofia.pjns.database.DatabaseConnection;
import com.github.moraesofia.pjns.entities.enums.CargoEnum;
import com.github.moraesofia.pjns.entities.enums.CategoriaEnum;
import com.github.moraesofia.pjns.entities.enums.GeneroEnum;

import java.sql.*;

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

    public boolean addPessoa(final Pessoa pessoa) throws ClassNotFoundException, SQLException, InstantiationException,
            IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO Pessoa(id,nome,cargo,nascimento,genero) " +
                    "VALUES (?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
            s.setObject(1, pessoa.getId(), Types.INTEGER);
            s.setObject(2, pessoa.getNome(), Types.VARCHAR);
            s.setObject(3, pessoa.getCargo(), Types.VARCHAR);
            s.setObject(4, pessoa.getNascimento(), Types.DATE);
            s.setObject(5, pessoa.getGenero(), Types.VARCHAR);
            s.executeUpdate();
            return true;
        } catch (final SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return false;
    }

    public void setPessoas(List<Pessoa> pessoas) throws ClassNotFoundException, SQLException, InstantiationException,
            IOException, IllegalAccessException {
        this.pessoas = pessoas;
        boolean added = false;
        for (final Pessoa pessoa : pessoas) {
            added |= addPessoa(pessoa);
        }
    }

    public List<Filme> getFilmes() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,titulo,ano,genero,id_diretor, " +
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

    public boolean addFilme(final Filme filme) throws ClassNotFoundException, SQLException, InstantiationException,
            IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            PreparedStatement s;
            s = connection.prepareStatement("INSERT INTO Filme(id,titulo,ano,genero,id_diretor,id_ator,id_atriz)," +
                    "VALUES (?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
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

    public void setFilmes(List<Filme> filmes) throws ClassNotFoundException, SQLException, InstantiationException,
            IOException, IllegalAccessException {
        this.filmes = filmes;
        boolean added = false;
        for (final Filme filme: filmes) {
            added |= addFilme(filme);
        }
    }

    public List<Premiacao> getPremiacoes() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,nome,ano FROM Premiacao");
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

    public boolean addPremiacao(final Premiacao premiacao) throws ClassNotFoundException, SQLException, InstantiationException,
            IllegalAccessException, IOException {
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

    public void setPremiacoes(List<Premiacao> premiacoes) throws ClassNotFoundException, SQLException,
            InstantiationException, IOException, IllegalAccessException {
        this.premiacoes = premiacoes;
        boolean added = false;
        for (final Premiacao premiacao : premiacoes) {
            added |= addPremiacao(premiacao);
        }
    }

    public List<Premio> getPremios() throws ClassNotFoundException, SQLException,
            InstantiationException, IllegalAccessException, IOException {
        final Connection connection = DatabaseConnection.connect();
        try {
            final PreparedStatement s = connection.prepareStatement("SELECT id,categoria,id_premiacao," +
                    "id_vencedor,id_filme FROM Premio");
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
