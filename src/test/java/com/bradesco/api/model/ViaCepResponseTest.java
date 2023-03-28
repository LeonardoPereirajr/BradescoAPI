package com.bradesco.api.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ViaCepResponseTest {

    @Test
    public void testSetAndGetCep() {
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        assertNull(viaCepResponse.getCep());

        String cep = "01001000";
        viaCepResponse.setCep(cep);
        assertEquals(cep, viaCepResponse.getCep());
    }

    @Test
    public void testSetAndGetLogradouro() {
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        assertNull(viaCepResponse.getLogradouro());

        String logradouro = "Praça da Sé";
        viaCepResponse.setLogradouro(logradouro);
        assertEquals(logradouro, viaCepResponse.getLogradouro());
    }

    @Test
    public void testSetAndGetComplemento() {
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        assertNull(viaCepResponse.getComplemento());

        String complemento = "lado ímpar";
        viaCepResponse.setComplemento(complemento);
        assertEquals(complemento, viaCepResponse.getComplemento());
    }

    @Test
    public void testSetAndGetBairro() {
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        assertNull(viaCepResponse.getBairro());

        String bairro = "Sé";
        viaCepResponse.setBairro(bairro);
        assertEquals(bairro, viaCepResponse.getBairro());
    }

    @Test
    public void testSetAndGetLocalidade() {
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        assertNull(viaCepResponse.getLocalidade());

        String localidade = "São Paulo";
        viaCepResponse.setLocalidade(localidade);
        assertEquals(localidade, viaCepResponse.getLocalidade());
    }

    @Test
    public void testSetAndGetUf() {
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        assertNull(viaCepResponse.getUf());

        String uf = "SP";
        viaCepResponse.setUf(uf);
        assertEquals(uf, viaCepResponse.getUf());
    }
}
