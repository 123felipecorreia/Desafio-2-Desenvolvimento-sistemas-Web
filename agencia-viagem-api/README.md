# Agência de Viagem API

API REST para gerenciamento de agência de viagens desenvolvida com Spring Boot.

## Tecnologias Utilizadas

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database (banco de dados em memória)
- Maven

## Estrutura do Projeto

```
agencia-viagem-api/
│
├── src/
│   └── main/
│       └── java/
│           └── com/
│               └── agenciaviagem/
│                   ├── AgenciaViagemApplication.java
│                   ├── controller/
│                   ├── service/
│                   ├── model/
│                   ├── repository/
│                   └── dto/
│
├── src/main/resources/
│   ├── application.properties
│   └── data.sql
│
├── pom.xml
├── README.md
└── .gitignore
```

## Como Executar

1. Certifique-se de ter o Java 17+ e Maven instalados
2. Clone o repositório
3. Navegue até o diretório do projeto
4. Execute o comando:
   ```
   mvn spring-boot:run
   ```
5. A aplicação estará disponível em: `http://localhost:8080`

## H2 Console

Acesse o console do banco de dados H2 em: `http://localhost:8080/h2-console`

- JDBC URL: `jdbc:h2:mem:testdb`
- Username: `sa`
- Password: (deixe em branco)

## Endpoints da API

### 📍 Destinos

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| `POST` | `/api/destinos` | Cadastrar novo destino |
| `GET` | `/api/destinos` | Listar todos os destinos |
| `GET` | `/api/destinos/{id}` | Visualizar detalhes de um destino |
| `GET` | `/api/destinos/pesquisa?termo=` | Pesquisar por nome ou localização |
| `PATCH` | `/api/destinos/{id}/avaliar` | Avaliar destino (nota de 1 a 5) |
| `DELETE` | `/api/destinos/{id}` | Excluir destino |

### Exemplos de Requisições

#### 1. Cadastrar Destino
```http
POST /api/destinos
Content-Type: application/json

{
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Cidade Maravilhosa com praias deslumbrantes",
  "preco": 1500.00,
  "atracoesTuristicas": "Cristo Redentor, Pão de Açúcar, Copacabana, Ipanema, Jardim Botânico"
}
```

**Resposta (201 Created):**
```json
{
  "id": 1,
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Cidade Maravilhosa com praias deslumbrantes",
  "preco": 1500.00,
  "atracoesTuristicas": "Cristo Redentor, Pão de Açúcar, Copacabana, Ipanema, Jardim Botânico",
  "avaliacaoMedia": null,
  "totalAvaliacoes": 0,
  "dataCriacao": "2025-11-09T10:30:00",
  "dataAtualizacao": "2025-11-09T10:30:00"
}
```

#### 2. Listar Todos os Destinos
```http
GET /api/destinos
```

**Resposta (200 OK):**
```json
[
  {
    "id": 1,
    "nome": "Rio de Janeiro",
    "localizacao": "Rio de Janeiro, Brasil",
    "descricao": "Cidade Maravilhosa com praias deslumbrantes",
    "preco": 1500.00,
    "avaliacaoMedia": 4.5,
    "totalAvaliacoes": 10,
    "dataCriacao": "2025-11-09T10:30:00",
    "dataAtualizacao": "2025-11-09T10:30:00"
  }
]
```

#### 3. Buscar Destino por ID
```http
GET /api/destinos/1
```

**Resposta (200 OK):** Mesmo formato do item anterior

#### 4. Pesquisar Destinos
```http
GET /api/destinos/pesquisa?termo=rio
```

**Resposta (200 OK):** Lista de destinos que contêm "rio" no nome ou localização

#### 5. Avaliar Destino
```http
PATCH /api/destinos/1/avaliar
Content-Type: application/json

{
  "nota": 5
}
```

**Resposta (200 OK):**
```json
{
  "id": 1,
  "nome": "Rio de Janeiro",
  "localizacao": "Rio de Janeiro, Brasil",
  "descricao": "Cidade Maravilhosa com praias deslumbrantes",
  "preco": 1500.00,
  "avaliacaoMedia": 4.55,
  "totalAvaliacoes": 11,
  "dataCriacao": "2025-11-09T10:30:00",
  "dataAtualizacao": "2025-11-09T11:45:00"
}
```

#### 6. Excluir Destino
```http
DELETE /api/destinos/1
```

**Resposta (200 OK):**
```json
{
  "mensagem": "Destino excluído com sucesso"
}
```

### Códigos de Status HTTP

- `200 OK` - Requisição bem-sucedida
- `201 Created` - Recurso criado com sucesso
- `400 Bad Request` - Dados inválidos na requisição
- `404 Not Found` - Destino não encontrado
- `500 Internal Server Error` - Erro no servidor

## Desenvolvimento

- **Controllers**: Camada de apresentação (REST endpoints)
- **Services**: Camada de lógica de negócio
- **Repositories**: Camada de acesso a dados
- **Models**: Entidades do banco de dados
- **DTOs**: Objetos de transferência de dados
