Task Finance Management System API

API para gestão de tarefas e finanças, com autenticação via JWT. Permite cadastro/login de usuários, CRUD de tarefas e transações, e relatórios financeiros.

✨ Funcionalidades

Autenticação e registro de usuários (JWT + BCrypt)

CRUD de Tarefas

CRUD de Transações com Categorias

Relatórios: Mensal e Resumo geral

Endpoints REST com Spring Web

Persistência com Spring Data JPA (PostgreSQL)

🧰 Tecnologias

Java 21

Spring Boot 3.5.x

Spring Security

Spring Data JPA

PostgreSQL

BCrypt

Maven

🚀 Como executar
1) Pré-requisitos

Java 21

Maven 3.9+

PostgreSQL em execução

2) Configurar o banco

Crie um banco e um usuário (exemplo):

CREATE DATABASE taskfinance;
CREATE USER taskfinance_user WITH ENCRYPTED PASSWORD 'strong_password';
GRANT ALL PRIVILEGES ON DATABASE taskfinance TO taskfinance_user;

3) Configurar aplicação

Se usar application.properties:

spring.datasource.url=jdbc:postgresql://localhost:5432/taskfinance
spring.datasource.username=taskfinance_user
spring.datasource.password=strong_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

# JWT (exemplo)
app.jwt.secret=troque-por-uma-chave-secreta-grande
app.jwt.expiration=86400000


Se preferir application.yml:

spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/taskfinance
    username: taskfinance_user
    password: strong_password
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true

app:
  jwt:
    secret: troque-por-uma-chave-secreta-grande
    expiration: 86400000

4) Rodar a aplicação

Via Maven:

mvn spring-boot:run


Ou com wrapper:

./mvnw spring-boot:run


A API sobe em: http://localhost:8080

🧪 Populando categorias iniciais (opcional)

Insira algumas categorias para usar nas transações:

INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Transporte');
INSERT INTO category (name) VALUES ('Lazer');

🔐 Autenticação

A autenticação é feita via JWT. Obtenha um token no login e envie em cada requisição protegida:

Authorization: Bearer <seu_token_jwt>

Registro

POST /api/auth/register

{
  "email": "novo.usuario@exemplo.com",
  "password": "senhaSegura123",
  "name": "Nome do Utilizador"
}

Login

POST /api/auth/login

{
  "email": "novo.usuario@exemplo.com",
  "password": "senhaSegura123"
}


Resposta esperada (exemplo):

{
  "token": "jwt_aqui",
  "type": "Bearer"
}

📚 Endpoints
1) Tarefas (requer JWT)

GET /api/tasks — Lista todas as tarefas do usuário autenticado

POST /api/tasks — Cria nova tarefa

GET /api/tasks/{id} — Detalha uma tarefa

PUT /api/tasks/{id} — Atualiza uma tarefa

DELETE /api/tasks/{id} — Exclui uma tarefa

Exemplo de criação:

curl -X POST http://localhost:8080/api/tasks \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "title": "Estudar Spring Security",
    "description": "Revisar filtros e JWT",
    "dueDate": "2025-10-05",
    "status": "PENDING"
  }'

2) Transações (requer JWT)

GET /api/transactions — Lista transações do usuário

POST /api/transactions — Cadastra transação

GET /api/transactions/{id} — Detalha transação

PUT /api/transactions/{id} — Atualiza transação

DELETE /api/transactions/{id} — Exclui transação

Exemplo de criação:

curl -X POST http://localhost:8080/api/transactions \
  -H "Content-Type: application/json" \
  -H "Authorization: Bearer <TOKEN>" \
  -d '{
    "amount": 120.50,
    "type": "EXPENSE",
    "date": "2025-09-29",
    "categoryId": 1,
    "description": "Almoço"
  }'


Observação: Garanta que categoryId exista (veja as inserções SQL acima).

3) Relatórios (requer JWT)

GET /api/reports/monthly?year={int}&month={int} — Relatório financeiro do mês

GET /api/reports/summary — Resumo geral (saldo, total receitas/despesas)

Exemplos:

# Mensal de Setembro/2025
curl -H "Authorization: Bearer <TOKEN>" \
  "http://localhost:8080/api/reports/monthly?year=2025&month=9"

# Resumo geral
curl -H "Authorization: Bearer <TOKEN>" \
  http://localhost:8080/api/reports/summary

🧱 Estrutura (sugerida)
src/
  main/
    java/
      org/example/taskfinancemanagementsystem/
        TaskFinanceApplication.java
        config/          # Security, JWT filters, CORS
        controller/      # Controllers REST
        dto/             # DTOs de request/response
        model/           # Entidades JPA (Usuario, Tarefa, Transacao, Category)
        repository/      # Interfaces JPA
        service/         # Regra de negócio
        security/        # JwtUtil, JwtFilter, UserDetailsService
    resources/
      application.properties|yml

🧷 Boas práticas & notas

Senhas são armazenadas com BCrypt

Garanta que o JWT secret seja seguro e privado (não versionar em repositório público)

Use profiles (application-dev.yml, application-prod.yml) se necessário

Em produção, configurar:

CORS restrito

HTTPS (proxy ou server TLS)

Migrations (Flyway/Liquibase) para versionar o schema
