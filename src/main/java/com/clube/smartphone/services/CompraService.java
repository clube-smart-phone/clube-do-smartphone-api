package com.clube.smartphone.services;

import com.clube.smartphone.entities.Compra;
import com.clube.smartphone.entities.Financeiro;
import com.clube.smartphone.entities.Produtos;
import com.clube.smartphone.enums.Pagamento;
import com.clube.smartphone.repositories.CompraRepository;
import com.clube.smartphone.repositories.ProdutosRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    private CompraRepository compraRepository;
    private ProdutosRepository produtosRepository;
    private FinanceiroService financeiroRepository;

    public CompraService(CompraRepository compraRepository, ProdutosRepository produtosRepository, FinanceiroService financeiroRepository) {
        this.produtosRepository = produtosRepository;
        this.compraRepository = compraRepository;
        this.financeiroRepository = financeiroRepository;
    }

    public Compra salvar(Compra compra) {
        return compraRepository.save(compra);
    }

    public List<Compra> listar() {
        return compraRepository.findAll();
    }

    public Compra buscarPorId(Long id) {
        Compra compra = compraRepository.findById(id).orElseThrow(() -> new RuntimeException("Compra inexistente"));
        return compra;
    }

    public Compra compra(Long id, Double quantidade, Pagamento pagamento) {

        Produtos produto = produtosRepository.findById(id).get();
        Double atualizaQuantidade = produto.getQuantidade() - quantidade;
        produto.setQuantidade(atualizaQuantidade);
        produtosRepository.save(produto);
        Double valorDoProduto = 0.0;

        valorDoProduto = quantidade * produto.getPreco();

        var venda = new Financeiro(valorDoProduto, LocalDateTime.now().toString());
        financeiroRepository.salvar(venda);

        var compra = new Compra(produto);

        switch (pagamento) {

            case DEBITO -> compra.setPagamento(Pagamento.DEBITO);
            case DINHEIRO -> compra.setPagamento(Pagamento.DINHEIRO);
            case PIX -> compra.setPagamento(Pagamento.PIX);
            case CREDITO -> compra.setPagamento(Pagamento.CREDITO);

        }

        compraRepository.save(compra);

        return compra;

    }
}
