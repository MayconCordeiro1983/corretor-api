header
API de gestão de imóveis com Spring Boot e JWT

Tecnologias:

- Java 17
- Spring Boot
- Spring Security
- JWT
- PostgreSQL
- Maven

Funcionalidades:

- Cadastro de usuários
- Login com autenticação JWT
- CRUD de imóveis
- Imóveis vinculados ao usuário logado

Corretor API

API REST para gestão de imóveis desenvolvida com Spring Boot e JWT Authentication.  
O sistema permite cadastro de usuários, autenticação segura e gerenciamento de imóveis vinculados ao usuário logado.

Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Maven
- JPA / Hibernate
- Banco de dados relacional (H2 / PostgreSQL)

Estrutura do Projeto

src
├── controller
├── service
├── repository
├── model
├── dto
├── security
└── config

Autenticação

A API utiliza JWT (JSON Web Token) para autenticação.

Fluxo:

1️ Usuário faz login  
2️ API gera um token JWT  
3️ Token é enviado no header das requisições protegidas

Header:

Authorization: Bearer TOKEN

Endpoints da API

Usuários

Criar usuário

POST `/usuarios`

Body:

{
"nome": "Maycon",
"email": "maycon@email.com
",
"senha": "123456"
}

Login

POST `/usuarios/login`

Body:

{
"email": "maycon@email.com
",
"senha": "123456"
}

Resposta:

{
"token": "jwt_token_aqui"
}

Usuário logado

GET `/usuarios/me`

Header:

Authorization: Bearer TOKEN

🏠 Imóveis

Criar imóvel

POST `/imoveis`

Body:

{
"titulo": "Casa Jardim América",
"endereco": "Rua C-150",
"preco": 850000
}

Listar imóveis do usuário logado

GET `/imoveis/meus`

Header:

Authorization: Bearer TOKEN

Como rodar o projeto

Clone o repositório:

git clone https://github.com/MayconCordeiro1983/corretor-api.git

Entre na pasta:

cd corretor-api

Execute:

./mvnw spring-boot:run

A aplicação iniciará em:

http://localhost:8081

estando a API

Você pode testar usando:

- Postman
- Insomnia
- PowerShell
- Curl

Funcionalidades

✔ Cadastro de usuário  
✔ Login com JWT  
✔ Autenticação com Spring Security  
✔ Cadastro de imóveis  
✔ Imóveis vinculados ao usuário logado

Autor

Maycon Cordeiro

GitHub:  
https://github.com/MayconCordeiro1983
2a119d2 (Adicionado README profissional)
