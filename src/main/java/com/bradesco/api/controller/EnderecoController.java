package com.bradesco.api.controller;

import com.bradesco.api.model.ConsultaEnderecoRequest;
import com.bradesco.api.model.Endereco;
import com.bradesco.api.model.ViaCepResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/v1")
public class EnderecoController {

    private static final String URL_VIACEP = "https://viacep.com.br/ws/";

    @PostMapping("/consulta-endereco")
    public ResponseEntity<?> consultaEndereco(@RequestBody ConsultaEnderecoRequest request) {
        String cep = request.getCep();
        if (cep == null) {
            return ResponseEntity.badRequest().body("CEP inválido. O CEP deve conter 8 dígitos numéricos.");
        }

        String url = URL_VIACEP + cep + "/json/";
        RestTemplate restTemplate = new RestTemplate();
        ViaCepResponse viaCepResponse = null;
        try {
            viaCepResponse = restTemplate.getForObject(url, ViaCepResponse.class);
        } catch (HttpClientErrorException.NotFound e) {
            return ResponseEntity.notFound().build();
        } catch (RestClientException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Erro ao consultar o serviço ViaCEP");
        }

        if (viaCepResponse != null && viaCepResponse.getCep() != null) {
            Endereco endereco = new Endereco();
            endereco.setCep(viaCepResponse.getCep());
            endereco.setRua(viaCepResponse.getLogradouro());
            endereco.setComplemento(viaCepResponse.getComplemento());
            endereco.setBairro(viaCepResponse.getBairro());
            endereco.setCidade(viaCepResponse.getLocalidade());
            endereco.setEstado(viaCepResponse.getUf());
            endereco.setFrete(calculaFrete(endereco.getEstado()));
            return ResponseEntity.ok().body(endereco);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    private double calculaFrete(String estado) {
        switch (estado) {
            case "SP":
            case "RJ":
            case "MG":
            case "ES":
                return 7.85;
            case "GO":
            case "MT":
            case "MS":
            case "DF":
                return 12.50;
            case "MA":
            case "PI":
            case "CE":
            case "RN":
            case "PE":
            case "PB":
            case "SE":
            case "AL":
            case "BA":
                return 15.98;
            case "PR":
            case "SC":
            case "RS":
                return 17.30;
            case "AC":
            case "AP":
            case "AM":
            case "PA":
            case "RO":
            case "RR":
            case "TO":
                return 20.83;
            default:
                return 0.0;
        }
    }

    public ViaCepResponse getViaCepResponse(String cep) {
        RestTemplate restTemplate = new RestTemplate();
        String url = URL_VIACEP + cep + "/json/";
        return restTemplate.getForObject(url, ViaCepResponse.class);
    }

}
