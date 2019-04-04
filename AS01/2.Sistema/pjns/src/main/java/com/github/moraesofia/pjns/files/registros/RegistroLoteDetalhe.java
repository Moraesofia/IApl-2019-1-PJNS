package com.github.moraesofia.pjns.files.registros;

import com.github.moraesofia.pjns.files.enums.TipoRegistroDetalheEnum;

public class RegistroLoteDetalhe extends Registro {

    private int numeroRegistro;
    private TipoRegistroDetalheEnum tipoRegistroDetalhe;

    public RegistroLoteDetalhe(Registro registro) {
        super();
        this.setPosicaoInicial(registro.getPosicaoInicial());
        this.setTamanho(registro.getTamanho());
        this.setNumeroLote(registro.getNumeroLote());
        this.setTipoRegistro(registro.getTipoRegistro());
    }

    public int getNumeroRegistro() {
        return numeroRegistro;
    }

    public void setNumeroRegistro(int numeroRegistro) {
        this.numeroRegistro = numeroRegistro;
    }

    public TipoRegistroDetalheEnum getTipoRegistroDetalhe() {
        return tipoRegistroDetalhe;
    }

    public void setTipoRegistroDetalhe(TipoRegistroDetalheEnum tipoRegistroDetalhe) {
        this.tipoRegistroDetalhe = tipoRegistroDetalhe;
    }
}
