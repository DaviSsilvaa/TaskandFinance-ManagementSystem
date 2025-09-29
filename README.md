Sistema de Gestão de Tarefas e Finanças - API
Este projeto é uma API RESTful completa para o gerenciamento de tarefas e finanças pessoais, desenvolvida com Spring Boot. A API permite que os usuários se registrem, autentiquem, gerenciem suas tarefas diárias e controlem suas transações financeiras de maneira integrada e segura.

Funcionalidades Principais
Autenticação Segura: Sistema de registro e login com autenticação baseada em JWT (JSON Web Tokens).

Gestão de Tarefas: Crie, liste, atualize e apague tarefas com títulos, descrições, prazos e prioridades.

Controle Financeiro: Cadastre receitas e despesas, associe transações a categorias e monitore suas finanças.

Relatórios: Gere relatórios financeiros mensais e resumos gerais para visualizar seus gastos.

Segurança: Acesso a dados pessoais (tarefas e finanças) protegido e restrito a cada usuário autenticado.

Tecnologias Utilizadas
Backend:

Spring Boot: Framework principal para o desenvolvimento da aplicação.

Spring Security: Para implementação da segurança e autenticação de usuários.

Spring Data JPA: Para a camada de persistência e integração com o banco de dados.

JWT (JSON Web Tokens): Para a autenticação stateless da API.

Banco de Dados:

PostgreSQL: Banco de dados relacional utilizado para armazenar os dados.

Segurança de Senhas:

BCrypt: Algoritmo para criptografia segura de senhas.

Documentação e Build:

Springdoc-openapi (Swagger): Geração de documentação interativa para a API.

Maven: Gerenciador de dependências e build do projeto.

Guia de Instalação e Execução
Siga os passos abaixo para configurar e executar o projeto localmente.

1. Pré-requisitos
Java JDK 21 ou superior.

Maven 3.8 ou superior.

PostgreSQL instalado e em execução.

Um cliente de API como o Postman ou o Insomnia para testar os endpoints.

2. Configuração do Banco de Dados
Crie um Banco de Dados:
No PostgreSQL, crie uma nova base de dados. Por exemplo, com o nome task_finance.

Configure a Conexão:
Abra o ficheiro src/main/resources/application.properties e atualize as seguintes propriedades com os seus dados de acesso ao PostgreSQL:

spring.datasource.url=jdbc:postgresql://localhost:5432/task_finance
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres

Popule as Categorias Iniciais (Obrigatório):
Para que o cadastro de transações funcione, é necessário ter categorias no banco de dados. Execute o seguinte script SQL na sua base de dados:

-- Insere as novas categorias
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Transporte');
INSERT INTO category (name) VALUES ('Lazer');
INSERT INTO category (name) VALUES ('Moradia');
INSERT INTO category (name) VALUES ('Salário');

3. Clonando o Repositório
Clone este repositório para sua máquina local:

git clone [https://github.com/seu-usuario/task-finance-management-system.git](https://github.com/seu-usuario/task-finance-management-system.git)
cd task-finance-management-system

4. Executando a Aplicação
Você pode executar a aplicação de duas formas:

Via Terminal (com Maven Wrapper):

./mvnw spring-boot:run

Pela sua IDE (IntelliJ/Eclipse):

Importe o projeto como um projeto Maven.

Encontre a classe TaskFinanceApplication.java e execute o método main.

A API estará disponível em http://localhost:8080.

Como Usar a API
1. Autenticação
Primeiro, crie um usuário e faça login para obter um token JWT.

POST /api/auth/register - Cria um novo usuário.

{
    "name": "Nome do Utilizador",
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123"
}

POST /api/auth/login - Autentica um usuário e retorna um token JWT.

{
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123"
}
