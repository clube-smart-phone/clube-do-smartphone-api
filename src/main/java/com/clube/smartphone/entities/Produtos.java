package com.clube.smartphone.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
public class Produtos extends RepresentationModel<Produtos> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Informe a marca")
    private String fabricante;
    @NotNull(message = "Informe o modelo")
    private String modelo;
    @NotNull(message = "Insira a cor do produto")
    private String cor;
    @NotNull(message = "Defina uma quantidade")
    private Double quantidade;
    @NotNull(message = "Insira o valor do produto")
    private  double preco;

    public Produtos() {

    }

    public Produtos(String fabricante, String modelo, String cor, Double quantidade, double preco) {
        this.fabricante = fabricante;
        this.modelo = modelo;
        this.cor = cor;
        this.quantidade = quantidade;
        this.preco = preco;
    }
}
