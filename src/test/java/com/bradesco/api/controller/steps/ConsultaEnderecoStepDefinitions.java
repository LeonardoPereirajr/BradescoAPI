package com.bradesco.api.controller.steps;

import com.bradesco.api.controller.EnderecoController;
import com.bradesco.api.model.ConsultaEnderecoRequest;
import com.bradesco.api.model.Endereco;
import com.bradesco.api.model.ViaCepResponse;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.junit.Assert;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@CucumberContextConfiguration
@SpringBootTest
public class ConsultaEnderecoStepDefinitions {

    private ConsultaEnderecoRequest request;
    private ResponseEntity<Endereco> response;

    @Given("que o CEP {string} é válido")
    public void queOCepÉVálido(String cep) {
        request = new ConsultaEnderecoRequest();
        request.setCep(cep);
        request.isCepValido();
    }

    @Given("que o CEP {string} é inválido")
    public void queOCepÉInválido(String cep) {
        request = new ConsultaEnderecoRequest();
        request.setCep(cep);
        request.isCepValido();
    }

    @When("o usuário consulta o endereço pelo CEP")
    public void oUsuárioConsultaOEndereçoPeloCep() {
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

    @Then("o sistema retorna o endereço")
    public void oSistemaRetornaOEndereço() {
        Assert.assertEquals("01001-000", response.getBody().getCep());
        Assert.assertEquals("Praça da Sé", response.getBody().getRua());
        Assert.assertEquals("Sé", response.getBody().getBairro());
        Assert.assertEquals("São Paulo", response.getBody().getCidade());
        Assert.assertEquals("SP", response.getBody().getEstado());
    }

    @Then("o sistema retorna uma mensagem de erro")
    public void oSistemaRetornaUmaMensagemDeErro() {
        Assert.assertNull(response.getBody());
        Assert.assertEquals(400, response.getStatusCodeValue());
    }
    @And("Endereço deve retornar informaçoes")
    public void oEndereçoRetornadoDeveConterAsInformaçõesDoLogradouroBairroCidadeEEstado() {
        Assert.assertEquals("Praça da Sé", response.getBody().getRua());
        Assert.assertEquals("Sé", response.getBody().getBairro());
        Assert.assertEquals("São Paulo", response.getBody().getCidade());
        Assert.assertEquals("SP", response.getBody().getEstado());
    }
}
