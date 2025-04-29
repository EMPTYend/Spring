# 📖 Library Management Application (Spring Boot)

## 🚀 Цель
Разработать Spring Boot приложение для управления библиотекой с использованием:
- Spring Boot + Spring JDBC
- H2 Database (in-memory)
- REST API с использованием DTO
- CRUD-операции для авторов, книг, категорий, издателей и библиотеки

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
- Используются DTO-классы вместо Entity для передачи данных через REST API. DTO-классы включают связанную информацию, например, для книги — автора, издателя и категории.
- DTO не должны содержать только одно поле; они должны быть сложными и включать несколько связанных данных.

### Пример структуры DTO:
```java
public class BookDTO {
    private Long id;
    private String title;
    private Long authorId;
    private Long publisherId;
    private List<Long> categoryIds;
}

💡 CRUD возможности
Для Author:
POST /api/authors — Создание автора

GET /api/authors — Получить всех авторов

GET /api/authors/{id} — Получить автора по ID

PUT /api/authors/{id} — Обновить автора

DELETE /api/authors/{id} — Удалить автора

Для Publisher:
POST /api/publishers — Создание издателя

GET /api/publishers — Получить всех издателей

GET /api/publishers/{id} — Получить издателя по ID

PUT /api/publishers/{id} — Обновить издателя

DELETE /api/publishers/{id} — Удалить издателя

Для Book:
POST /api/books — Создание книги

GET /api/books — Получить все книги

GET /api/books/{id} — Получить книгу по ID

PUT /api/books/{id} — Обновить книгу

DELETE /api/books/{id} — Удалить книгу

Для Category:
POST /api/categories — Создание категории

GET /api/categories — Получить все категории

GET /api/categories/{id} — Получить категорию по ID

PUT /api/categories/{id} — Обновить категорию

DELETE /api/categories/{id} — Удалить категорию

Для Library:
POST /api/libraries — Создание библиотеки

GET /api/libraries — Получить все библиотеки

GET /api/libraries/{id} — Получить библиотеку по ID

PUT /api/libraries/{id} — Обновить библиотеку

DELETE /api/libraries/{id} — Удалить библиотеку

🏛️ База данных
Встроенная база H2 (in-memory)

Консоль H2: http://localhost:8080/h2-console

JDBC URL: jdbc:h2:mem:librarydb

🎓 Технологии
Java 17+

Spring Boot 3.4.4

Spring JDBC

H2 Database

Lombok

Maven

🔎 Создание структуры

spring init --dependencies=web,jdbc,h2,lombok --java-version=17 --build=maven library

✅ Запуск проекта

./mvnw spring-boot:run

Открыть в браузере:

API: http://localhost:8080/api/books

H2 Console: http://localhost:8080/h2-console

🌐 Пример запроса на создание книги

POST /api/books
Content-Type: application/json

{
  "title": "Sport",
  "authorId": 1,
  "publisherId": 1,
  "categoryIds": [1, 2]
}

📆 data.sql для заполнения данных

INSERT INTO author (id, name) VALUES (1, 'Tolik');
INSERT INTO publisher (id, name) VALUES (1, 'Sportsmen');
INSERT INTO category (id, name) VALUES (1, 'Sport'), (2, 'Footboll');
INSERT INTO book (id, title, author_id, publisher_id) VALUES (1, 'Sport', 1, 1);
INSERT INTO book_category (book_id, category_id) VALUES (1, 1), (1, 2);

📝 Валидация данных
Строковые поля — не пустые

Числовые значения — в допустимых пределах

При создании или обновлении сущности — проверка существования всех указанных связанных ID (например, нельзя привязать книгу к несуществующему автору)

Этот проект позволяет управлять библиотечными данными, используя Spring JDBC и DTO для взаимодействия через REST API.