package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.validacao.DigisacRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class AuthenticationService {
    ObjectMapper mapper = new ObjectMapper();
    HttpClient client = HttpClient.newHttpClient();
    String url = "";

    public AuthenticationService(){

    }

    public String gerarToken(String email, String password) throws IOException, InterruptedException {
        DigisacRequest digisacRequest = new DigisacRequest(email, password);
        String jsonRequestToken = mapper.writeValueAsString(digisacRequest);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Content-Type","application/json")
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestToken))
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
}

