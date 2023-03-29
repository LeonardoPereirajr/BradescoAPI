package com.bradesco.api.controller.steps;

import com.bradesco.api.controller.EnderecoController;
import com.bradesco.api.model.ConsultaEnderecoRequest;
import com.bradesco.api.model.Endereco;
import com.bradesco.api.model.ViaCepResponse;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

@CucumberContextConfiguration
@SpringBootTest
public class StepDefinitions {

    private ConsultaEnderecoRequest request;
    private ResponseEntity<Endereco> response;

    @Given("que o usuário informou o CEP {string}")
    public void que_o_usuário_informou_o_cep(String cep) {
        request = new ConsultaEnderecoRequest();
        request.setCep(cep);
    }

    @When("eu envio a requisição para a API")
    public void eu_envio_a_requisição_para_a_api() {
        EnderecoController controller = new EnderecoController();
        ViaCepResponse viaCepResponse = new ViaCepResponse();
        viaCepResponse.setCep("01001-000");
        viaCepResponse.setLogradouro("Praça da Sé");
        viaCepResponse.setBairro("Sé");
        viaCepResponse.setLocalidade("São Paulo");
        viaCepResponse.setUf("SP");
        Mockito.when(controller.getViaCepResponse(Mockito.anyString())).thenReturn(viaCepResponse);
        response = (ResponseEntity<Endereco>) controller.consultaEndereco(request);
    }

    @Then("eu devo receber um status code {int} OK")
    public void eu_devo_receber_um_status_code_ok(Integer statusCode) {
        Assert.assertEquals(Optional.ofNullable(statusCode), response.getStatusCodeValue());
    }

    @Then("o endereço retornado deve conter as informações do logradouro, bairro, cidade e estado")
    public void o_endereço_retornado_deve_conter_as_informações_do_logradouro_bairro_cidade_e_estado() {
        Assert.assertEquals("01001-000", response.getBody().getCep());
        Assert.assertEquals("Praça da Sé", response.getBody().getRua());
        Assert.assertEquals("Sé", response.getBody().getBairro());
        Assert.assertEquals("São Paulo", response.getBody().getCidade());
        Assert.assertEquals("SP", response.getBody().getEstado());
    }

    @Then("o sistema retorna uma mensagem de erro")
    public void o_sistema_retorna_uma_mensagem_de_erro() {
        Assert.assertNull(response.getBody());
        Assert.assertEquals(400, response.getStatusCodeValue());
    }

}

