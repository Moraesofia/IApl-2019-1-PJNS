package com.github.moraesofia.pjns.files;

import com.github.moraesofia.pjns.entities.Filme;
import com.github.moraesofia.pjns.entities.Pessoa;
import com.github.moraesofia.pjns.entities.Premiacao;
import com.github.moraesofia.pjns.entities.Premio;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class ArquivoJns {

    private List<Pessoa> pessoas = new ArrayList<>();

    private List<Filme> filmes = new ArrayList<>();

    private List<Premiacao> premiacoes = new ArrayList<>();

    private List<Premio> premios = new ArrayList<>();

    @XmlElementWrapper(name = "pessoas")
    @XmlElement(name = "pessoa")
    public List<Pessoa> getPessoas() {
        return pessoas;
    }

    public void setPessoas(List<Pessoa> pessoas) {
        this.pessoas = pessoas;
    }

    @XmlElementWrapper(name = "filmes")
    @XmlElement(name = "filme")
    public List<Filme> getFilmes() {
        return filmes;
    }

    public void setFilmes(List<Filme> filmes) {
        this.filmes = filmes;
    }

    @XmlElementWrapper(name = "premiacoes")
    @XmlElement(name = "premiacao")
    public List<Premiacao> getPremiacoes() {
        return premiacoes;
    }

    public void setPremiacoes(List<Premiacao> premiacoes) {
        this.premiacoes = premiacoes;
    }

    @XmlElementWrapper(name = "premios")
    @XmlElement(name = "premio")
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

        // TODO Deduplicar de alguma outra forma que printe quando tiver duplicados.
        // TODO Checa campos obrigatórios
        // TODO Checa chaves estrangeiras
    }
}
