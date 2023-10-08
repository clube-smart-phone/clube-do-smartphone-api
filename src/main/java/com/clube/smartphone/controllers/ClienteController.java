package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.dtos.ClienteDTO;
import com.clube.smartphone.services.ClienteService;
import com.clube.smartphone.services.EnderecoSerivce;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/clientes")
@CrossOrigin("http://localhost:5173")
public class ClienteController {

    private final ClienteService serviceCliente;
    private final EnderecoSerivce serviceEndereco;

    public ClienteController(ClienteService serviceCliente, EnderecoSerivce enderecoSerivce) {
        this.serviceCliente = serviceCliente;
        this.serviceEndereco = enderecoSerivce;
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> listar() {

        List<ClienteDTO> cliente = serviceCliente.listar();

        cliente.forEach(clienteDTO -> {
            clienteDTO.add(linkTo(methodOn(ClienteController.class).buscarPorId(clienteDTO.getId())).withSelfRel());
        });

        return ResponseEntity.ok().body(cliente);

    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscarPorId(@PathVariable Long id) {
        var cliente = serviceCliente.buscarPorId(id);
        cliente.add(linkTo(methodOn(ClienteController.class).listar()).withRel("Todos clientes"));
        return ResponseEntity.ok(cliente);
    }

    @PostMapping
    public ResponseEntity<Object> salvar(@RequestBody @Valid ClienteDTO cliente, BindingResult result) {

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .collect(Collectors.toList());

            Map<String, Object> response = new HashMap<>();
            response.put("success", false);
            response.put("message", "Erro de validação");
            response.put("errors", errors);

            return ResponseEntity.badRequest().body(response);
        }

        serviceEndereco.save(cliente.getEndereco());
        serviceCliente.salvar(cliente);

        Map<String, Object> response = new HashMap<>();
        response.put("success", true);
        response.put("message", "Cliente cadastrado com sucesso");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

}
