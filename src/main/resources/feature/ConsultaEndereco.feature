Feature: Consulta de endereço
  Como usuário do sistema
  Eu gostaria de consultar um endereço através do CEP
  Para que eu possa obter informações sobre o logradouro, bairro, cidade e estado

  Scenario: Consulta de endereço com CEP válido
    Given que o usuário informou o CEP "01001000"
    When eu envio a requisição para a API
    Then eu devo receber um status code 200 OK
    And o endereço retornado deve conter as informações do logradouro, bairro, cidade e estado

  Scenario: Consulta de endereço com CEP inválido
    Given que o usuário informou o CEP "00000000"
    When eu envio a requisição para a API
    Then eu devo receber um status code 404 Not Found
