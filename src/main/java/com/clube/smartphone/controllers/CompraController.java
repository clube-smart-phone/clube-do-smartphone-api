package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Compra;
import com.clube.smartphone.enums.Pagamento;
import com.clube.smartphone.services.CompraService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/comprar")
public class CompraController {

    private CompraService compraService;

    public CompraController(CompraService compraService) {
        this.compraService = compraService;
    }

    @GetMapping
    public ResponseEntity<List<Compra>> listar() {

        List<Compra> compras = compraService.listar();

        compras.forEach(compra -> {
            compra.add(linkTo(methodOn(CompraController.class).buscarPorId(compra.getId())).withSelfRel());
        });

        return ResponseEntity.ok().body(compraService.listar());

    }

    @GetMapping("/{id}")
    public ResponseEntity<Compra> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok().body(compraService.buscarPorId(id));
    }

    @PutMapping("/{id}/{quantidade}/{pagamento}")
    public ResponseEntity<Compra> compra(@PathVariable Long id, @PathVariable Double quantidade, @PathVariable Pagamento pagamento) {

        Compra compra = compraService.compra(id, quantidade, pagamento);
        return ResponseEntity.ok().body(compra);

    }
}
