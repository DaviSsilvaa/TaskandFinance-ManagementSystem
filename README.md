Task Finance Management System API
<p>API para gestão de <strong>tarefas</strong> e <strong>finanças</strong>, com autenticação via <strong>JWT</strong>. Permite cadastro/login de usuários, CRUD de tarefas e transações, e relatórios financeiros.</p>
✨ Funcionalidades
<p>• Autenticação e registro de usuários (JWT + BCrypt)<br/> • CRUD de <strong>Tarefas</strong><br/> • CRUD de <strong>Transações</strong> com <strong>Categorias</strong><br/> • Relatórios: <strong>Mensal</strong> e <strong>Resumo geral</strong><br/> • Endpoints REST com <strong>Spring Web</strong><br/> • Persistência com <strong>Spring Data JPA</strong> (PostgreSQL)</p>
🧰 Tecnologias
<p><strong>Java 21</strong> • <strong>Spring Boot 3.5.x</strong> • <strong>Spring Security</strong> • <strong>Spring Data JPA</strong> • <strong>PostgreSQL</strong> • <strong>BCrypt</strong> • <strong>Maven</strong></p>
🚀 Como executar
1) Pré-requisitos
<p>• Java 21<br/> • Maven 3.9+<br/> • PostgreSQL em execução</p>
2) Configurar o banco
CREATE DATABASE taskfinance;
CREATE USER taskfinance_user WITH ENCRYPTED PASSWORD 'strong_password';
GRANT ALL PRIVILEGES ON DATABASE taskfinance TO taskfinance_user;

3) Configurar a aplicação
<p>Use <code>application.properties</code> <em>ou</em> <code>application.yml</code>:</p>
# application.properties
spring.datasource.url=jdbc:postgresql://localhost:5432/taskfinance
spring.datasource.username=taskfinance_user
spring.datasource.password=strong_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

4) Rodar a aplicação
Executar TaskFinanceApplication

<p>A API sobe em: <code>http://localhost:8080</code></p>
🧪 Dados iniciais (categorias)
<p>Opcional: insira categorias para usar nas transações.</p>
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Transporte');
INSERT INTO category (name) VALUES ('Lazer');

🔐 Autenticação (JWT)
<p>Envie o token em cada requisição protegida:</p>
Authorization: Bearer <seu_token_jwt>

Registro
<p><code>POST /api/auth/register</code></p>
{
  "email": "novo.usuario@exemplo.com",
  "password": "senhaSegura123",
  "name": "Nome do Utilizador"
}

Login
<p><code>POST /api/auth/login</code></p>
{
  "email": "novo.usuario@exemplo.com",
  "password": "senhaSegura123"
}

<p><strong>Resposta (exemplo):</strong></p>
{
  "token": "jwt_aqui",
  "type": "Bearer"
}

📚 Endpoints
1) Tarefas (requer JWT)
<p> GET <code>/api/tasks</code> — Lista tarefas do usuário<br/> POST <code>/api/tasks</code> — Cria tarefa<br/> GET <code>/api/tasks/{id}</code> — Detalha tarefa<br/> PUT <code>/api/tasks/{id}</code> — Atualiza tarefa<br/> DELETE <code>/api/tasks/{id}</code> — Exclui tarefa </p> <p><strong>Exemplo (criar):</strong></p>
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
<p> GET <code>/api/transactions</code> — Lista transações<br/> POST <code>/api/transactions</code> — Cadastra transação<br/> GET <code>/api/transactions/{id}</code> — Detalha transação<br/> PUT <code>/api/transactions/{id}</code> — Atualiza transação<br/> DELETE <code>/api/transactions/{id}</code> — Exclui transação </p> <p><strong>Exemplo (criar):</strong></p>
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

<p><em>Observação:</em> garanta que o <code>categoryId</code> exista (veja o SQL acima).</p>
3) Relatórios (requer JWT)
<p> GET <code>/api/reports/monthly?year={int}&month={int}</code> — Relatório do mês<br/> GET <code>/api/reports/summary</code> — Resumo geral (saldo, total receitas/despesas) </p>
# Mensal de Setembro/2025
curl -H "Authorization: Bearer <TOKEN>" \
  "http://localhost:8080/api/reports/monthly?year=2025&month=9"

# Resumo geral
  http://localhost:8080/swagger-ui/index.html

🧱 Estrutura do projeto (sugerida)
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
