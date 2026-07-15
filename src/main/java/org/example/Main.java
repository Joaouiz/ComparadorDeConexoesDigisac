package org.example;

import org.example.model.Conexao;
import org.example.service.DigisacService;
import org.example.service.AuthenticationService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() throws IOException, InterruptedException {

        //TROCAR O {URL} PELO LINK DO DIGISAC
        String email = ""; //PREENCHER EMAIL
        String password = ""; //PREENCHER SENHA
        AuthenticationService validacao = new AuthenticationService();
        String token = validacao.gerarToken(email, password);
        DigisacService digisacService = new DigisacService();
        Scanner scanner = new Scanner(System.in);

        List<Conexao> listaEntrada = new ArrayList<>();

        //System.out.println(token);

        System.out.println("1 - Lista do inicio do dia\n2 - Lista do final do dia");
        int escolha = scanner.nextInt();



        if(escolha == 1){
            listaEntrada = digisacService.gerarListaConexoes(token);
        }
        else if(escolha == 2){
            //List<Conexao> listaSaida = digisacService.gerarListaConexoes(token);
        }
        else{
            System.out.println("Por favor ne...");
        }

        for(int i = 0; i <= 88; i++) {
            System.out.println(listaEntrada.get(i).getNomeConexao());
            System.out.println(listaEntrada.get(i).getNumeroConexao());
            System.out.println(listaEntrada.get(i).getStatusConexao() + "\n");
        }
    }
}