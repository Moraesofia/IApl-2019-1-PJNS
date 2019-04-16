package com.github.moraesofia.pjns.files.registros;

import com.github.moraesofia.pjns.files.enums.TipoLoteEnum;

public class RegistroLoteHeader extends Registro {

    private TipoLoteEnum tipoLote;
    private int versaoLayoutLote;

    public RegistroLoteHeader(Registro registro) {
        super();
        this.setPosicaoInicial(registro.getPosicaoInicial());
        this.setTamanho(registro.getTamanho());
        this.setNumeroLote(registro.getNumeroLote());
        this.setTipoRegistro(registro.getTipoRegistro());
    }

    public TipoLoteEnum getTipoLote() {
        return tipoLote;
    }

    public void setTipoLote(TipoLoteEnum tipoLote) {
        this.tipoLote = tipoLote;
    }

    public int getVersaoLayoutLote() {
        return versaoLayoutLote;
    }

    public void setVersaoLayoutLote(int versaoLayoutLote) {
        this.versaoLayoutLote = versaoLayoutLote;
    }

}
