# employee-api

----
employee api assignment. It uses spring-boot and h2 in memory database. Mock test cases written for service and controller. Integration test is written for controller and repository layer.

#### build: `mvn clean package` 
#### run: `mvn spring-boot:run`
#### access url: `http://localhost:8080/swagger-ui.html`
send post request with the below data by using above swagger url.
```json
{
  "department": "SALES",
  "firstName": "astring",
  "gender": "MALE",
  "lastName": "string"
}
```
![image](https://user-images.githubusercontent.com/17001948/68547154-3394ee00-0404-11ea-8341-7e1ec3680edb.png)
