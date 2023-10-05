package com.clube.smartphone.entities;

import com.clube.smartphone.services.FinanceiroService;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
public class Financeiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private String dataDaVenda;

    public Financeiro() {

    }

    public Financeiro(Double valor, String dataDaVenda) {
        this.valor = valor;
        this.dataDaVenda = dataDaVenda;
    }
}
