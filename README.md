### Spring Cloud example project

Developed on Spring boot 2.4


### Requests

        
        POST http://localhost:8012/actuator/busrefresh
        GET http://localhost:8012/actuator
        
        POST http://192.168.0.101:8082/users-ws/users
        {
            "firstName": "Paik1000sfgsdfgsdf0",
            "lastName": "Rob20000",
            "email":"r@e.com",
            "password": "rsdfg45gw45ghrewt"  
        }
        
        POST http://192.168.0.101:8082/users-ws/users/login
        {
            "email":"r@e.com",
            "password": "rsdfg45gw45ghrewt"  
        }
        
        GET http://localhost:8082/users-ws/users/status/check
        Authorization: Bearer <token>
