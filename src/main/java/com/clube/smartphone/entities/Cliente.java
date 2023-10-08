package com.clube.smartphone.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.RepresentationModel;

import java.time.LocalDate;

@Entity
@Data
public class Cliente extends RepresentationModel<Cliente> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Insira o nome do cliente")
    private String nome;
    @NotBlank(message = "Insira o contato do cliente")
    private String telefone;
    @NotNull(message = "Insira a data de nascimento")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate dataDeNascimento;
    @NotNull
    private String cpf;
    @NotNull(message = "Insira o e-mail")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    @ManyToOne
    private Endereco endereco;

    public Cliente() {

    }

    public Cliente(Long id, String nome, String telefone, String cpf, String email, LocalDate dataDeNascimento) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.cpf = cpf;
        this.email = email;
        this.dataDeNascimento = dataDeNascimento;
    }
}
