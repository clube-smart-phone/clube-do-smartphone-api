package com.clube.smartphone.entities.dtos;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.entities.Endereco;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
public class ClienteDTO extends RepresentationModel<ClienteDTO> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "Insira o nome do cliente")
    private String nome;
    @NotBlank(message = "Insira o contato do cliente")
    private String telefone;
    @NotNull
    private String cpf;
    @NotNull(message = "Insira o e-mail")
    @Email(message = "Formato de e-mail inválido")
    private String email;
    @NotNull(message = "Preencha o endereço")
    @ManyToOne
    private Endereco endereco;

    public ClienteDTO(Cliente cliente) {
        this.id = cliente.getId();
        this.nome = cliente.getNome();
        this.telefone = cliente.getTelefone();
        this.cpf = cliente.getCpf();
        this.email = cliente.getEmail();
        this.endereco = cliente.getEndereco();
    }

}
