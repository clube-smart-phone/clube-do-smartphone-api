package com.clube.smartphone.exceptions.ClienteExceptions;

public class ClienteNaoEncontrado extends RuntimeException{

    public ClienteNaoEncontrado(String mensagem) {
        super(mensagem);
    }
}
