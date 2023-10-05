package com.clube.smartphone.repositories;

import com.clube.smartphone.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRespository extends JpaRepository<Cliente, Long> {
    boolean existsByEmail(String email);
    boolean existsByTelefone(String telefone);
    Cliente cpf(String cpf);
}
