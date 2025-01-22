
# Tic-Tac-Toe Game API

## Overview
This repository contains a Spring Boot-based implementation of a Tic-Tac-Toe game. The application supports user registration, authentication, gameplay, and match history retrieval. The database is designed with Hibernate JPA, ensuring normalized tables for scalability and efficiency.

---

## API Endpoints

### 1. **Authentication APIs**

#### Register a User
**Endpoint:** `POST /api/auth/register`  
**Description:** Register a new user with a unique username and password.  
**Request Body:**
```json
{
    "username": "exampleUser",
    "password": "examplePass"
}
```
**Response:**
```
User registered!
```

#### Login a User
**Endpoint:** `POST /api/auth/login`  
**Description:** Login a user and obtain a JWT token for authenticated requests.  
**Request Body:**
```json
{
    "username": "exampleUser",
    "password": "examplePass"
}
```
**Response:**
```
User logged in!
```

---

### 2. **Game Management APIs**

#### Start a Game
**Endpoint:** `POST /api/games/start`  
**Description:** Start a new game between two users.  
**Request Body:**
```json
{
    "player1Id": 1,
    "player2Id": 2
}
```
**Response:**
```json
{
    "id": 1,
    "player1": {
        "id": 1,
        "username": "player1"
    },
    "player2": {
        "id": 2,
        "username": "player2"
    },
    "status": "IN_PROGRESS",
    "result": null
}
```

#### Make a Move
**Endpoint:** `POST /api/games/{gameId}/move`  
**Description:** Make a move in the specified game.  
**Path Variable:** `gameId` (ID of the game)
**Request Body:**
```json
{
    "playerId": 1,
    "position": 4
}
```
**Response:**
```json
{
    "id": 1,
    "game": {
        "id": 1
    },
    "player": {
        "id": 1
    },
    "position": 4,
    "timestamp": "2025-01-22T12:34:56"
}
```

#### Finish a Game
**Endpoint:** `POST /api/games/{gameId}/finish`  
**Description:** Mark a game as completed with the result (win/loss/draw).  
**Path Variable:** `gameId` (ID of the game)
**Query Parameter:** `result` (`WIN`, `LOSS`, `DRAW`)
**Response:**
```json
{
    "id": 1,
    "status": "COMPLETED",
    "result": "WIN"
}
```

---

### 3. **Game History APIs**

#### Fetch Match History
**Endpoint:** `GET /api/games/history/{userId}`  
**Description:** Fetch the match history for a specific user, including game results and moves.  
**Path Variable:** `userId` (ID of the user)
**Response:**
```json
[
    {
        "gameId": 1,
        "opponent": {
            "id": 2,
            "username": "player2"
        },
        "result": "WIN",
        "moves": [
            {"position": 0, "timestamp": "2025-01-22T12:34:56"},
            {"position": 1, "timestamp": "2025-01-22T12:35:00"}
        ]
    }
]
```

---

## Database Structure

### User Table
| Column Name | Type          | Constraints       |
|-------------|---------------|-------------------|
| id          | BIGINT        | Primary Key       |
| username    | VARCHAR(255)  | Unique, Not Null  |
| password    | VARCHAR(255)  | Not Null          |

### Game Table
| Column Name | Type           | Constraints       |
|-------------|----------------|-------------------|
| id          | BIGINT         | Primary Key       |
| player1_id  | BIGINT         | Foreign Key       |
| player2_id  | BIGINT         | Foreign Key       |
| status      | ENUM           | Not Null          |
| result      | ENUM           | Nullable          |

### GameMove Table
| Column Name | Type           | Constraints       |
|-------------|----------------|-------------------|
| id          | BIGINT         | Primary Key       |
| game_id     | BIGINT         | Foreign Key       |
| player_id   | BIGINT         | Foreign Key       |
| position    | INT            | Not Null          |
| timestamp   | DATETIME       | Not Null          |

---

## Running the Application

### Prerequisites
- Java 17 or later
- MySQL
- Maven

### Steps to Run
1. Clone the repository.
2. Configure the database connection in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tictactoe
   spring.datasource.username=<your_username>
   spring.datasource.password=<your_password>
   spring.jpa.hibernate.ddl-auto=update
   ```
3. Run the application:
   ```bash
   mvn spring-boot:run
   ```

---


