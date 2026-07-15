package org.example.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Conexao;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class DigisacService {

    public List<Conexao> gerarListaConexoes(String token) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        ObjectMapper mapper = new ObjectMapper();
        String url = "";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        //System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(response.body())));
        //^^^^printa o json

        JsonNode root = mapper.readTree(response.body());

        // Exemplo pegacao de dados
        // root.get("candidates").get(0).get("content").get("parts").get(0).get("text").asText()

        List<Conexao> conexoes = new ArrayList<>();
        /*
        for (JsonNode c : root.get("connections")) {
            conexoes.add(new Conexao(
                    get(c).get("name").asText(),
                    get(c).get("number").asText(),
                    get(c).get("idService").asText(),
                    get(c).get("status").asText()
            ));
        }
        */
        for (int i = 0; i < root.get("total").asInt(); i++) {

            JsonNode conexao = root.get("data").get(i);
                String nome = conexao.get("name").asText();
                String id = conexao.get("id").asText();
                JsonNode data = conexao.get("data");
                    JsonNode status = data.get("status");
                    String connected = status.path("isConnected").asText();

            conexoes.add(new Conexao(nome, "jujuba", id, connected));
        }

        return conexoes;
    }

}
