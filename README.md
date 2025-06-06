# 📊 Consulta Crédito - Fullstack (API + Frontend)

Este projeto é composto por duas partes:

- 🧠 **Backend (API)**: Aplicação Java 17 com Spring Boot que se conecta a um banco de dados PostgreSQL.
- 🌐 **Frontend**: Aplicação Angular 19 empacotada com Nginx para exibição da interface.

Toda a configuração está contida em containers Docker, facilitando a execução do sistema completo sem instalação manual de dependências.

---

## ✅ Pré-requisitos

- [Docker](https://www.docker.com/)
- [Docker Compose](https://docs.docker.com/compose/)

---

## 🚀 Como rodar o projeto

### 1. Clone este repositório

```bash
git clone https://github.com/jvhk/api-consulta-credito.git
cd api-consulta-credito
```

### 2. Suba todos os containers (frontend, backend e banco)

```bash
docker-compose up --build
```

---

## 🧱 O que acontece ao iniciar?

- O banco PostgreSQL é iniciado no container `postgres_credito`.
- O banco `credito_db` é criado automaticamente com tabela `credito` e dados iniciais via scripts SQL.
- A API Spring Boot é empacotada com Gradle e iniciada no container `backend_api_credito`.
- A aplicação Angular é construída e servida via Nginx no container `frontend_credito`.

### 🔄 Tabela `credito` criada automaticamente:

```sql
CREATE TABLE credito
(
	id               BIGINT GENERATED BY DEFAULT AS IDENTITY,
	numero_credito   VARCHAR(50)   NOT NULL,
	numero_nfse      VARCHAR(50)   NOT NULL,
	data_constituicao DATE         NOT NULL,
	valor_issqn      DECIMAL(15,2) NOT NULL,
	tipo_credito     VARCHAR(50)   NOT NULL,
	simples_nacional BOOLEAN       NOT NULL,
	aliquota         DECIMAL(5,2)  NOT NULL,
	valor_faturado   DECIMAL(15,2) NOT NULL,
	valor_deducao    DECIMAL(15,2) NOT NULL,
	base_calculo     DECIMAL(15,2) NOT NULL
);
```

### 📦 Dados de exemplo inseridos:

```sql
INSERT INTO credito (...) VALUES
('123456', '7891011', '2024-02-25', 1500.75, 'ISSQN', true, 5.0, 30000.00, 5000.00, 25000.00),
('789012', '7891011', '2024-02-26', 1200.50, 'ISSQN', false, 4.5, 25000.00, 4000.00, 21000.00),
('654321', '1122334', '2024-01-15', 800.50, 'Outros', true, 3.5, 20000.00, 3000.00, 17000.00);
```

---

## 🌐 Acessos

- 🔸 **Frontend (interface)**: [http://localhost:4200](http://localhost:4200)
- 🔸 **Backend (API REST)**: [http://localhost:8080/api](http://localhost:8080/api)

---

## 🔍 Endpoints da API

A API estará acessível na rota base `/api`. Endpoints disponíveis:

| Método | URL                                       | Descrição                                                 |
|--------|-------------------------------------------|-----------------------------------------------------------|
| GET    | `/creditos/{numeroNfse}`                 | Lista créditos filtrados pelo número da NFSe              |
| GET    | `/creditos/credito/{numeroCredito}`      | Retorna um crédito específico pelo número do crédito      |

Exemplos:

- `GET http://localhost:8080/api/creditos/7891011`
- `GET http://localhost:8080/api/creditos/credito/123456`

---

## 🧪 Acesso ao banco de dados

Você pode acessar o PostgreSQL com ferramentas como DBeaver, pgAdmin ou via terminal `psql`:

- **Host**: `localhost`
- **Porta**: `5432`
- **Banco**: `credito_db`
- **Usuário**: `postgres`
- **Senha**: `root`

---

## 🧹 Parar os containers

```bash
docker-compose down
```

---

## 🗂️ Estrutura de pastas relevante

```
.
├── docker-compose.yaml              # Compose unificado
│
├── backend/
│   └── creditos/
│       ├── Dockerfile               # Docker do backend
│       ├── db/
│       │   └── init/
│       │       ├── create-db.sql
│       │       └── insert-values.sql
│       └── src/                     # Código Java/Spring Boot
│
└── frontend/
    └── consulta-credito/
        ├── Dockerfile              # Docker do frontend (Angular + Nginx)
        └── src/                    # Código Angular
```

---

## 👤 Autor

João Vitor  
[LinkedIn](https://www.linkedin.com/in/jvhk/)  
[GitHub](https://github.com/jvhk)

---