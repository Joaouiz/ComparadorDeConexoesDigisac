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
        String url = "{{URL}}/api/v1/services?query={%22where%22%3A{%22archivedAt%22%3A{%22%24eq%22%3Anull}}%2C%22order%22%3A[[%22name%22%2C%22asc%22]]%2C%22page%22%3A1%2C%22perPage%22%3A50}";

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .header("Authorization", "Bearer " + token)
                .GET()
                .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println(response.statusCode());
        System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(mapper.readTree(response.body())));
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
        for (int i = 0; i <= 50; i++) {
            if (root.get("connections").get(i) == null){ //talvez de certo pra verificar se o json acabou
                continue;
            }

            conexoes.add(new Conexao(
                    root.get("connections").get(i).get("name").asText(),
                    root.get("connections").get(i).get("number").asText(),
                    root.get("connections").get(i).get("idService").asText(),
                    root.get("connections").get(i).get("status").asText()
            ));
        }

        return conexoes;
    }

}
