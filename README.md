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

Apenas rodar o TaskFinanceApplication

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


### 1. Clonando o Repositório

Para iniciar o projeto, clone este repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/task-finance-management-system.git
cd task-finance-management-system
