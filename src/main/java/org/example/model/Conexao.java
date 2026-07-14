package org.example.model;

public class Conexao {
    String nomeConexao;
    String numeroConexao;
    String idConexao;
    String statusConexao;

    public Conexao(String nomeConexao, String numeroConexao, String idConexao, String statusConexao) {
        this.nomeConexao = nomeConexao;
        this.numeroConexao = numeroConexao;
        this.idConexao = idConexao;
        this.statusConexao = statusConexao;
    }

    public String getNomeConexao() {
        return nomeConexao;
    }
    public String getNumeroConexao() {
        return numeroConexao;
    }
    public String getStatusConexao() {
        return statusConexao;
    }
}
