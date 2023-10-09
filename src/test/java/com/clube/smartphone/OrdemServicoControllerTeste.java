package com.clube.smartphone;

import com.clube.smartphone.controllers.OrdemServicoController;
import com.clube.smartphone.entities.Aparelho;
import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.entities.Endereco;
import com.clube.smartphone.entities.OrdemServico;
import com.clube.smartphone.enums.Status;
import com.clube.smartphone.services.AparelhoService;
import com.clube.smartphone.services.ClienteService;
import com.clube.smartphone.services.OrdemServicoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class OrdemServicoControllerTeste {

    @Autowired
    ObjectMapper objectMapper;

    @InjectMocks
    private OrdemServicoController ordemServicoController;

    @Mock
    private OrdemServicoService ordemServicoService;

    @Mock
    private ClienteService clienteService;

    @Mock
    private AparelhoService aparelhoService;

    @Test
    public void CriaOrdemDeServico() {

        Endereco endereco = new Endereco(1L, "Rua M", "Trapiche", "57010795", "Maceió");
        Cliente cliente = new Cliente(1L, "Gustavo", "981621126", "123456789", "email@email.com", LocalDate.now(), endereco);
        Aparelho aparelho = new Aparelho(1L, "iPhone", "Apple", "6S", "dasdasrer34", "123456");
        List<Aparelho> aparelhos = List.of(aparelho);
        OrdemServico ordem = new OrdemServico(1L, cliente, "123456789", "Quebrou", "05/03/2023", Status.ANALISE, aparelhos);

        when(ordemServicoService.salvar(ordem)).thenReturn(ordem);

        BindingResult result = new BeanPropertyBindingResult(ordem, "ordem");

        ordem.setCliente(cliente);
        ordem.setStatus(Status.ANALISE);
        ResponseEntity<Object> responseEntity = ordemServicoController.criar(ordem, result);

        verify(ordemServicoService).salvar(ordem);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertEquals(ordem, responseEntity.getBody());

    }

    @Test
    public void ListaTodasOrdensDeServico() {

        List<OrdemServico> ordens = ordemServicoService.listar();

        when(ordemServicoService.listar()).thenReturn(ordens);

        ResponseEntity<List<OrdemServico>> responseEntity = ordemServicoController.listar();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(ordens, responseEntity.getBody());

    }

    @Test
    public void BuscarOrdemPorCpf() {

        List<OrdemServico> ordem = Collections.singletonList(new OrdemServico());

        when(ordemServicoService.ordemCliente(anyString())).thenReturn(ordem);

        ResponseEntity<List<OrdemServico>> responseEntity = ordemServicoController.ordemServico("123456");

        verify(ordemServicoService).ordemCliente("123456");

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        assertEquals(ordem, responseEntity.getBody());

    }

    @Test
    public void FinalizarOrdemComSucesso() {
        // Suponha que você tenha uma ordem fictícia
        OrdemServico ordem = new OrdemServico();
        ordem.setStatus(Status.FINALIZADO); // Defina o status conforme necessário

        // Defina o comportamento do serviço mock
        when(ordemServicoService.finalizarOrdem(anyString())).thenReturn(ordem);

        // Execute a ação no controlador
        ResponseEntity<OrdemServico> responseEntity = ordemServicoController.finalizarOrdem("123456");

        // Verifique se o serviço mock foi chamado com o argumento correto
        verify(ordemServicoService).finalizarOrdem("123456");

        // Verifique o status de resposta
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        // Verifique se a ordem retornada corresponde à ordem fictícia
        assertEquals(ordem, responseEntity.getBody());
    }

}