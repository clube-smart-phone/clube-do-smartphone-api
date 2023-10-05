package com.clube.smartphone.entities;

import com.clube.smartphone.enums.Pagamento;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Entity
@Data
public class Compra extends RepresentationModel<Compra> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull(message = "Insira a forma de pagamento")
    private Pagamento pagamento;
    @NotNull(message = "Insira o produto")
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produtos produto;

    public Compra() {

    }

    public Compra(Produtos produto) {
        this.produto = produto;
    }

}
