# Game Server

## Setup and Run Instructions

1. **Clone the Repository**
git clone <repository-url> cd game-project/server

2. **Build the Project**
mvn clean install


3. **Run the Server**
mvn exec
-Dexec.mainClass="com.example.GameServer"


4. **Configuration**
- The server runs on port 8080 by default.
- Adjust configuration in `src/main/resources/application.properties` if needed.

5. **Dependencies**
- Java 8 or higher
- Maven
