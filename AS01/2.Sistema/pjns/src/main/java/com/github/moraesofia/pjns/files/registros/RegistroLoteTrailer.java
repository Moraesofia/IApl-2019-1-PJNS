package com.github.moraesofia.pjns.files.registros;

public class RegistroLoteTrailer extends Registro {
    private int quantidadeRegistros;

    public RegistroLoteTrailer(Registro registro) {
        super();
        this.setTamanho(registro.getTamanho());
        this.setNumeroLote(registro.getNumeroLote());
        this.setTipoRegistro(registro.getTipoRegistro());
    }

    public int getQuantidadeRegistros() {
        return quantidadeRegistros;
    }

    public void setQuantidadeRegistros(int quantidadeRegistros) {
        this.quantidadeRegistros = quantidadeRegistros;
    }
}
