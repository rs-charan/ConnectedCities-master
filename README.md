# ConnectedCities Application Summary
This repository contains project to identify connectivity between two cities

**Problem Statement :**<br>
1 . To develop an application which responds with yes/no if there exists a path between two cities .

**Tech Stack :**<br>
1 . Java , Spring Boot

**Approach :**<br>
- INPUT :
  * Input to application is txt file (cities.txt) which is available in application.properties .
  * Input file contains multiple lines with each line containing two cities separated by comma .
  * Application is designed in such a way that it is extendible to parse any input file in other format , it exposes **FileParsingUtil** abstract class
    which abstracts implementation detils for parsing for various file formats by adhering to **OCP** and **Abstract Factory Pattern**
    
- FUNCTIONALITY (Modules):
  * Application basically has four functionalities : controller , service , routeestablisher , parser and other helper modules
  * **controller** receives incoming http get  request and processes source/destination received as part of request and delgates it to service
  * **service** calls parser and route establisher classes to get raw data in required form to check if route exists between two cities
  * **parser** contains parsing logic for given file format i.e., CSV in this case
  * **routestablisher** routeestablisher after getting parsed content from parser will give the data in expected format to service class
  
- ALGORITHM :
  * Below connectedcities application uses BFS to detect if there is a path between given two cities
  * For any invalid input application returns response as no
  
- URL :
  * Application can be accessed by : http://localhost:8080/connected?origin=city1&destination=city2
  
 **Always there is scope of improvement , furhter improvements can be done by**<br>
    * Swagger can be used for API documentation <br>
    * Spring security can be used for Authenticating incoming traffic <br>
    
 **Sample Request Response**
 
 1 . http://localhost:8080/connected?origin=Boston&destination=Philadelphia
 
 output : yes
 
 2 . http://localhost:8080/connected?origin=Boston&destination=Newark
 
 output : yes
 
 3 . http://localhost:8080/connected?origin=Philadelphia&destination=Albany
 
 output : no
