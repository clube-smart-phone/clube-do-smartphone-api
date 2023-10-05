package com.clube.smartphone.services;

import com.clube.smartphone.controllers.ProdutosController;
import com.clube.smartphone.entities.Compra;
import com.clube.smartphone.entities.Financeiro;
import com.clube.smartphone.entities.Produtos;
import com.clube.smartphone.enums.Pagamento;
import com.clube.smartphone.repositories.FinanceiroRepository;
import com.clube.smartphone.repositories.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
public class ProdutosServices {

    private ProdutosRepository repository;
    private FinanceiroService financeiroRepository;

    public ProdutosServices(ProdutosRepository repository, FinanceiroService financeiroRepository) {
        this.repository = repository;
        this.financeiroRepository = financeiroRepository;
    }

    public Produtos salvar(Produtos produtos) {
        return repository.save(produtos);
    }

    public List<Produtos> listar() {
        return repository.findAll();
    }

    public Produtos buscarPorId(Long id) {
        Produtos produto = repository.findById(id).orElseThrow(() -> new RuntimeException("Produto não encontrado."));
        return produto;
    }

    public List<Produtos> buscarPorModelo(String modelo) {
        List<Produtos> produtos = repository.findAll();
        List<Produtos> produto = new ArrayList<>();

        for (Produtos p : produtos) {
            if (p.getModelo().equalsIgnoreCase(modelo)) {
                long id = p.getId();
                p.add(linkTo(methodOn(ProdutosController.class).buscarPorId(id)).withSelfRel());
                produto.add(p);
            }
        }

        return produto;
    }

}
