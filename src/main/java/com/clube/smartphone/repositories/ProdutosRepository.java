package com.clube.smartphone.repositories;

import com.clube.smartphone.entities.Produtos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutosRepository extends JpaRepository<Produtos, Long> {

    List<Produtos> modelo(String modelo);
    Produtos findBymodelo(String modelo);

}
