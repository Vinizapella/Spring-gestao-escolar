# 🏫 Spring Gestão Escolar

> API REST completa para gestão de instituições de ensino — construída com **Spring Boot**.

---

## 🗂️ Sobre

O **Spring Gestão Escolar** é uma aplicação back-end desenvolvida com **Spring Boot** para gerenciar os principais recursos de uma instituição escolar, como alunos, professores, turmas e disciplinas, via API REST com persistência em banco de dados relacional.

---

## ✨ Funcionalidades

- 🎓 Cadastro e gerenciamento de alunos
- 👨‍🏫 Gerenciamento de professores
- 📚 Controle de disciplinas
- 🏷️ Gerenciamento de turmas
- 🔗 Vínculo entre alunos, turmas e disciplinas
- 🗄️ Persistência com banco de dados relacional

---

## 🛠️ Tecnologias utilizadas

| Tecnologia | Função |
|---|---|
| ☕ Java | Linguagem principal |
| 🍃 Spring Boot | Framework back-end |
| 🗄️ Spring Data JPA | Persistência e ORM |
| 🐬 MySQL | Banco de dados |
| 📦 Maven | Gerenciador de dependências |

---

## ⚙️ Pré-requisitos

- ☕ Java JDK 17+
- 📦 Apache Maven
- 🐬 MySQL Server

---

## 🚀 Como executar

```bash
# Clone o repositório
git clone https://github.com/Vinizapella/Spring-Gestao-Escolar.git

# Acesse a pasta
cd Spring-Gestao-Escolar

# Configure o banco em src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/gestao_escolar
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha

# Execute o projeto
mvn spring-boot:run
```

---

## 🌐 Endpoints

| Método | Rota | Descrição |
|---|---|---|
| `GET` | `/alunos` | Lista todos os alunos |
| `GET` | `/alunos/{id}` | Busca aluno por ID |
| `POST` | `/alunos` | Cadastra novo aluno |
| `PUT` | `/alunos/{id}` | Atualiza um aluno |
| `DELETE` | `/alunos/{id}` | Remove um aluno |
| `GET` | `/professores` | Lista todos os professores |
| `POST` | `/professores` | Cadastra novo professor |
| `GET` | `/turmas` | Lista todas as turmas |
| `POST` | `/turmas` | Cadastra nova turma |
| `GET` | `/disciplinas` | Lista todas as disciplinas |
| `POST` | `/disciplinas` | Cadastra nova disciplina |

---

## 📁 Estrutura

```
📦 Spring-Gestao-Escolar
 ┣ 📂 src
 ┃ ┗ 📂 main
 ┃   ┣ 📂 java
 ┃   ┃ ┣ 📂 controller
 ┃   ┃ ┣ 📂 service
 ┃   ┃ ┣ 📂 repository
 ┃   ┃ ┗ 📂 model
 ┃   ┗ 📂 resources
 ┃     ┗ 📄 application.properties
 ┣ 📄 pom.xml
 ┗ 📄 README.md
```

---

## 👤 Autor

Feito com 🖤 por **Vinizapella** — projeto concluído para fins acadêmicos.

---

<p align="center">
  <img src="https://img.shields.io/badge/Java-ED8B00?style=flat-square&logo=openjdk&logoColor=white" />
  <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat-square&logo=springboot&logoColor=white" />
  <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat-square&logo=mysql&logoColor=white" />
  <img src="https://img.shields.io/badge/Maven-C71A36?style=flat-square&logo=apachemaven&logoColor=white" />
  <img src="https://img.shields.io/badge/REST%20API-✔-brightgreen?style=flat-square" />
  <img src="https://img.shields.io/badge/status-concluído-brightgreen?style=flat-square" />
</p>
