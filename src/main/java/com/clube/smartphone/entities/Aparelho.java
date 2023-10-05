package com.clube.smartphone.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
public class Aparelho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Insira o tipo de aparelho")
    private String tipo;
    @NotBlank(message = "Insira a marca do aparelho")
    private String marca;
    @NotBlank(message = "Insira o modelo do aparelho")
    private String modelo;
    @NotBlank(message = "Insira o IMEI do aparelho")
    private String imei;
    @NotBlank(message = "Insira a senha do aparelho")
    private String senha;

}
