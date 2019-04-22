package com.github.moraesofia.pjns.entities;

import com.github.moraesofia.pjns.entities.enums.CargoEnum;
import com.github.moraesofia.pjns.entities.enums.GeneroEnum;

/**
 * Created by aluno on 01/04/19.
 */
public class Pessoa {

    private int id;

    private String nome;

    private String nascimento;

    private CargoEnum cargo;

    private GeneroEnum genero;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public CargoEnum getCargo() {
        return cargo;
    }

    public void setCargo(CargoEnum cargo) {
        this.cargo = cargo;
    }

    public GeneroEnum getGenero() {
        return genero;
    }

    public void setGenero(GeneroEnum genero) {
        this.genero = genero;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pessoa pessoa = (Pessoa) o;

        return id == pessoa.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
