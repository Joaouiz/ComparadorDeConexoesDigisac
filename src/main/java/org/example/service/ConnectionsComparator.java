package org.example.service;

import org.example.model.Conexao;
import org.example.model.Status;

import java.time.*;

import java.util.ArrayList;
import java.util.List;

public class ConnectionsComparator {
    private static final ZoneId ZONE = ZoneId.of("America/Sao_Paulo");

    public List<Status> compare(List<Conexao> listaInicio, List<Conexao> listaFinal) {
        List<Status> relatorio = new ArrayList<>();

        for (Conexao i : listaInicio) {
            for (Conexao f : listaFinal) {
                if (i.getIdConexao().equals(f.getIdConexao())) {
                    //ACHOU A CONEXAO
                    if (i.getStatusConexao() == f.getStatusConexao()) {
                        if (!i.getStatusConexao()) {
                            //CONEXAO INICIO CAIDA E CONTINUA CAIDA
                            relatorio.add(new Status(true, false,false,false,false, false));

                            if (f.getLastSync() != null &&  f.getLastShutdown() != null) {
                                LocalDateTime dataSync = f.getLastSync().atZone(ZONE).toLocalDateTime();
                                LocalDateTime dataShutdown = f.getLastShutdown().atZone(ZONE).toLocalDateTime();

                                LocalDate hoje = LocalDate.now(ZONE);
                                LocalDate diaSync = f.getLastSync().atZone(ZONE).toLocalDate();
                                LocalDate diaShutdown = f.getLastShutdown().atZone(ZONE).toLocalDate();

                                if (dataShutdown.isAfter(dataSync) && diaSync.equals(hoje) && diaShutdown.equals(hoje)) {
                                    relatorio.add(new Status(false, false, false, false, false, true));
                                }
                            }
                            break;
                        }
                        else {
                            //CONEXAO INICIO BOA E CONTINUOU BOA
                            relatorio.add(new Status(false, true,false,false,false, false));
                        }
                    }
                    if(i.getStatusConexao() && !f.getStatusConexao()) {
                        //CONEXAO CAIU
                        relatorio.add(new Status(false, false,true,false,false, false));
                        break;
                    }
                    if(!i.getStatusConexao() && f.getStatusConexao())  {
                        //CONEXAO SUBIU
                        relatorio.add(new Status(false, false,false,true,false, false));
                        break;
                    }
                    if (f.getLastShutdown() == null){
                        //SE NUNCA CAIU EH PQ EH NOVA
                        relatorio.add(new Status(true, false,false,false,true, false));
                        break;
                    }
                }

            }
        }

        return relatorio;
    }
}
