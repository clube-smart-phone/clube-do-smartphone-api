package com.clube.smartphone.entities;

import com.clube.smartphone.enums.Status;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdemServico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Preencha todos os dados do cliente")
    @ManyToOne
    private Cliente cliente;
    @NotNull
    private String cpf;
    @NotNull(message = "Insira o problema do aparelho")
    private String problemaRelatado;
    @NotNull
    private String data;
    @NotNull
    private Status status;
    @NotNull(message = "Preencha os dados do aparelho")
    @OneToMany
    private List<Aparelho> aparelho;

}
