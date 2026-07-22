package org.example;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.example.model.Conexao;
import org.example.model.Status;
import org.example.service.ConnectionsComparator;
import org.example.service.ConnectionsComparatorMap;
import org.example.service.DigisacService;
import org.example.service.AuthenticationService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() throws IOException, InterruptedException {

        String email = "joao.ordines@eem.adv.br"; //PREENCHER EMAIL
        String password = "%.qe6=D["; //PREENCHER SENHA
        AuthenticationService validacao = new AuthenticationService();
        //String token = validacao.gerarToken(email, password);
        DigisacService digisacService = new DigisacService();
        Scanner scanner = new Scanner(System.in);
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        //digisacService.testadorGet("");
        //System.out.println(token);

        int continuaCaida = 0, continuaOnline = 0, caiu = 0, subiu = 0, novaConexao = 0, subiuCaiu = 0;

            System.out.println("1 - Lista do inicio do dia\n2 - Lista do final do dia");
            int escolha = scanner.nextInt();

            if (escolha == 1) {
                List<Conexao> listaEntrada = new ArrayList<>();
                listaEntrada = digisacService.gerarListaConexoes("");

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("conexoesInicio.json"), listaEntrada);

            } else if (escolha == 2) {
                List<Conexao> listaSaida = new ArrayList<>();
                listaSaida = digisacService.gerarListaConexoes("");

                mapper.writerWithDefaultPrettyPrinter().writeValue(new File("conexoesFim.json"), listaSaida);

                ConnectionsComparatorMap comparador = new ConnectionsComparatorMap();

                List<Status> kpop = new ArrayList<>();
                List<Conexao> listaEntrada = mapper.readValue(new File("conexoesInicio.json"), new TypeReference<List<Conexao>>() {});
                kpop = comparador.compare(listaEntrada, listaSaida);

                //contador
                for (Status s : kpop) {
                    if (s.getContinuaCaida()) {
                        continuaCaida++;
                    }
                    if (s.getContinuaOnline()) {
                        continuaOnline++;
                    }
                    if (s.getCaiu()) {
                        caiu++;
                    }
                    if (s.getSubiu()) {
                        subiu++;
                    }
                    if (s.getNovaConexao()) {
                        novaConexao++;
                    }
                    if (s.getSubiuCaiu()) {
                        subiuCaiu++;
                    }

                }
                System.out.println("--------------------------");
                System.out.println("Relatorio Conexoes");
                System.out.println("--------------------------");
                System.out.println("Mantiveram caidas " + continuaCaida + " conexoes");
                System.out.println("Mantiveram online " + continuaOnline + " conexoes");
                System.out.println("Subiu caiu " + subiuCaiu + " conexoes");
                System.out.println("Cairam " + caiu + " conexoes");
                System.out.println("Subiram " + subiu + " conexoes");

                System.out.println("E por fim, tivemos " + novaConexao + " novas conexoes");

            } else {
                System.out.println("Por favor ne...");
            }

        /*for(int i = 0; i <= 88; i++) {
            System.out.println(listaEntrada.get(i).getNomeConexao());
            System.out.println(listaEntrada.get(i).getNumeroConexao());
            System.out.println(listaEntrada.get(i).getIdConexao());
            System.out.println(listaEntrada.get(i).getStatusConexao() + "\n");
        }*/

        /*for (int i = 0; i <= kpop.size(); i++) {
            System.out.println(kpop.get(i).getContinuaCaida());
            System.out.println(kpop.get(i).getContinuaOnline());
            System.out.println(kpop.get(i).getCaiu());
            System.out.println(kpop.get(i).getSubiu());
        }*/


    }
}