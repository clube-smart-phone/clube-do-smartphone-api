package com.clube.smartphone.exceptions.ClienteExceptions;

public class ListaClientesVazia extends RuntimeException{

    public ListaClientesVazia(String mensagem) {
        super(mensagem);
    }
}
