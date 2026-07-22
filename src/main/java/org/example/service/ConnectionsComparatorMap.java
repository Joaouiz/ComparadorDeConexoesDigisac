package org.example.service;

import org.example.model.Conexao;
import org.example.model.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConnectionsComparatorMap {
    private static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");

    public List<Status> compare(List<Conexao> listaInicio, List<Conexao> listaFinal) {
        List<Status> relatorio = new ArrayList<>();
        Map<String, Conexao> mapaInicio = new HashMap<>();
        Map<String, Conexao> mapaFinal = new HashMap<>();

        for (Conexao c : listaInicio) {
            mapaInicio.put(c.getIdConexao(), c);
        }
        for (Conexao c : listaFinal) {
            mapaFinal.put(c.getIdConexao(), c);
        }

        for (Conexao f : mapaFinal.values()) {
            Conexao i = mapaInicio.get(f.getIdConexao());
            if (i != null) {
                if (i.getStatusConexao() == f.getStatusConexao()) {
                    if (!i.getStatusConexao()) {
                        //CONEXAO INICIO CAIDA E CONTINUA CAIDA
                        relatorio.add(new Status(true, false,false,false,false, false));

                        if (f.getLastSync() != null &&  f.getLastShutdown() != null) {
                            //SUBIU CAIU
                            LocalDateTime dataSync = f.getLastSync().atZone(ZONE).toLocalDateTime();
                            LocalDateTime dataShutdown = f.getLastShutdown().atZone(ZONE).toLocalDateTime();

                            LocalDate hoje = LocalDate.now(ZONE);
                            LocalDate diaSync = f.getLastSync().atZone(ZONE).toLocalDate();
                            LocalDate diaShutdown = f.getLastShutdown().atZone(ZONE).toLocalDate();

                            if (dataShutdown.isAfter(dataSync) && diaSync.equals(hoje) && diaShutdown.equals(hoje)) {
                                relatorio.add(new Status(false, false, false, false, false, true));
                            }
                        }
                    }
                    else {
                        //CONEXAO INICIO BOA E CONTINUOU BOA
                        relatorio.add(new Status(false, true,false,false,false, false));
                    }
                }
                else if(i.getStatusConexao() && !f.getStatusConexao()) {
                    //CONEXAO CAIU
                    relatorio.add(new Status(false, false,true,false,false, false));
                }
                else if(!i.getStatusConexao() && f.getStatusConexao())  {
                    //CONEXAO SUBIU
                    relatorio.add(new Status(false, false,false,true,false, false));
                }
            }
            else {
                //CONEXAO NOVA
                relatorio.add(new Status(false, false,false,false,true, false));
            }
        }

        return relatorio;
    }
}
