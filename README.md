# Task Finance Management System API

Este projeto fornece uma API para o gerenciamento de tarefas e finanças, permitindo que usuários possam gerenciar suas tarefas de forma simples e eficiente. A API inclui recursos como autenticação de usuários, gerenciamento de usuários, e a interação com dados de tarefas e finanças.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento da aplicação.
- **Spring Security**: Segurança e autenticação de usuários.
- **Spring Data JPA**: Integração com banco de dados PostgreSQL.
- **PostgreSQL**: Banco de dados utilizado.
- **BCrypt**: Criptografia de senhas.
- **Spring Web**: Para construção de endpoints RESTful.

## Instruções de Execução

Rodar TaskFinanceApplication

O Bd é em PostGre, cria um register com essa requisição, email, senha, e nome

POST	http://localhost:8080/api/auth/register	Cria um novo utilizador no sistema.

{
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123",
    "name": "Nome do Utilizador"
}

Aqui você loga com o email e senha

Login	http://localhost:8080/api/auth/login	POST	JSON (E-mail/Senha)

{
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123",
}

2. Gestão de Tarefas (Requer JWT)

GET	/api/tasks	Retorna todas as tarefas do utilizador autenticado.
POST	/api/tasks	Cria uma nova tarefa.
GET	/api/tasks/{id}	Retorna os detalhes de uma tarefa específica pelo ID.
PUT	/api/tasks/{id}	Atualiza uma tarefa específica pelo ID.
DELETE	/api/tasks/{id}	Exclui uma tarefa específica pelo ID.

3. Gestão de Finanças (Requer JWT)

GET	/api/transactions	Retorna a lista de todas as transações do utilizador autenticado.
POST	/api/transactions	Cadastra uma nova transação.
GET	/api/transactions/{id}	Retorna os detalhes de uma transação específica pelo ID.
PUT	/api/transactions/{id}	Atualiza uma transação específica pelo ID.
DELETE	/api/transactions/{id}	Exclui uma transação específica pelo ID.

4. Relatórios e Gráficos (Requer JWT)

GET	/api/reports/monthly	year (int), month (int)	Retorna um relatório financeiro mensal detalhado.
GET	/api/reports/summary	Nenhum	Retorna um resumo financeiro geral (saldos, total de receitas/despesas).


É necessário inserir dados no BD, para transactions


-- Insere as novas categorias
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Transporte');
INSERT INTO category (name) VALUES ('Lazer');

### 1. Clonando o Repositório

Para iniciar o projeto, clone este repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/task-finance-management-system.git
cd task-finance-management-system
