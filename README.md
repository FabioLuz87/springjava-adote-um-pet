# API REST com Spring Boot

API REST em arquitetura MVC para uma plataforma de adoção de animais, sem autenticação nesta etapa.

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

## Endpoints

| Método | Rota | Resultado |
|---|---|---|
| `GET` | `/api/v1/entidades` | Lista entidades |
| `GET` | `/api/v1/entidades/{id}` | Busca uma entidade |
| `POST` | `/api/v1/entidades` | Cria uma entidade |
| `PUT` | `/api/v1/entidades/{id}` | Atualiza uma entidade |
| `DELETE` | `/api/v1/entidades/{id}` | Remove uma entidade |

Exemplo de corpo para criação e atualização:

```json
{
  "name": "Abrigo Amigo",
  "type": "ONG",
  "phone": "51999999999",
  "email": "contato@abrigo.org",
  "serviceHours": "Seg-Sex 9h-18h",
  "address": {
    "street": "Rua A",
    "number": "10",
    "complement": null,
    "neighborhood": "Centro",
    "city": "São Leopoldo",
    "state": "RS",
    "zipCode": "93000-000"
  }
}
```

A listagem aceita os parâmetros usuais de paginação, por exemplo:
`GET /api/v1/entidades?page=0&size=20&sort=name,asc`.
