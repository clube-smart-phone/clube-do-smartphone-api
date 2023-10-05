package com.clube.smartphone.services;

import com.clube.smartphone.entities.Endereco;
import com.clube.smartphone.repositories.EnderecoRepository;
import org.springframework.stereotype.Service;

@Service
public class EnderecoSerivce {

    private EnderecoRepository repository;

    public EnderecoSerivce(EnderecoRepository repository) {
        this.repository = repository;
    }

    public void save(Endereco endereco) {
        repository.save(endereco);
    }
}
