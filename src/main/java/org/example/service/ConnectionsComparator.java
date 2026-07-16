package org.example.service;

import org.example.model.Conexao;
import org.example.model.Status;

import java.util.ArrayList;
import java.util.List;

public class ConnectionsComparator {
    public List<Status> compare(List<Conexao> listaInicio, List<Conexao> listaFinal) {
        List<Status> relatorio = new ArrayList<>();

        for (Conexao i : listaInicio) {
            for (Conexao f : listaFinal) {
                if (i.getIdConexao().equals(f.getIdConexao())) {
                    //ACHOU A CONEXAO
                    if (i.getStatusConexao() == f.getStatusConexao()) {
                        if (!i.getStatusConexao()) {
                            //CONEXAO INICIO CAIDA E CONTINUA CAIDA
                            relatorio.add(new Status(true, false,false,false,false));
                            break;
                        }
                        else  {
                            //CONEXAO INICIO BOA E CONTINUOU BOA
                            relatorio.add(new Status(false, true,false,false,false));
                            break;
                        }
                    }
                    else {
                        if(i.getStatusConexao() && !f.getStatusConexao()) {
                            //CONEXAO CAIU
                            relatorio.add(new Status(false, false,true,false,false));
                            break;
                        }
                        else  {
                            //CONEXAO SUBIU
                            relatorio.add(new Status(false, false,false,true,false));
                            break;
                        }
                    }
                }
                else {
                    //PROVAVELMENTE UM BANIDO VOLTOU OU DEU PAU EM ALGUMA COISA
                    relatorio.add(new Status(false, false,false,false,true));
                }
            }
        }

        return relatorio;
    }
}
