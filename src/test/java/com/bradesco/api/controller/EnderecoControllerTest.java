package com.bradesco.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class EnderecoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void consultaEnderecoDeveRetornarEnderecoEValorDoFrete() throws Exception {
        String cep = "01001-000";
        String rua = "Praça da Sé";
        String bairro = "Sé";
        String cidade = "São Paulo";
        String estado = "SP";
        double frete = 7.85;

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
                        .contentType("application/json")
                        .content("{\"cep\": \"" + cep + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.cep").value(cep))
                .andExpect(MockMvcResultMatchers.jsonPath("$.rua").value(rua))
                .andExpect(MockMvcResultMatchers.jsonPath("$.bairro").value(bairro))
                .andExpect(MockMvcResultMatchers.jsonPath("$.cidade").value(cidade))
                .andExpect(MockMvcResultMatchers.jsonPath("$.estado").value(estado))
                .andExpect(MockMvcResultMatchers.jsonPath("$.frete").value(frete));
    }

    @Test
    void consultaEnderecoComCepInvalidoDeveRetornarBadRequest() throws Exception {
        String cep = "123456789";
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
                        .contentType("application/json")
                        .content("{\"cep\": \"" + cep + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void consultaEnderecoComCepNaoEncontradoDeveRetornarNotFound() throws Exception {
        String cep = "00000000";
        mockMvc.perform(MockMvcRequestBuilders.post("/v1/consulta-endereco")
                        .contentType("application/json")
                        .content("{\"cep\": \"" + cep + "\"}"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

}