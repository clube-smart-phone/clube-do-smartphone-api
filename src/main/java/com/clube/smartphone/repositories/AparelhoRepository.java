package com.clube.smartphone.repositories;

import com.clube.smartphone.entities.Aparelho;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AparelhoRepository extends JpaRepository<Aparelho, Long> {
}
