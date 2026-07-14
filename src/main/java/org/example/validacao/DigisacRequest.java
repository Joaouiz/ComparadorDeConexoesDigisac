package org.example.validacao;

public class DigisacRequest {
    String grant_type;
    String client_id;
    String client_secret;
    String username;
    String password;

    public DigisacRequest(String username, String password) {
        this.grant_type = "password";
        this.client_id = "api";
        this.client_secret = "secret";
        this.username = username;
        this.password = password;
    }

    public String getGrant_type() {
        return grant_type;
    }
    public String getClient_id() {
        return client_id;
    }
    public String getClient_secret() {
        return client_secret;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
