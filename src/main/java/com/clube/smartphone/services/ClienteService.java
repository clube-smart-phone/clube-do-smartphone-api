package com.clube.smartphone.services;

import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.entities.dtos.ClienteDTO;
import com.clube.smartphone.repositories.ClienteRespository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteService {

    private ClienteRespository respository;

    public ClienteService(ClienteRespository respository) {
        this.respository = respository;
    }

    public Cliente salvar(ClienteDTO cliente) {

        validaCliente(cliente);
        var clienteEntity = new Cliente();
        BeanUtils.copyProperties(cliente, clienteEntity);
        System.out.println(clienteEntity);

        return respository.save(clienteEntity);
    }

    public List<ClienteDTO> listar() {
        return respository.findAll().stream().map(ClienteDTO::new).collect(Collectors.toList());
    }

    public ClienteDTO buscarPorId(Long id) {
        var cliente = respository.findById(id).map(ClienteDTO::new).get();
        return cliente;
    }

    public void remover(Long id) {
        respository.deleteById(id);
    }

    public void validaCliente(ClienteDTO cliente) {

        if (respository.existsByEmail(cliente.getEmail())) {
            throw new RuntimeException("E-mail já cadastrado no sistema");
        } else if (respository.existsByTelefone(cliente.getTelefone())) {
            throw new RuntimeException("Telefone já cadastrado no sistema");
        }
    }

    public Cliente cpf(String cpf) {
        return respository.cpf(cpf);
    }

}
