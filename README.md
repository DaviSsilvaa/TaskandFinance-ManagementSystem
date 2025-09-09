# Task Finance Management System API

Este projeto fornece uma API para o gerenciamento de tarefas e finanças, permitindo que usuários possam gerenciar suas tarefas de forma simples e eficiente. A API inclui recursos como autenticação de usuários, gerenciamento de usuários, e a interação com dados de tarefas e finanças.

## Tecnologias Utilizadas

- **Spring Boot**: Framework para desenvolvimento da aplicação.
- **Spring Security**: Segurança e autenticação de usuários.
- **Spring Data JPA**: Integração com banco de dados PostgreSQL.
- **PostgreSQL**: Banco de dados utilizado.
- **BCrypt**: Criptografia de senhas.
- **Spring Web**: Para construção de endpoints RESTful.

## Endpoints da API

### 1. POST /login

**Descrição**: Endpoint de login para autenticar usuários.  
**Parâmetros de entrada**:
- `username`: Nome de usuário.
- `password`: Senha do usuário.

**Respostas**:
- `200 OK`: Se o login for bem-sucedido, o usuário será redirecionado para a página principal.
- `403 Forbidden`: Se o login falhar (usuário ou senha inválidos).

---

### 2. GET /usuarios

**Descrição**: Endpoint para listar todos os usuários cadastrados.  
**Requer autenticação.**

**Respostas**:
- `200 OK`: Retorna uma lista com os dados dos usuários cadastrados.
- `401 Unauthorized`: Caso não esteja autenticado.

---

### 3. GET /usuarios/{id}

**Descrição**: Endpoint para recuperar um usuário específico pelo ID.  
**Parâmetros de entrada**:
- `id`: O ID do usuário que se deseja obter.

**Respostas**:
- `200 OK`: Retorna os detalhes do usuário.
- `404 Not Found`: Se o usuário com o ID não for encontrado.

---

### 4. POST /usuarios

**Descrição**: Endpoint para cadastrar um novo usuário.  
**Parâmetros de entrada**:
- `email`: O email do usuário.
- `nome`: Nome completo do usuário.
- `senha`: Senha do usuário.

**Respostas**:
- `201 Created`: Usuário criado com sucesso.
- `400 Bad Request`: Se algum campo obrigatório estiver ausente.

---

### 5. POST /logout

**Descrição**: Endpoint para fazer o logout do sistema.

**Respostas**:
- `200 OK`: Se o logout for bem-sucedido.

---

## Instruções de Execução

### 1. Clonando o Repositório

Para iniciar o projeto, clone este repositório para sua máquina local:

```bash
git clone https://github.com/seu-usuario/task-finance-management-system.git
cd task-finance-management-system
