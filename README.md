# REST Web Service with JMS Implementation 
This small project shows how to use REST DSL and add TIBCO EMS broker as JMS component in Apache Camel .
# Sample Use Case 
In this example , REST web service has been exposed with POST HTTP method and the incoming message - POST request has been sent on JMS queue . Th exception is raised incase of bad request and the error message and code are sent back in response to the service .

This is an "Employee Use Case" and it performs the following tasks :
1. It creates new employee details , filters the incoming message on the basis of incoming Employee_Title and sends the request to configured JMS queue.
3. The exception is raised incase of bad request and sent back as response to the caller.

 # Apache Camel Concepts Covered :
 1. REST DSL - Expose REST web service
 2. JMS Component - Add TIBCO JMS Connection
 3. Properties component - Add global values in properties file - JMS ,REST connection details and JMS queues
 4. JasyptPropertiesParser - Encrypt username and password of JMS connection
 5. Exception Handling   
    
    i) onException block - Handle generic exception
    
    ii)doTry doCatch block - Handle parse exception
 6. EIP
    
    i)Content based Routing - Filter incoming message before sending to JMS queue
    
    ii)wireTap
 7.  JacksonDataFormat 
 
    i)Marshall POJO to JSON and vice versa
 8. camel-restdsl-openapi - Auto generate Camel REST DSL 
 
 # OpenAPI Specification 
 OpenAPI Specification (formerly Swagger Specification) is an API description format for REST APIs. An OpenAPI file allows you to describe your entire API ,including:
   -  Available endpoints and operations on each endpoint (POST /employee)
   -  Operation parameters Input and output for each operation
   -   Authentication methods etc
   
  Refer this link for more information : https://swagger.io/docs/specification/about/
  
  # Encrypt Username and Password 
  Camel Jasypt encryption library is used to encrypt and decrypt sensitive information of an application
  
  The following command has been used to encrypt password of TIBCO JMS connection
  -  java -jar camel-jasypt-3.4.3.jar -c encrypt -p secret -i <passowrd>
  
      Encrypted text: iWwq47eGLgyY/yhJbkQHTQ==
      
     Refer this link for more information : https://camel.apache.org/components/latest/others/jasypt.html
     
      

 
