# Book and Author Management System - Backend

Διαδικτυακή εφαρμογή διαχείρισης βιβλίων και συγγραφέων.

## Τεχνολογίες
- Java 17
- Spring Boot
- Spring Data JPA / Hibernate
- MySQL
- Swagger (SpringDoc OpenAPI)

## Προαπαιτούμενα
- Java 17+
- Maven
- MySQL Server

## Οδηγίες Εγκατάστασης

### 1. Δημιουργία Βάσης Δεδομένων
Άνοιξε το MySQL και τρέξε:
```sql
CREATE DATABASE bookandauthor;
```

### 2. Ρύθμιση application.properties
Άνοιξε το αρχείο `src/main/resources/application.properties` και συμπλήρωσε:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bookandauthor
spring.datasource.username=YOUR_USERNAME
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

### 3. Εκτέλεση
```cmd
mvn spring-boot:run
```

Η εφαρμογή τρέχει στο `http://localhost:8080`

## Swagger UI
Τεκμηρίωση API: `http://localhost:8080/swagger-ui/index.html`

## Endpoints
| Method | URL | Περιγραφή |
|--------|-----|-----------|
| GET | /books | Λίστα βιβλίων |
| POST | /books | Προσθήκη βιβλίου |
| PUT | /books/{isbn} | Ενημέρωση βιβλίου |
| DELETE | /books/{isbn} | Διαγραφή βιβλίου |
| GET | /books/{isbn}/authors | Συγγραφείς βιβλίου |
| GET | /authors | Λίστα συγγραφέων |
| POST | /authors | Προσθήκη συγγραφέα |
| PUT | /authors/{id} | Ενημέρωση συγγραφέα |
| DELETE | /authors/{id} | Διαγραφή συγγραφέα |
| GET | /authors/{id}/books | Βιβλία συγγραφέα |
