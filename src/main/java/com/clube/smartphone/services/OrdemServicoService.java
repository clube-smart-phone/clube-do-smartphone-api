package com.clube.smartphone.services;

import com.clube.smartphone.entities.OrdemServico;
import com.clube.smartphone.enums.Status;
import com.clube.smartphone.repositories.OrdemServicoRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemServicoService {

    private final OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository) {
        this.ordemServicoRepository = ordemServicoRepository;
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }
    
    private final LocalDateTime data = LocalDateTime.now();
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    private final String dataFormatada = data.format(formatter);

    public OrdemServico salvar(OrdemServico ordem) {

        ordem.setData(dataFormatada);
        ordem.setStatus(Status.ANALISE);

        return ordemServicoRepository.save(ordem);
    }

    public List<OrdemServico> ordemCliente(String cpf) {
        List<OrdemServico> ordens = ordemServicoRepository.findAll();
        List<OrdemServico> ord = new ArrayList<>();

        ordens.forEach(o -> {
            if (o.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                ord.add(o);
            }
        });

        return ord;
    }

    public OrdemServico finalizarOrdem(String cpf) {

        OrdemServico ordem = ordemServicoRepository.cpf(cpf);
        ordem.setStatus(Status.FINALIZADO);
        ordem.setData(dataFormatada);
        ordemServicoRepository.save(ordem);

        return ordem;
    }

}
