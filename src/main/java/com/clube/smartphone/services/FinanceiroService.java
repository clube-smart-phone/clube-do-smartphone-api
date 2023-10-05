package com.clube.smartphone.services;

import com.clube.smartphone.entities.Financeiro;
import com.clube.smartphone.repositories.FinanceiroRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class FinanceiroService {

    private FinanceiroRepository repository;

    public FinanceiroService(FinanceiroRepository repository) {
        this.repository = repository;
    }

    public Financeiro salvar(Financeiro financeiro) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String dataFormatada = LocalDateTime.now().format(formatter);
        financeiro.setDataDaVenda(dataFormatada);

        return repository.save(financeiro);
    }

    public List<Financeiro> listar() {
        return repository.findAll();
    }

}
