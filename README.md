Informacije o web aplikaciji:

Tehnologije:
1. Java 8
2. Maven 3.8.4
3. Spring boot 2.5.10 + Spring Security
4. h2 db + liquibase

Upute za lokalno pokretanje:

1. Pokrenuti mvn spring-boot:run
2. Pristupiti swaggeru na http://localhost:8080/webshop/swagger-ui/
3. Pristupiti h2 konzoli na http://localhost:8080/webshop/console/
4. Preuzeti JWT preko REST APIja npr. "curl --header "Content-Type: application/json" --request POST --data "{ """username""":"""admin""", """password""":"""pass1""" }" http://localhost:8080/webshop/authenticate"
5. Pristupiti REST APIju koristeći JWT npr. "curl --header "Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTY1NDcwMTIwMywiaWF0IjoxNjU0NjgzMjAzfQ.0k9Qm4IloUOacoba_N6Gmtz-E6mETsVNmnmfxWFZ5tg-xCre13AjOT7Y99GQIl6aTqC8lUCyorcAN0ZJgBMffQ" -i http://localhost:8080/webshop/rest/user/getById/1"
