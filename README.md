<div align="center">
<img src="https://github.com/user-attachments/assets/bc078890-dbda-4a77-8a35-8f6c127987a7" alt="Banner do projeto com um desenvolvedor a programar" width="600"/>
</div>

<h1 align="center">🚀 Sistema de Gestão de Tarefas e Finanças - API</h1>

<p align="center">
Uma API RESTful completa para o gerenciamento de tarefas e finanças pessoais, desenvolvida com Spring Boot.
</p>
<p align="center">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/status-conclu%C3%ADdo-green%3Fstyle%3Dfor-the-badge" alt="Status do Projeto: Concluído"/>
</p>

📋 Índice
✨ Funcionalidades Principais

🛠️ Tecnologias Utilizadas

⚙️ Guia de Instalação e Execução

Pré-requisitos

Configuração do Banco de Dados

Executando a Aplicação

🔌 Como Usar a API

Autenticação e Autorização

Endpoints da API

✨ Funcionalidades Principais
✅ Autenticação Segura: Sistema de registro e login com autenticação baseada em JWT.

✅ Gestão de Tarefas: CRUD completo para tarefas com títulos, descrições, prazos e prioridades.

✅ Controle Financeiro: CRUD completo para transações (receitas/despesas) associadas a categorias.

✅ Relatórios: Geração de relatórios financeiros mensais e resumos gerais.

✅ Segurança: Acesso a dados restrito por usuário autenticado.

🛠️ Tecnologias Utilizadas
<p align="center">
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Java-21-orange%3Fstyle%3Dfor-the-badge%26logo%3Dopenjdk%26logoColor%3Dwhite" />
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Spring_Boot-3.3.0-6DB33F%3Fstyle%3Dfor-the-badge%26logo%3Dspring-boot%26logoColor%3Dwhite" />
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Spring_Security-6.3-6DB33F%3Fstyle%3Dfor-the-badge%26logo%3Dspring-security%26logoColor%3Dwhite" />
<img src="https://www.google.com/search?q=https://img.shields.io/badge/PostgreSQL-42475E%3Fstyle%3Dfor-the-badge%26logo%3Dpostgresql%26logoColor%3Dwhite" />
<img src="https://www.google.com/search?q=https://img.shields.io/badge/JWT-000000%3Fstyle%3Dfor-the-badge%26logo%3Djsonwebtokens%26logoColor%3Dwhite" />
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Maven-C71A36%3Fstyle%3Dfor-the-badge%26logo%3Dapache-maven%26logoColor%3Dwhite" />
<img src="https://www.google.com/search?q=https://img.shields.io/badge/Swagger-85EA2D%3Fstyle%3Dfor-the-badge%26logo%3Dswagger%26logoColor%3Dblack" />
</p>

⚙️ Guia de Instalação e Execução
Siga os passos abaixo para configurar e executar o projeto localmente.

1. Pré-requisitos
Java JDK 21 ou superior.

Maven 3.8 ou superior.

PostgreSQL instalado e em execução.

Um cliente de API como o Postman ou o Insomnia.

2. Configuração do Banco de Dados
Crie a Base de Dados:
No PostgreSQL, crie uma nova base de dados com o nome task_finance.

Configure a Conexão:
Abra o ficheiro src/main/resources/application.properties e atualize com os seus dados:

spring.datasource.url=jdbc:postgresql://localhost:5432/task_finance
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres

Popule as Categorias (Obrigatório):
Execute o seguinte script SQL na sua base de dados para criar as categorias iniciais:

INSERT INTO category (name) VALUES ('Alimentação'), ('Transporte'), ('Lazer'), ('Moradia'), ('Salário');

3. Executando a Aplicação
Clone o Repositório:

git clone [https://github.com/seu-usuario/task-finance-management-system.git](https://github.com/seu-usuario/task-finance-management-system.git)
cd task-finance-management-system

Execute a Aplicação (via Maven Wrapper):

./mvnw spring-boot:run

A API estará disponível em http://localhost:8080.

🔌 Como Usar a API
1. Autenticação e Autorização
Passo 1: Registre um Usuário
Faça uma requisição POST para /api/auth/register com o corpo:

{
    "name": "Nome do Utilizador",
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123"
}

Passo 2: Faça Login para Obter o Token
Faça uma requisição POST para /api/auth/login com o corpo:

{
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123"
}
