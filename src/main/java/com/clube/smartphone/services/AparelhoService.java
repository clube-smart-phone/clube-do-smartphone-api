package com.clube.smartphone.services;

import com.clube.smartphone.entities.Aparelho;
import com.clube.smartphone.repositories.AparelhoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AparelhoService {

    private AparelhoRepository repository;

    public AparelhoService(AparelhoRepository repository) {
        this.repository = repository;
    }

    public List<Aparelho> salvar(List<Aparelho> aparelho) {
        return repository.saveAll(aparelho);
    }
}
