# Sistema de Gestão de Estoque e Vendas de Calçados - EstoShoes

Este é um sistema de gestão de estoque e vendas para uma loja de calçados, desenvolvido utilizando Java com Spring Boot, Hibernate/JPA para persistência de dados, e JWT para autenticação. O projeto foi feito para a Unidade Curricular: ESTRUTURAS DE DADOS E ANÁLISE DE ALGORITMOS

## Funcionalidades Principais

- Cadastro e gestão de clientes
- Cadastro e gestão de produtos (calçados)
- Gerenciamento de vendas
- Controle de estoque e vendas de produtos
- Autenticação de usuários utilizando JWT

## Tecnologias Utilizadas

- Java
- Spring Boot
- Spring Security
- Spring Data JPA
- MySQL
- JWT (JSON Web Tokens) para autenticação
- Lombok para redução de boilerplate code

## Pré-requisitos

- JDK 21
- MySQL 5.7 ou superior
- Maven 3.6 ou superior

## Configuração do Banco de Dados

1. Crie um banco de dados MySQL com o nome `esto_shoes`:

   ```sql
   CREATE DATABASE esto_shoes;
2. Verifique e ajuste as configurações de conexão com o banco de dados no arquivo application.properties.

## Como Executar

1. Clone o repositório:

    ```bash
    git clone https://github.com/seu-usuario/esto-shoes.git
    cd esto-shoes
2. Compile o projeto:

    ```bash
    mvn clean package
3. Execute o projeto:

    ```bash
    java -jar target/esto-shoes-1.0.0.jar
4. O servidor estará rodando em <http://localhost:8080>.

## Endpoints

Nos endpoints abaixo do de login, é necessário passar o token no header authorization com o token retornado pelo endpoint de login. Token vale por 20 minutos

- **Login**
- - POST /api/auth/login: Autenticação de usuário para obter token JWT.
<br>

- **Clientes**
- - POST /api/clientes: Cadastrar cliente
- - GET /api/clientes: Lista todos os clientes cadastrados.
- - PUT /api/clientes/{id}: Atualizar cliente
- - GET /api/clientes/{id}: Buscar cliente por id.
- - DELETE /api/clientes/{id}: Deletar cliente por id.
<br>

- **Produtos**
- - POST /api/clientes: Cadastrar produto
- - GET /api/clientes: Lista todos os produtos cadastrados.
- - GET /api/clientes/produtos: Lista todos os produtos cadastrados ordenados pelo parametro de sua preferencia
- - PUT /api/clientes/{id}: Atualizar produto
- - GET /api/clientes/{id}: Buscar produto por id.
- - DELETE /api/clientes/{id}: Deletar produto por id.
<br>

- **Vendas**
- - POST /api/vendas: Cria uma nova venda.
- - GET /api/vendas: Lista todas as vendas
- - GET /api/vendas/{id}: Obtém detalhes de uma venda específica.
<br>

- **Usuarios**
- - POST /api/usuarios: Cria um usuário do sistema
- - GET /api/usuarios/{id}: Obtém detalhes do usuário
