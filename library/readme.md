# 📖 Library Management Application (Spring Boot)

## 🚀 Цель
Разработать Spring Boot приложение для управления библиотекой с использованием:
- Spring Boot + Spring Data JPA
- H2 Database (in-memory)
- REST API с использованием DTO
- CRUD-операции для авторов, издателей и книг

## Структура
### Сущности (Entities):
- **Author**: Один автор может написать много книг (One-to-Many)
- **Publisher**: Один издатель может издать много книг (One-to-Many)
- **Book**:
  - Many-to-One: Author
  - Many-to-One: Publisher
  - Many-to-Many: Category
- **Category**: Может быть связана с несколькими книгами (Many-to-Many)
- **Library**: Содержит список книг (ElementCollection)

### DTO:
Используются DTO-классы вместо Entity для передачи данных через REST API (ModelMapper применяется для преобразования).

## 💡 CRUD возможности
### Для Author:
- POST `/api/authors` — Создание
- GET `/api/authors` — Список
- GET `/api/authors/{id}` — Один
- PUT `/api/authors/{id}` — Обновление
- DELETE `/api/authors/{id}` — Удаление

### Для Publisher:
- Аналогично `/api/publishers`

### Для Book:
- POST `/api/books`
- GET `/api/books`
- GET `/api/books/{id}`
- PUT `/api/books/{id}`
- DELETE `/api/books/{id}`

## 🏛️ База данных
- Встроенная база **H2** (in-memory)
- Консоль H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:librarydb`

## 🎓 Технологии
- Java 17+
- Spring Boot 3.4.4
- Spring Data JPA
- H2 Database
- Lombok
- Maven

## 🔎 Создание структуры
```bash
spring init --dependencies=web,data-jpa,h2,lombok --java-version=17 --build=maven library
```

## 🛌 Конфигурация `application.properties`
```properties
spring.datasource.url=jdbc:h2:mem:librarydb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=create
spring.sql.init.mode=always
```

## ✅ Запуск проекта
```bash
./mvnw spring-boot:run
```
Открыть в браузере:
- API: [http://localhost:8080/api/books](http://localhost:8080/api/books)
- H2 Console: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

## 🌐 Пример запроса на создание книги
```http
POST /api/books
Content-Type: application/json

{
  "title": "The Hobbit",
  "authorId": 1,
  "publisherId": 1,
  "categoryIds": [1, 2]
}
```

## 📆 data.sql для заполнения данных
```sql
INSERT INTO author (id, name) VALUES (1, 'J.R.R. Tolkien');
INSERT INTO publisher (id, name) VALUES (1, 'George Allen & Unwin');
INSERT INTO category (id, name) VALUES (1, 'Fantasy'), (2, 'Adventure');
INSERT INTO book (id, title, author_id, publisher_id) VALUES (1, 'The Hobbit', 1, 1);
INSERT INTO book_category (book_id, category_id) VALUES (1, 1), (1, 2);
```

---
