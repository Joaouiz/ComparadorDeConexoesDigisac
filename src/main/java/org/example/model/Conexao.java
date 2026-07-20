package org.example.model;

import java.time.Instant;

public class Conexao {
    String nomeConexao;
    String numeroConexao;
    String idConexao;
    boolean statusConexao;
    Instant lastSync;
    Instant lastShutdown;

    public Conexao(String nomeConexao, String numeroConexao, String idConexao, boolean statusConexao,  Instant lastSync, Instant lastShutdown) {
        this.nomeConexao = nomeConexao;
        this.numeroConexao = numeroConexao;
        this.idConexao = idConexao;
        this.statusConexao = statusConexao;
        this.lastSync = lastSync;
        this.lastShutdown = lastShutdown;
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
    public Instant getLastSync() {
        return lastSync;
    }
    public Instant getLastShutdown() {
        return lastShutdown;
    }
}
