package com.bradesco.api.model;

public class ConsultaEnderecoRequest {

    private String cep;

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public boolean isCepValido() {
        return cep != null &&
                cep.matches("[0-9]{8}") &&
                !cep.matches("(\\d)\\1{7,}") &&
                !cep.matches("01234567|12345678|23456789");
    }
}

