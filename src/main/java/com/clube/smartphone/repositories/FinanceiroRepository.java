package com.clube.smartphone.repositories;

import com.clube.smartphone.entities.Financeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FinanceiroRepository extends JpaRepository<Financeiro, Long> {

}