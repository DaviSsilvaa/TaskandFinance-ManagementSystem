<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Task Finance Management System API</title>
</head>
<body>
    <h1>Task Finance Management System API</h1>
    <p>Este projeto fornece uma API para o gerenciamento de tarefas e finanças, permitindo que usuários possam gerenciar suas tarefas de forma simples e eficiente. A API inclui recursos como autenticação de usuários, gerenciamento de usuários, e a interação com dados de tarefas e finanças.</p>

    <h2>Tecnologias Utilizadas</h2>
    <ul>
        <li><strong>Spring Boot</strong>: Framework para desenvolvimento da aplicação.</li>
        <li><strong>Spring Security</strong>: Segurança e autenticação de usuários.</li>
        <li><strong>Spring Data JPA</strong>: Integração com banco de dados PostgreSQL.</li>
        <li><strong>PostgreSQL</strong>: Banco de dados utilizado.</li>
        <li><strong>BCrypt</strong>: Criptografia de senhas.</li>
        <li><strong>Spring Web</strong>: Para construção de endpoints RESTful.</li>
    </ul>

    <h2>Endpoints da API</h2>

    <h3>1. POST /login</h3>
    <p><strong>Descrição</strong>: Endpoint de login para autenticar usuários.</p>
    <p><strong>Parâmetros de entrada:</strong></p>
    <ul>
        <li><strong>username</strong>: Nome de usuário.</li>
        <li><strong>password</strong>: Senha do usuário.</li>
    </ul>
    <p><strong>Resposta:</strong></p>
    <ul>
        <li><strong>200 OK</strong>: Se o login for bem-sucedido, o usuário será redirecionado para a página principal.</li>
        <li><strong>403 Forbidden</strong>: Se o login falhar (usuário ou senha inválidos).</li>
    </ul>

    <h3>2. GET /usuarios</h3>
    <p><strong>Descrição</strong>: Endpoint para listar todos os usuários cadastrados.</p>
    <p><strong>Autenticação:</strong> Requer autenticação.</p>
    <p><strong>Resposta:</strong></p>
    <ul>
        <li><strong>200 OK</strong>: Retorna uma lista com os dados dos usuários cadastrados.</li>
        <li><strong>401 Unauthorized</strong>: Caso não esteja autenticado.</li>
    </ul>

    <h3>3. GET /usuarios/{id}</h3>
    <p><strong>Descrição</strong>: Endpoint para recuperar um usuário específico pelo ID.</p>
    <p><strong>Parâmetros de entrada:</strong></p>
    <ul>
        <li><strong>id</strong>: O ID do usuário que se deseja obter.</li>
    </ul>
    <p><strong>Resposta:</strong></p>
    <ul>
        <li><strong>200 OK</strong>: Retorna os detalhes do usuário.</li>
        <li><strong>404 Not Found</strong>: Se o usuário com o ID não for encontrado.</li>
    </ul>

    <h3>4. POST /usuarios</h3>
    <p><strong>Descrição</strong>: Endpoint para cadastrar um novo usuário.</p>
    <p><strong>Parâmetros de entrada:</strong></p>
    <ul>
        <li><strong>email</strong>: O email do usuário.</li>
        <li><strong>nome</strong>: Nome completo do usuário.</li>
        <li><strong>senha</strong>: Senha do usuário.</li>
    </ul>
    <p><strong>Resposta:</strong></p>
    <ul>
        <li><strong>201 Created</strong>: Usuário criado com sucesso.</li>
        <li><strong>400 Bad Request</strong>: Se algum campo obrigatório estiver ausente.</li>
    </ul>

    <h3>5. POST /logout</h3>
    <p><strong>Descrição</strong>: Endpoint para fazer o logout do sistema.</p>
    <p><strong>Resposta:</strong></p>
    <ul>
        <li><strong>200 OK</strong>: Se o logout for bem-sucedido.</li>
    </ul>

    <h2>Instruções de Execução</h2>

    <h3>1. Clonando o Repositório</h3>
    <p>Para iniciar o projeto, clone este repositório para sua máquina local:</p>
    <pre>
git clone https://github.com/seu-usuario/task-finance-management-system.git
cd task-finance-management-system
    </pre>

    <h3>2. Instalando as Dependências</h3>
    <p>Certifique-se de que o <strong>Java</strong> (JDK 11 ou superior) e o <strong>Maven</strong> estão instalados no seu ambiente de desenvolvimento.</p>

    <h3>3. Configurando o Banco de Dados</h3>
    <p>Certifique-se de que o <strong>PostgreSQL</strong> está instalado e em execução. Crie um banco de dados com o nome <code>taskfinance_db</code> ou altere o arquivo <code>application.properties</code> para utilizar um banco de dados diferente.</p>
    <pre>
spring.datasource.url=jdbc:postgresql://localhost:5432/taskfinance_db
spring.datasource.username=postgres
spring.datasource.password=postgres
    </pre>

    <h3>4. Rodando a Aplicação</h3>
    <p>Para rodar a aplicação, execute o seguinte comando:</p>
    <pre>
mvn spring-boot:run
    </pre>
    <p>Ou, se preferir, você pode empacotar o projeto e rodar o arquivo JAR:</p>
    <pre>
mvn clean package
java -jar target/task-finance-management-system-0.0.1-SNAPSHOT.jar
    </pre>
    <p>A API estará disponível em <a href="http://localhost:8080">http://localhost:8080</a>.</p>

    <h2>Segurança</h2>
    <p>A API utiliza autenticação básica com nome de usuário <code>admin</code> e senha <code>admin123</code>. As senhas dos usuários são armazenadas de forma segura utilizando o algoritmo de criptografia <strong>BCrypt</strong>.</p>

    <h2>Notas</h2>
    <ul>
        <li>O <strong>CSRF</strong> (Cross-Site Request Forgery) está habilitado por padrão no Spring Security. Ao interagir com os endpoints de autenticação, é necessário fornecer o token CSRF.</li>
        <li>Em ambientes de produção, é recomendável configurar <strong>HTTPS</strong> e ajustar as configurações de segurança para proteger melhor os dados dos usuários.</li>
    </ul>

    <h2>Contribuições</h2>
    <p>Contribuições são bem-vindas! Se você encontrou algum bug ou tem sugestões de melhorias, sinta-se à vontade para abrir um <strong>issue</strong> ou enviar um <strong>pull request</strong>.</p>

    <h2>Licença</h2>
    <p>Este projeto está sob a licença <strong>MIT</strong>. Veja o arquivo <code>LICENSE</code> para mais detalhes.</p>

</body>
</html>
