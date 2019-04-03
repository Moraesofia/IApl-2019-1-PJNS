package com.github.moraesofia.pjns.files.registros;

public class RegistroFileHeader extends Registro {
    private int versaoLayoutArquivo;

    public RegistroFileHeader(Registro registro) {
        super();
        this.setTamanho(registro.getTamanho());
        this.setNumeroLote(registro.getNumeroLote());
        this.setTipoRegistro(registro.getTipoRegistro());
    }

    public int getVersaoLayoutArquivo() {
        return versaoLayoutArquivo;
    }

    public void setVersaoLayoutArquivo(int versaoLayoutArquivo) {
        this.versaoLayoutArquivo = versaoLayoutArquivo;
    }
}
