package com.clube.smartphone.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Insira a rua")
    private String rua;
    @NotBlank(message = "Insira o bairro")
    private String bairro;
    @NotBlank(message = "Insira o CEP")
    private String cep;
    @NotBlank(message = "Insira a cidade")
    private String cidade;

}
