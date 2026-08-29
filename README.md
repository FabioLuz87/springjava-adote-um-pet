# API REST com Spring Boot

Chassi de uma API REST em arquitetura MVC, sem autenticação, com uma feature CRUD de produtos.

## Tecnologias

- Java 21
- Spring Boot 4.1.1
- Spring Web MVC
- Spring Data JPA com Hibernate
- Bean Validation
- H2 em memória
- Maven Wrapper

## Executar

No Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em `http://localhost:8080`. O console do H2 fica em
`http://localhost:8080/h2-console`, usando a URL JDBC
`jdbc:h2:mem:api-rest`, usuário `sa` e senha vazia.

## Endpoints de produtos

| Método | Rota | Resultado |
|---|---|---|
| `GET` | `/api/v1/products` | Lista paginada |
| `GET` | `/api/v1/products/{id}` | Busca por ID |
| `POST` | `/api/v1/products` | Cria produto |
| `PUT` | `/api/v1/products/{id}` | Atualiza produto |
| `DELETE` | `/api/v1/products/{id}` | Remove produto |

Exemplo de corpo para criação e atualização:

```json
{
  "sku": "NOTE-001",
  "name": "Notebook",
  "price": 3999.90
}
```

A listagem aceita os parâmetros usuais de paginação, por exemplo:
`GET /api/v1/products?page=0&size=20&sort=name,asc`.
