# EpicGamesApi

It is custom api for get free games from Epicgames

### Run it with Docker:

    docker build -t epic .
    
    docker run --name epic-container -p 8009:8009 epic

### Run it with jdk 11:

  Linux and MacOs:
      
    sudo chmod +x mvnw && ./mvnw spring-boot:run
    
  Windows:
  
    mvnw.cmd spring-boot:run
   
### Browser:

    http://localhost:8009/swagger

    http://localhost:8009/api/epic/games
    
    http://localhost:8009/api/epic/games/active
    
    http://localhost:8009/api/epic/games/coming-soon
    
