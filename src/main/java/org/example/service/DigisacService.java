package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Conexao;
import org.example.validacao.DigisacRequest;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class DigisacService {

    public List<Conexao> gerarListaConexoes(String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String url = "https://eggeremesquita.digisac.app//api/v1/services?query=%7B%22where%22%3A%7B%22archivedAt%22%3A%7B%22%24eq%22%3Anull%7D%7D%2C%22order%22%3A%5B%5B%22name%22%2C%22asc%22%5D%5D%2C%22page%22%3A1%2C%22perPage%22%3A200%7D";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.statusCode());
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(response.body())));
        //^^^^printa o json

        List<Conexao> conexoes = new ArrayList<>();

        JsonNode root = mapper.readTree(response.body());

        for (int i = 0; i < root.get("total").asInt(); i++){
            JsonNode conexao = root.get("data").get(i);

                if(conexao.get("type").asText().equals("whatsapp")) {
                    String nome = conexao.get("name").asText();
                    String id = conexao.get("id").asText();
                    JsonNode data = conexao.get("data");
                    JsonNode status = data.get("status");
                    boolean connected = status.path("isConnected").asBoolean();
                    String sync = data.path("lastSyncAt").asText();
                    Instant lastSync = null;
                    if (!sync.isBlank()) {
                        lastSync = Instant.parse(sync);
                    }
                    String shutdown = data.path("lastShutdownAt").asText();
                    Instant lastShutdown = null;
                    if (!shutdown.isBlank()) {
                        lastShutdown = Instant.parse(shutdown);
                    }
                    conexoes.add(new Conexao(nome, "jujuba", id, connected, lastSync, lastShutdown));
                }
        }

        return conexoes;
    }

    public void testadorGet(String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String url = "";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //System.out.println(response.statusCode());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(response.body())));
        //^^^^printa o json
    }
}