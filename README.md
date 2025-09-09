Task Finance Management System API

Este projeto fornece uma API para o gerenciamento de tarefas e finanças, permitindo que usuários possam gerenciar suas tarefas de forma simples e eficiente. A API inclui recursos como autenticação de usuários, gerenciamento de usuários, e a interação com dados de tarefas e finanças.

Tecnologias Utilizadas

Spring Boot: Framework para desenvolvimento da aplicação.

Spring Security: Segurança e autenticação de usuários.

Spring Data JPA: Integração com banco de dados PostgreSQL.

PostgreSQL: Banco de dados utilizado.

BCrypt: Criptografia de senhas.

Spring Web: Para construção de endpoints RESTful.

Endpoints da API
1. POST /login

Descrição: Endpoint de login para autenticar usuários.

Parâmetros de entrada:

username: Nome de usuário.

password: Senha do usuário.

Resposta:

200 OK: Se o login for bem-sucedido, o usuário será redirecionado para a página principal.

403 Forbidden: Se o login falhar (usuário ou senha inválidos).

2. GET /usuarios

Descrição: Endpoint para listar todos os usuários cadastrados.

Autenticação: Requer autenticação.

Resposta:

200 OK: Retorna uma lista com os dados dos usuários cadastrados.

401 Unauthorized: Caso não esteja autenticado.

3. GET /usuarios/{id}

Descrição: Endpoint para recuperar um usuário específico pelo ID.

Parâmetros de entrada:

id: O ID do usuário que se deseja obter.

Resposta:

200 OK: Retorna os detalhes do usuário.

404 Not Found: Se o usuário com o ID não for encontrado.

4. POST /usuarios

Descrição: Endpoint para cadastrar um novo usuário.

Parâmetros de entrada:

email: O email do usuário.

nome: Nome completo do usuário.

senha: Senha do usuário.

Resposta:

201 Created: Usuário criado com sucesso.

400 Bad Request: Se algum campo obrigatório estiver ausente.

5. POST /logout

Descrição: Endpoint para fazer o logout do sistema.

Resposta:

200 OK: Se o logout for bem-sucedido.

Instruções de Execução
1. Clonando o Repositório

Para iniciar o projeto, clone este repositório para sua máquina local:

git clone https://github.com/seu-usuario/task-finance-management-system.git
cd task-finance-management-system

2. Instalando as Dependências

Certifique-se de que o Java
 (JDK 11 ou superior) e o Maven
 estão instalados no seu ambiente de desenvolvimento.

3. Configurando o Banco de Dados

Certifique-se de que o PostgreSQL está instalado e em execução. Crie um banco de dados com o nome taskfinance_db ou altere o arquivo application.properties para utilizar um banco de dados diferente.

spring.datasource.url=jdbc:postgresql://localhost:5432/taskfinance_db
spring.datasource.username=postgres
spring.datasource.password=postgres

4. Rodando a Aplicação

Para rodar a aplicação, execute o seguinte comando:

mvn spring-boot:run


Ou, se preferir, você pode empacotar o projeto e rodar o arquivo JAR:

mvn clean package
java -jar target/task-finance-management-system-0.0.1-SNAPSHOT.jar


A API estará disponível em http://localhost:8080
.

Segurança

A API utiliza autenticação básica com nome de usuário admin e senha admin123. As senhas dos usuários são armazenadas de forma segura utilizando o algoritmo de criptografia BCrypt.

Notas

O CSRF (Cross-Site Request Forgery) está habilitado por padrão no Spring Security. Ao interagir com os endpoints de autenticação, é necessário fornecer o token CSRF.

Em ambientes de produção, é recomendável configurar HTTPS e ajustar as configurações de segurança para proteger melhor os dados dos usuários.

Contribuições

Contribuições são bem-vindas! Se você encontrou algum bug ou tem sugestões de melhorias, sinta-se à vontade para abrir um issue ou enviar um pull request.
