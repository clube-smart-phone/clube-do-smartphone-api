package com.clube.smartphone.services;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.entities.OrdemServico;
import com.clube.smartphone.enums.Status;
import com.clube.smartphone.repositories.ClienteRespository;
import com.clube.smartphone.repositories.OrdemServicoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrdemServicoService {

    private ClienteRespository respository;
    private OrdemServicoRepository ordemServicoRepository;

    public OrdemServicoService(OrdemServicoRepository ordemServicoRepository, ClienteRespository respository) {
        this.ordemServicoRepository = ordemServicoRepository;
        this.respository = respository;
    }

    public List<OrdemServico> listar() {
        return ordemServicoRepository.findAll();
    }

    public OrdemServico salvar(OrdemServico ordem) {

        LocalDateTime data = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String dataFormatada = data.format(formatter);

        ordem.setData(dataFormatada);
        ordem.setStatus(Status.ANALISE);
        return ordemServicoRepository.save(ordem);
    }

    public List<OrdemServico> ordemCliente(String cpf) {
        List<OrdemServico> ordens = ordemServicoRepository.findAll();
        List<OrdemServico> ord = new ArrayList<>();

        for (OrdemServico ordem : ordens) {
            if (ordem.getCliente().getCpf().equalsIgnoreCase(cpf)) {
                ord.add(ordem);
            }
        }

        return ord;
    }

}
