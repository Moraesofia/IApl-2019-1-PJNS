package com.github.moraesofia.pjns.files.registros;

import com.github.moraesofia.pjns.files.enums.TipoRegistroEnum;

public class Registro {
    private int tamanho;
    private int numeroLote;
    private TipoRegistroEnum tipoRegistro;

    public int getTamanho() {
        return tamanho;
    }

    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    public int getNumeroLote() {
        return numeroLote;
    }

    public void setNumeroLote(int numeroLote) {
        this.numeroLote = numeroLote;
    }

    public TipoRegistroEnum getTipoRegistro() {
        return tipoRegistro;
    }

    public void setTipoRegistro(TipoRegistroEnum tipoRegistro) {
        this.tipoRegistro = tipoRegistro;
    }
}
