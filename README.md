📌 CRUD Java com MySQL

Este projeto é um CRUD simples em Java, utilizando MySQL como banco de dados e o padrão DAO para organizar e separar as responsabilidades.
O projeto funciona totalmente pelo console, permitindo:

✔ Inserir usuários
✔ Listar todos
✔ Buscar por ID
✔ Atualizar
✔ Deletar

🚀 Tecnologias Utilizadas

Java (JDK 17+ ou superior)

MySQL Server

MySQL Connector/J (driver JDBC)

Eclipse ou VSCode (opcional)

DAO Pattern

🗂 Estrutura do Projeto
src/
 ├── application/
 │     └── Program.java
 ├── db/
 │     ├── DB.java
 │     └── DbException.java
 ├── model/
 │     ├── dao/
 │     │     ├── DaoFactory.java
 │     │     └── UserDao.java
 │     ├── dao/impl/
 │     │     └── UserDaoJDBC.java
 │     └── entities/
 │           └── User.java

🛢 Configuração do Banco de Dados (MySQL)
1. Criar banco:
CREATE DATABASE crudjava;
USE crudjava;

2. Criar tabela:
CREATE TABLE user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    email VARCHAR(120) NOT NULL
);

3. Configurar conexão (db.properties):

Crie um arquivo com:

user=SEU_USUARIO
password=SUA_SENHA
dburl=jdbc:mysql://localhost:3306/crudjava
useSSL=false

▶ Como Executar

Instale o MySQL e verifique se o serviço está rodando.

Crie o banco e a tabela.

Baixe o MySQL Connector/J e coloque na pasta lib/.

Adicione o .jar ao Build Path.

Compile e execute o Program.java.

🔧 Funcionalidades
➕ Inserir Usuário

Você digita nome e email → é salvo no MySQL.

📄 Listar Usuários

Lista todos os registros da tabela.

🔍 Buscar por ID

Retorna um único usuário.

✏ Atualizar Usuário

Modifica nome e email baseado no ID.

❌ Deletar Usuário

Remove o registro pelo ID.

📷 Exemplo de execução
==================================
        SISTEMA DE USUÁRIOS
==================================
1 - Inserir
2 - Listar
3 - Buscar por ID
4 - Atualizar
5 - Deletar
0 - Sair
==================================

📎 Padrão DAO

O projeto segue boas práticas:

UserDao → interface com operações do CRUD

UserDaoJDBC → implementação usando MySQL

DaoFactory → fábrica que entrega o DAO

DB.java → gerencia conexões e statements

🤝 Contribuições

Sinta-se à vontade para abrir PRs, melhorar o código ou adicionar novas funcionalidades como:

GUI

API REST com Spring

Autenticação

Logs
