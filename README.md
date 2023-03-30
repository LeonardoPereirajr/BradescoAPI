# API de Consulta CEP

API que realiza consulta de Cep conforme a documentação https://viacep.com.br/.

## Como utilizar

Clone o projeto para a sua IDE.

Através do INSOMNIA ou POSTMANN gere a requisição. Exemplo:

http://localhost:8080/v1/consulta-endereco

REQUEST
{
  "cep": "89010003"
}

RESPONSE
{
	"cep": "89010-003",
	"rua": "Rua 15 de Novembro",
	"complemento": "de 915 ao fim - lado ímpar",
	"bairro": "Centro",
	"cidade": "Blumenau",
	"estado": "SC",
	"frete": 17.3
}

Tecnologias utilizadas

 - Java 11
 - Spring boot
 - API REST Template
 - Documentação Swagger
 - Testes unitários JUnit5
 - Testes automatizados utilizando cucumber

Autor
| Leonardo Pereira | 
