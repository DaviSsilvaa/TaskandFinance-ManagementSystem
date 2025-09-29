<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Documentação da API - Sistema de Gestão de Tarefas e Finanças</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Inter:wght@400;500;600;700&display=swap');
        body {
            font-family: 'Inter', sans-serif;
        }
        .prose h2 {
            font-size: 1.75rem;
            font-weight: 700;
            margin-top: 2.5rem;
            margin-bottom: 1.5rem;
            border-bottom: 1px solid #e5e7eb;
            padding-bottom: 0.5rem;
        }
        .prose h3 {
            font-size: 1.25rem;
            font-weight: 600;
            margin-top: 2rem;
            margin-bottom: 1rem;
        }
        .prose code {
            background-color: #f3f4f6;
            color: #1f2937;
            padding: 0.2rem 0.4rem;
            border-radius: 0.25rem;
            font-size: 0.9em;
        }
        .prose pre {
            background-color: #1f2937;
            color: #f9fafb;
            padding: 1rem;
            border-radius: 0.5rem;
            overflow-x: auto;
        }
        .prose pre code {
            background-color: transparent;
            color: inherit;
            padding: 0;
        }
        .prose table {
            width: 100%;
            margin-top: 1.5rem;
            border-collapse: collapse;
        }
        .prose th, .prose td {
            border: 1px solid #d1d5db;
            padding: 0.75rem 1rem;
            text-align: left;
        }
        .prose th {
            background-color: #f9fafb;
            font-weight: 600;
        }
    </style>
</head>
<body class="bg-gray-50 text-gray-800">

    <div class="container mx-auto max-w-4xl py-12 px-4">
        <header class="text-center mb-12">
            <h1 class="text-5xl font-extrabold text-gray-900">Sistema de Gestão de Tarefas e Finanças - API</h1>
            <p class="mt-4 text-lg text-gray-600">Uma API RESTful completa para gerenciar tarefas e finanças pessoais com Spring Boot.</p>
        </header>

        <main class="bg-white p-8 sm:p-12 rounded-2xl shadow-lg prose prose-lg max-w-none">
            
            <section id="funcionalidades">
                <h2>Funcionalidades Principais</h2>
                <ul>
                    <li><strong>Autenticação Segura:</strong> Sistema de registro e login com autenticação baseada em JWT (JSON Web Tokens).</li>
                    <li><strong>Gestão de Tarefas:</strong> Crie, liste, atualize e apague tarefas com títulos, descrições, prazos e prioridades.</li>
                    <li><strong>Controle Financeiro:</strong> Cadastre receitas e despesas, associe transações a categorias e monitore suas finanças.</li>
                    <li><strong>Relatórios:</strong> Gere relatórios financeiros mensais e resumos gerais para visualizar seus gastos.</li>
                    <li><strong>Segurança:</strong> Acesso a dados pessoais protegido e restrito a cada usuário autenticado.</li>
                </ul>
            </section>

            <section id="tecnologias">
                <h2>Tecnologias Utilizadas</h2>
                <h3>Backend:</h3>
                <ul>
                    <li><strong>Spring Boot:</strong> Framework principal para o desenvolvimento da aplicação.</li>
                    <li><strong>Spring Security:</strong> Para implementação da segurança e autenticação de usuários.</li>
                    <li><strong>Spring Data JPA:</strong> Para a camada de persistência e integração com o banco de dados.</li>
                    <li><strong>JWT (JSON Web Tokens):</strong> Para a autenticação stateless da API.</li>
                </ul>
                <h3>Banco de Dados:</h3>
                <ul>
                    <li><strong>PostgreSQL:</strong> Banco de dados relacional utilizado para armazenar os dados.</li>
                </ul>
                <h3>Segurança de Senhas:</h3>
                <ul>
                    <li><strong>BCrypt:</strong> Algoritmo para criptografia segura de senhas.</li>
                </ul>
                <h3>Documentação e Build:</h3>
                <ul>
                    <li><strong>Springdoc-openapi (Swagger):</strong> Geração de documentação interativa para a API.</li>
                    <li><strong>Maven:</strong> Gerenciador de dependências e build do projeto.</li>
                </ul>
            </section>
            
            <section id="instalacao">
                <h2>Guia de Instalação e Execução</h2>

                <h3>1. Pré-requisitos</h3>
                <ul>
                    <li>Java JDK 21 ou superior.</li>
                    <li>Maven 3.8 ou superior.</li>
                    <li>PostgreSQL instalado e em execução.</li>
                    <li>Um cliente de API como o Postman ou o Insomnia para testar os endpoints.</li>
                </ul>

                <h3>2. Configuração do Banco de Dados</h3>
                <ol>
                    <li>
                        <strong>Crie um Banco de Dados:</strong>
                        <p>No PostgreSQL, crie uma nova base de dados. Por exemplo, com o nome <code>task_finance</code>.</p>
                    </li>
                    <li>
                        <strong>Configure a Conexão:</strong>
                        <p>Abra o ficheiro <code>src/main/resources/application.properties</code> e atualize as seguintes propriedades:</p>
                        <pre><code>spring.datasource.url=jdbc:postgresql://localhost:5432/task_finance
spring.datasource.username=seu_usuario_postgres
spring.datasource.password=sua_senha_postgres</code></pre>
                    </li>
                    <li>
                        <strong>Popule as Categorias Iniciais (Obrigatório):</strong>
                        <p>Execute o seguinte script SQL na sua base de dados:</p>
                        <pre><code>-- Insere as novas categorias
