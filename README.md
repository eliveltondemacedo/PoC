## Fluxo
1. Cliente autenticar credenciais de login com o Auth Service, se as credenciais de login estiverem corretas, ele irá gerar o Token JWT e enviá-lo de volta ao cliente no cabeçalho, bem como na resposta, por isso estamos cuidando da Autenticação.

2. O mesmo token JWT que precisamos enviar em cada solicitação para acessar microsserviços, cada microsserviço validará o token e também cuidará da Autorização.

! [texto alternativo] (https://github.com/kuldeepsingh99/authorization-authentication/blob/master/images/AuthFlow.png "Auth Flow")

# Auth: - recursos de serviço #

Este serviço valida as credenciais do usuário e gera o token JWT

1. Serviço de Login (geração do token JWT)
2. Refresh Service (gerando novo token JWT)
3. Serviço de Registro (Adicionando novo usuário)

# Customer: - Recursos do projeto #

Este serviço valida o token JWT e também cuida da autenticação

## Passos para instalar ##

1. Execute o tables.sql no MYSQL DB, ele criará o banco de dados e duas tabelas.
2. Importe o projeto maven para eclipse ou qualquer outra ferramenta.
3. Execute o mvn clean install um por um nos dois projetos.
4. Em Auth Project, execute este arquivo ** com.portal.auto.SpringBootWebApplication.java ** (ele iniciará o servidor na porta 8080)
5. Em Customer Project, execute este arquivo ** com.portal.auto.CustomerApplication.java ** (ele iniciará o servidor na porta 9090)

# End Points #

### Register ###

POST /register HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 6c4fb8a3-8eae-c3b7-7cfb-3afa56d1acd5

**{"name":"hello","password":"123456","confirmPassword":"123456"}**


Na resposta, você receberá uma mensagem JSON de Sucesso ou Falha.

Ex.
{
  "flag": "success",
  "role": null,
  "message": " Usuário registrado com sucesso, por favor, faça o login para continuar"
}


Após o registro, você notará que ele criará um registro na tabela de usuários com o padrão ROLE e também criará dois registros na tabela usertoken com dois usertype diferentes (WEB, ANDRIOD).

** Nota: - Precisamos armazenar o token para diferentes dispositivos separadamente, podemos modificar esse fluxo se precisarmos. **

### Login ###

POST /login HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 6e8ba103-3a95-83c3-7a95-694ec2cbfa12

**{"username":"hello","password":"123456","userType":"WEB"}**

Response
{
    "flag": "sucesso",
    "role": "ROLE_USER",
    "message": "eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiI2ZTdjMmU1ZmI0MjY0NDc5YjY2ZmUyOGVhOWUyMDZiZiIsInN1YiI6ImFwb3N0ZWsiLCJpYXQiOjE1MTIxMTcyNTMsImV4cCI6MTUxMjExNzMxMywiaXNzIjoia3VsZGVlcC5jb20iLCJyb2xlIjoiUk9MRV9VU0VSIn0.e-Qdw5H8FpWpN7cNrZxDt7XS7hjL8CH7w9gExxEJzeHNSKYL9RTafWcGO9yhXB3-R3xjKMjmq2gva0fXMJIOOg"
}

Ele retornará o token de acesso na mensagem de resposta, bem como no cabeçalho também.

### Refresh ### 

POST /refresh HTTP/1.1
Host: localhost:8080
Content-Type: application/json
Cache-Control: no-cache
Postman-Token: 79508fec-630b-c5fb-3a11-3fd65105f1d0

{"username":"hello","token":"eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiIxZDk4NGI2MTJmYjc0ZDg0YjBjZDEyM2ZlNzIzMDQ0NCIsInN1YiI6ImFkbWluIiwiaWF0IjoxNTEyMTE2MTk1LCJleHAiOjE1MTIxMTYyNTUsImlzcyI6Imt1bGRlZXAuY29tIiwicm9sZSI6IkFETUlOIn0.Ni6c9I12_QI634quXMl_OE1buucV92yLv5tj4bXL-j5KfMk68K3z4r7Q1pHQedMKPllWOvL0Ixw71JH0PWj9Hw","userType":"WEB"}


Aqui, precisamos enviar o token atual junto com o usuário. Caso contrário, você não poderá receber um novo token.









