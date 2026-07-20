package org.example.model;

public class Status {
    boolean continuaCaida;
    boolean continuaOnline;
    boolean caiu;
    boolean subiu;
    boolean novaConexao;
    boolean subiuCaiu;

    public Status(boolean continuaCaida, boolean continuaOnline, boolean caiu, boolean subiu, boolean novaConexao, boolean subiuCaiu) {
        this.continuaCaida = continuaCaida;
        this.continuaOnline = continuaOnline;
        this.caiu = caiu;
        this.subiu = subiu;
        this.novaConexao = novaConexao;
        this.subiuCaiu = subiuCaiu;
    }

    public boolean getContinuaCaida() {
        return continuaCaida;
    }
    public boolean getContinuaOnline() {
        return continuaOnline;
    }
    public boolean getCaiu() {
        return caiu;
    }
    public boolean getSubiu() {
        return subiu;
    }
    public boolean getNovaConexao() {
        return novaConexao;
    }
    public boolean getSubiuCaiu() {
        return subiuCaiu;
    }
}
