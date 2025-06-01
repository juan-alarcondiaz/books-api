# Books API

A RESTful API for managing books and authors, built with Spring Boot and following a hexagonal architecture pattern.

## Technologies Used

- Java 21
- Spring Boot 3.5.0
- Spring Data JPA
- Oracle Database
- Docker & Docker Compose
- Maven
- Lombok
- MapStruct

## Project Structure

The project follows a hexagonal architecture (also known as ports and adapters) with the following components:

- **Domain**: Contains the business models and exceptions
- **Application**: Contains the business logic, ports (interfaces), and adapters
- **Infrastructure**: Contains the controllers, repositories, DTOs, and mappers

## Prerequisites

- Java 21 or higher
- Maven 3.9+
- Docker and Docker Compose (for containerized deployment)
- Oracle Database (or use the provided Docker setup)

## Running the Application

### Using Docker (Recommended)

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd books-api
   ```

2. Create a `.env` file in the project root with the following environment variables:
   ```
   ORACLE_PWD=your_oracle_password
   DB_URL=jdbc:oracle:thin:@oracle-db:1521:free
   DB_USER=system
   DB_PASS=your_oracle_password
   ```

3. Build and start the containers:
   ```bash
   docker-compose up -d
   ```

4. The application will be available at http://localhost:8080

### Running Locally

1. Clone the repository:
   ```bash
   git clone <repository-url>
   cd books-api
   ```

2. Make sure you have Oracle Database running and accessible

3. Set the following environment variables:
   ```
   DB_URL=jdbc:oracle:thin:@localhost:1521:YOUR_SID
   DB_USER=your_username
   DB_PASS=your_password
   ```

4. Build and run the application:
   ```bash
   mvn clean package
   java -jar target/books.jar
   ```

5. The application will be available at http://localhost:8080

## API Endpoints

### Books

- **GET /books** - Get all books
- **GET /books/{id}** - Get a book by ID
- **POST /books/** - Create a new book
- **PUT /books/{id}** - Update a book
- **DELETE /books/{id}** - Delete a book

### Authors

- **GET /authors** - Get all authors
- **GET /authors/{id}** - Get an author by ID
- **POST /authors** - Create a new author
- **PUT /authors/{id}** - Update an author
- **DELETE /authors/{id}** - Delete an author

## Example Requests

### Create an Author

```bash
curl -X POST http://localhost:8080/authors \
  -H "Content-Type: application/json" \
  -d '{"firstName": "Gabriel", "lastName": "García"}'
```

### Create a Book

```bash
curl -X POST http://localhost:8080/books \
  -H "Content-Type: application/json" \
  -d '{"title": "One Hundred Years of Solitude", "publishDate": "1967-08-15", "authors": [1,3]}'
```

## Development

### Building the Project

```bash
mvn clean package
```

### Running Tests

```bash
mvn test
```

## License

This project is licensed under the MIT License - see the LICENSE file for details.