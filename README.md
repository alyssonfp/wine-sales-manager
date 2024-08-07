# Wine Sales Manager
## Descrição
O Wine Sales Manager é uma API desenvolvida em Java com Spring Boot, projetada para gerenciar vendas de vinhos, clientes e suas compras. A API permite realizar operações como criação de compras, recomendações de vinhos para clientes e análise de compras por ano.

## Funcionalidades
1. Gestão de Clientes e Compras: Criação e consulta de clientes e suas respectivas compras.
2. Recomendações de Vinhos: Sugestão do tipo de vinho mais comprado por cada cliente.
3. Análise de Compras: Listar clientes com base no valor total de suas compras e encontrar a maior compra realizada em um ano específico.
4. Top 3 Clientes Fieis: Retornar os três clientes com maior volume de compras.

## Tecnologias Utilizadas
#### Java: Linguagem principal utilizada.
#### Spring Boot: Framework para construção da aplicação.
#### Spring Data JPA: Utilizado para persistência de dados.
#### H2 Database: Banco de dados em memória utilizado para desenvolvimento e testes.
#### JUnit e Mockito: Para testes unitários e integração.
#### Maven: Gerenciamento de dependências.

## Pré-requisitos
Java 17+
Maven
## Instalação e Execução
Clone o repositório:

    git clone https://github.com/alyssonfp/wine-sales-manager.git

Navegue até o diretório do projeto:

    cd wine-sales-manager

Compile e execute os testes:

    mvn clean install

Execute a aplicação:

    mvn spring-boot:run

A aplicação estará disponível em http://localhost:8080.

## Endpoints
1. GET /api/compras: Lista todas as compras ordenadas pelo valor total.
2. GET /api/maior-compra/{ano}: Retorna a maior compra realizada em um determinado ano.
3. GET /api/clientes-fieis: Retorna o top 3 dos clientes mais fieis.
4. GET /api/recomendacao/cliente/tipo: Retorna uma recomendação de vinho baseado nos tipos de vinho que o cliente mais compra.

## Testes
Os testes podem ser executados usando o Maven:

    mvn test

## Autor
Alysson Ferreira - alyssonfp