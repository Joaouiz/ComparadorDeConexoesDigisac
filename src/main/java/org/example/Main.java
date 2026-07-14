package org.example;

import org.example.model.Conexao;
import org.example.service.DigisacService;
import org.example.service.AuthenticationService;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    static void main() throws IOException, InterruptedException {

        //TROCAR O {URL} PELO LINK DO DIGISAC
        String email = " "; //PREENCHER EMAIL
        String password = " "; //PREENCHER SENHA
        AuthenticationService validacao = new AuthenticationService();
        String token = validacao.gerarToken(email, password);
        DigisacService digisacService = new DigisacService();
        Scanner scanner = new Scanner(System.in);

        System.out.println("1 - Lista do inicio do dia\n2 - Listo do final do dia");
        int escolha = scanner.nextInt();

        if(escolha == 1){
            List<Conexao> listaEntrada = digisacService.gerarListaConexoes(token);
        }
        else if(escolha == 2){
            List<Conexao> listaSaida = digisacService.gerarListaConexoes(token);
        }
        else{
            System.out.println("Por favor ne...");
        }

    }
}