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
4. Pristupiti REST APIju npr. curl --u admin:pass1 -i http://localhost:8080/webshop/rest/user/getById/1