# API RESTful com Spring Boot
[Link para o projeto no Heroku](https://controle-financeiro-rest.herokuapp.com/swagger-ui.html#/)


Nesse projeto foi desenvolvido uma API para cadastrar lançamentos financeiros de entrada e saída, permitindo que esse lançamento seja associado a um usuário já cadastrado.
## Tecnologias
Foram seguidos os princípios SOLID e REST, utilizando as seguintes tecnologias:
* Spring Boot
    * Cache e Paginação
* Spring Security
    * Autenticação com JWT
* Spring Data JPA
    * Banco de dados H2

## Fazendo login e gerando um Token JWT
Para obter o token de autorização é necessário entrar em **autenticacao-controller > POST > Try it out** e inserir o JSON: 
    `{
        "email":"matheus@email.com",
        "senha":"senha1"
    }`

<img src="https://user-images.githubusercontent.com/42250854/78290146-a3ae2880-74f9-11ea-981d-b1cb7fda6ccc.png" height="350" width="550" >

## Fazendo uma consulta utilizando o Token
Para fazer uma consulta é necessário copiar o token gerado em **autenticacao-controller** e adiciona-lo no campo **Header para Token JWT** no **lancamento-controller > GET > Try it out** e informar que é um token Bearer, como na figura abaixo:
<img src="https://user-images.githubusercontent.com/42250854/78292315-38feec00-74fd-11ea-9698-78ff584f2a2a.png">

## Próximas funcionalidades
Este projeto está em desenvolvimento e as próximas implementações serão:
*  Liquibase
* Testes Unitários

:shipit:
