# 🏠 Corretor API

Sistema full stack para gerenciamento de imóveis com autenticação JWT.

O sistema permite cadastro de usuários, login seguro e gerenciamento de imóveis vinculados ao usuário autenticado.

---

## 🚀 Tecnologias utilizadas

### Backend
- Java 17
- Spring Boot
- Spring Security
- JWT (JSON Web Token)
- Maven
- JPA / Hibernate
- PostgreSQL / H2

### Frontend
- HTML
- CSS
- JavaScript
- Bootstrap

---

## ⚙️ Funcionalidades

- ✔ Cadastro de usuários
- ✔ Login com autenticação JWT
- ✔ Rotas protegidas com Spring Security
- ✔ Dashboard do usuário
- ✔ CRUD completo de imóveis
- ✔ Imóveis vinculados ao usuário logado

---

## 🔐 Autenticação

A API utiliza JWT (JSON Web Token).

### Fluxo:

1. Usuário faz login  
2. A API gera um token JWT  
3. O token é enviado no header das requisições protegidas  

### Header:

```http
Authorization: Bearer TOKEN

📡 Endpoints da API
👤 Usuários

Criar usuário

POST /usuarios

{
  "nome": "Maycon",
  "email": "maycon@email.com",
  "senha": "123456"
}

Login

POST /usuarios/login

{
  "email": "maycon@email.com",
  "senha": "123456"
}

Resposta:

{
  "token": "jwt_token_aqui"
}

Usuário logado

GET /usuarios/me

Header:

Authorization: Bearer TOKEN

🏠 Imóveis
Criar imóvel

POST /imoveis

{
  "titulo": "Casa Jardim América",
  "endereco": "Rua C-150",
  "preco": 850000
}

Listar imóveis do usuário logado

GET /imoveis/meus

Header:

Authorization: Bearer TOKEN

🏃 Como rodar o projeto

1. Clonar o repositório

git clone https://github.com/MayconCordeiro1983/corretor-api.git

2. Entrar na pasta

cd corretor-api

3. Executar o backend

.\mvnw spring-boot:run

4. Acessar a aplicação

http://localhost:8081

🧪 Testes

Você pode testar a API utilizando:

Postman
Insomnia
Curl
PowerShell

📂 Estrutura do projeto

corretor-api/
├── src/
├── frontend/
├── img/
├── pom.xml
└── README.md

## 📸 Prints do sistema

### 🔐 Login
(img/login.png)

### 📝 Cadastro
(img/cadastro.png)

### 📊 Dashboard
(img/dashboard.png)

### 🏠 Meus Imóveis
(img/imoveis.png)

👨‍💻 Autor

Maycon Cordeiro

🔗 GitHub:
https://github.com/MayconCordeiro1983

