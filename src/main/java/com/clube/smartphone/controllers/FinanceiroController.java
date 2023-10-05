package com.clube.smartphone.controllers;

import com.clube.smartphone.entities.Financeiro;
import com.clube.smartphone.services.FinanceiroService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class FinanceiroController {

    private FinanceiroService financeiroService;

    public FinanceiroController(FinanceiroService financeiroService) {
        this.financeiroService = financeiroService;
    }

    @GetMapping
    public List<Financeiro> listar() {
        return financeiroService.listar();
    }
}
