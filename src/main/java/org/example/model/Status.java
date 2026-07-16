package org.example.model;

public class Status {
    boolean continuaCaida;
    boolean continuaOnline;
    boolean caiu;
    boolean subiu;
    boolean novaConexao;

    public Status(boolean continuaCaida, boolean continuaOnline, boolean caiu, boolean subiu, boolean novaConexao) {
        this.continuaCaida = continuaCaida;
        this.continuaOnline = continuaOnline;
        this.caiu = caiu;
        this.subiu = subiu;
        this.novaConexao = novaConexao;
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
}
