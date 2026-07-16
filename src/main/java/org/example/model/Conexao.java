package org.example.model;

public class Conexao {
    String nomeConexao;
    String numeroConexao;
    String idConexao;
    boolean statusConexao;

    public Conexao(String nomeConexao, String numeroConexao, String idConexao, boolean statusConexao) {
        this.nomeConexao = nomeConexao;
        this.numeroConexao = numeroConexao;
        this.idConexao = idConexao;
        this.statusConexao = statusConexao;
    }

    public Conexao() {

    }

    public String getNomeConexao() {
        return nomeConexao;
    }
    public String getNumeroConexao() {
        return numeroConexao;
    }
    public boolean getStatusConexao() {
        return statusConexao;
    }
    public String getIdConexao() {
        return idConexao;
    }
}