INSERT INTO category (name) VALUES ('Alimentação');
INSERT INTO category (name) VALUES ('Transporte');
INSERT INTO category (name) VALUES ('Lazer');
INSERT INTO category (name) VALUES ('Moradia');
INSERT INTO category (name) VALUES ('Salário');</code></pre>
                    </li>
                </ol>

                <h3>3. Clonando o Repositório</h3>
                <p>Clone este repositório para sua máquina local:</p>
                <pre><code>git clone https://github.com/seu-usuario/task-finance-management-system.git
cd task-finance-management-system</code></pre>

                <h3>4. Executando a Aplicação</h3>
                <p>Você pode executar a aplicação de duas formas:</p>
                <p><strong>Via Terminal (com Maven Wrapper):</strong></p>
                <pre><code>./mvnw spring-boot:run</code></pre>
                <p><strong>Pela sua IDE (IntelliJ/Eclipse):</strong></p>
                <ul>
                    <li>Importe o projeto como um projeto Maven.</li>
                    <li>Encontre a classe <code>TaskFinanceApplication.java</code> e execute o método `main`.</li>
                </ul>
                <p>A API estará disponível em <code>http://localhost:8080</code>.</p>
            </section>
            
            <section id="uso-api">
                <h2>Como Usar a API</h2>

                <h3>1. Autenticação</h3>
                <p>Primeiro, crie um usuário e faça login para obter um token JWT.</p>
                <p><strong><code>POST /api/auth/register</code></strong> - Cria um novo usuário.</p>
                <pre><code>{
    "name": "Nome do Utilizador",
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123"
}</code></pre>

                <p><strong><code>POST /api/auth/login</code></strong> - Autentica um usuário e retorna um token JWT.</p>
                <pre><code>{
    "email": "novo.usuario@exemplo.com",
    "password": "senhaSegura123"
}</code></pre>
                <p><strong>Resposta:</strong> Você receberá um token. Copie este token para usar nas requisições seguintes.</p>

                <h3>2. Autorização</h3>
                <p>Para aceder aos endpoints protegidos, inclua o token JWT no cabeçalho <code>Authorization</code> de cada requisição:<br><code>Authorization: Bearer &lt;SEU_TOKEN_JWT_AQUI&gt;</code></p>
                
                <h3>3. Endpoints da API (Requerem JWT)</h3>
                
                <h4>Gestão de Tarefas</h4>
                <table>
                    <thead>
                        <tr>
                            <th>Método</th>
                            <th>Endpoint</th>
                            <th>Descrição</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><code>GET</code></td>
                            <td><code>/api/tasks</code></td>
                            <td>Retorna todas as tarefas do usuário.</td>
                        </tr>
                        <tr>
                            <td><code>POST</code></td>
                            <td><code>/api/tasks</code></td>
                            <td>Cria uma nova tarefa.</td>
                        </tr>
                        <tr>
                            <td><code>GET</code></td>
                            <td><code>/api/tasks/{id}</code></td>
                            <td>Retorna os detalhes de uma tarefa.</td>
                        </tr>
                        <tr>
                            <td><code>PUT</code></td>
                            <td><code>/api/tasks/{id}</code></td>
                            <td>Atualiza uma tarefa.</td>
                        </tr>
                        <tr>
                            <td><code>DELETE</code></td>
                            <td><code>/api/tasks/{id}</code></td>
                            <td>Exclui uma tarefa.</td>
                        </tr>
                    </tbody>
                </table>
                
                <h4>Gestão de Finanças</h4>
                <table>
                    <thead>
                        <tr>
                            <th>Método</th>
                            <th>Endpoint</th>
                            <th>Descrição</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><code>GET</code></td>
                            <td><code>/api/transactions</code></td>
                            <td>Retorna todas as transações do usuário.</td>
                        </tr>
                        <tr>
                            <td><code>POST</code></td>
                            <td><code>/api/transactions</code></td>
                            <td>Cadastra uma nova transação.</td>
                        </tr>
                        <tr>
                            <td><code>GET</code></td>
                            <td><code>/api/transactions/{id}</code></td>
                            <td>Retorna os detalhes de uma transação.</td>
                        </tr>
                        <tr>
                            <td><code>PUT</code></td>
                            <td><code>/api/transactions/{id}</code></td>
                            <td>Atualiza uma transação.</td>
                        </tr>
                         <tr>
                            <td><code>DELETE</code></td>
                            <td><code>/api/transactions/{id}</code></td>
                            <td>Exclui uma transação.</td>
                        </tr>
                    </tbody>
                </table>
                
                <h4>Relatórios</h4>
                <table>
                    <thead>
                        <tr>
                            <th>Método</th>
                            <th>Endpoint</th>
                            <th>Descrição</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><code>GET</code></td>
                            <td><code>/api/reports/summary</code></td>
                            <td>Retorna um resumo financeiro geral.</td>
                        </tr>
                        <tr>
                            <td><code>GET</code></td>
                            <td><code>/api/reports/monthly</code></td>
                            <td>Retorna um relatório mensal (ex: <code>?month=2025-09</code>).</td>
                        </tr>
                    </tbody>
                </table>

            </section>
        </main>
        
        <footer class="text-center mt-12 text-gray-500">
            <p>Documentação gerada para o projeto de Gestão de Tarefas e Finanças.</p>
        </footer>

    </div>

</body>
</html>
