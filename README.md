# 🏠 Corretor Pro

Sistema Web para gerenciamento imobiliário desenvolvido como Projeto Final do curso de Análise e Desenvolvimento de Sistemas.

## 📋 Descrição

O Corretor Pro é uma aplicação web desenvolvida para auxiliar corretores de imóveis no gerenciamento de sua carteira imobiliária, permitindo o cadastro de imóveis, controle de propostas, autenticação de usuários e geração de relatórios profissionais em PDF.

## 🚀 Funcionalidades

### Usuários

* Cadastro de usuários
* Login seguro
* Autenticação via JWT
* Proteção de rotas com Spring Security

### Imóveis

* Cadastro de imóveis
* Consulta de imóveis
* Edição de imóveis
* Exclusão de imóveis
* Pesquisa e filtros
* Localização integrada

### Propostas

* Registro de propostas
* Consulta de propostas
* Alteração de status
* Exclusão de propostas

### Relatórios

* Relatório PDF de imóveis
* Relatório PDF de propostas
* Exportação profissional utilizando JasperReports

## 🛠 Tecnologias Utilizadas

### Backend

* Java 17
* Spring Boot
* Spring Security
* Spring Data JPA
* Hibernate
* PostgreSQL
* Maven

### Frontend

* HTML5
* CSS3
* JavaScript
* AJAX

### Relatórios

* JasperReports

### Infraestrutura

* Docker
* Render
* Vercel

## 🏗 Arquitetura

O projeto segue arquitetura em camadas:

* Controller
* Service
* Repository
* Entity
* Banco de Dados PostgreSQL

## 🔒 Segurança

O sistema utiliza Spring Security e autenticação JWT para proteção dos recursos da aplicação.

## 🗄 Banco de Dados

Entidades principais:

* Usuario
* Imovel
* Proposta

Banco utilizado:

* PostgreSQL

## 📄 Relatórios

O sistema gera relatórios PDF profissionais utilizando JasperReports:

* Relatório de Imóveis
* Relatório de Propostas

## ▶ Como Executar

### Banco de Dados

Criar banco:

```sql
CREATE DATABASE corretor_db;
```

### Backend

```bash
mvn spring-boot:run
```

Servidor:

```text
http://localhost:8081
```

### Frontend

Abrir:

```text
frontend/index.html
```

ou utilizar o Live Server do VS Code.

## 🐳 Docker

Build da imagem:

```bash
docker build -t corretor-pro .
```

Executar container:

```bash
docker run -p 8081:8081 corretor-pro
```

## 📚 Conceitos Aplicados

* AJAX
* Spring Framework
* Spring Boot
* Spring Security
* JPA
* Hibernate
* JasperReports
* PostgreSQL
* Docker

## 👨‍💻 Autor

Maycon Cordeiro

Projeto desenvolvido para a disciplina Projeto Final ADS.
