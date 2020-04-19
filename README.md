# Print Service Microservice using RabbitMQ
This project demonstrate how we can make use of RabbitMQ and Spring Boot to create a printing microservice that can be used by all of the other microservices to print information like report, bills etc. This will help centralize printer configuration and other related settings on this printing microservice alone. 

###Software Required: 
Java - Version 8\
RabbitMQ Server (https://www.rabbitmq.com/): Used  3.8.3 in above application

To demonstrate this created three services:  

| Service Name         | Port   |
| ------------- |:-------------:|
| eurekaService      | 8085 |
| printService      | 8082     |
| appService |   8083 |

#### eurekaService
This service uses Netflix Eureka to create a discovery server. Other services printService and appService are registered to this service using the service name given in the application.yml file. Once registered to this service eureka server will act as load balancer and also help service to connect to other service by just knowing there service name.

#### printService
Print Service uses Netflix Eureka Client to register to the eureka server (eurekaService) by giving the details of the eureka server address in its application.yml file. \
This service uses Rabbit MQ to receive message from other services which it can print to the PDF or someplace else. Here printService receives message from the the appService and prints message to the log file.

#### appService
App Service uses Netflix Eureka Client to register to the eureka server (eurakaService) by giving the details of the eureka server address in its application.yml file. \
This a dummy service which uses Rabbit MQ to send message to printService through the message queue.

#### Execute above program
Make sure all the ports mentioned above against each of the services are free, if not you can assign different port in the application.yml. If you change the port of the Eureka Server, we need to mention this in defaultZone of other services application.yml file.\
RabbitMQ username and password used here is 'guest' configured in yml file. This will be the default password when you install RabbitMQ first time in your system (Try at the url - http://localhost:15672/). By default, RabbitMQ will listen on port 5672 on all available interfaces and this value is placed at spring.cloud.stream.binders.local_rabbit.environment.spring.rabbitmq.port.\

Run all the service by executing the `mvn spring-boot:run`. \
After services starts running successfully you can check them at the eureka server http://localhost:8085/.

To transfer message from appService to printService using rabbitMQ trigger http://localhost:8083/message/{message}, where you can enter any value in place of message (Used simple rest controller with endpoint /message/{message} with request method GET). 
After triggering url, you can check message transferred at the RabbitMQ Web interface at port 15672 or log file for the message "Message to be printed : {message}".



