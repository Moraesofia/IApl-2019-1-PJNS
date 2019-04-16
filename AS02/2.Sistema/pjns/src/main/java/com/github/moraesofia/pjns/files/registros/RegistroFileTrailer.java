package com.github.moraesofia.pjns.files.registros;

public class RegistroFileTrailer extends Registro {
    private int quantidadeLotes;
    private int quantidadeRegistros;

    public RegistroFileTrailer(Registro registro) {
        super();
        this.setPosicaoInicial(registro.getPosicaoInicial());
        this.setTamanho(registro.getTamanho());
        this.setNumeroLote(registro.getNumeroLote());
        this.setTipoRegistro(registro.getTipoRegistro());
    }

    public int getQuantidadeLotes() {
        return quantidadeLotes;
    }

    public void setQuantidadeLotes(int quantidadeLotes) {
        this.quantidadeLotes = quantidadeLotes;
    }

    public int getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(int quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }
}
