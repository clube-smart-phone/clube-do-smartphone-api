package com.clube.smartphone;

import com.clube.smartphone.controllers.ClienteController;
import com.clube.smartphone.entities.Cliente;
import com.clube.smartphone.entities.Endereco;
import com.clube.smartphone.entities.dtos.ClienteDTO;
import com.clube.smartphone.services.ClienteService;
import com.clube.smartphone.services.EnderecoSerivce;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class ClienteControllerTest {

    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService clienteService;

    @Mock
    private EnderecoSerivce enderecoSerivce;

    @Test
    public void CriarCliente() {

        Endereco endereco = new Endereco(1L, "Rua M", "Trapiche", "57010795", "Maceió");
        ClienteDTO cliente = new ClienteDTO(1L, "Gustavo", "981621126", LocalDate.now(), "123456789", "email@email.com", endereco);

        when(clienteService.salvar(cliente)).thenReturn(cliente);

        BindingResult result = new BeanPropertyBindingResult(cliente, "ordem");

        ResponseEntity responseEntity = clienteController.salvar(cliente, result);

        verify(clienteService).salvar(cliente);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

        assertEquals(cliente, responseEntity.getBody());

    }

    @Test
    public void ListarTodosOsClientes() {

        List<ClienteDTO> lista = clienteService.listar();

        when(clienteService.listar()).thenReturn(lista);

        ResponseEntity response = clienteController.listar();

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(lista, response.getBody());

    }

    @Test
    public void BuscaClientePorId() {

        Endereco endereco = new Endereco(1L, "Rua M", "Trapiche", "57010795", "Maceió");
        ClienteDTO cliente = new ClienteDTO(1L, "Gustavo", "981621126", LocalDate.now(), "123456789", "email@email.com", endereco);

        when(clienteService.buscarPorId(anyLong())).thenReturn(cliente);

        ResponseEntity response = clienteController.buscarPorId(anyLong());

        assertEquals(HttpStatus.OK, response.getStatusCode());

        assertEquals(cliente, response.getBody());

    }

}
