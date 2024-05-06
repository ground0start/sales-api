# Spring Boot Data Analytics Dashboard Backend + Python Script

## Assumptions
1. If some staff item did not log quantity/price, will not count, neither in sales amount or quantity
   1. Exp. data line such as
`      2844,21,Shawnee Golf Course,2544625,2016/01/06 16:05:59+00,2016/01/06 05:00:00+00,12038, Staff Dogs, Brats,Polish, Etc.,1`
   
2. Same item description will have different itemID, so count by item description text
3. Clean Dirty data - like Winter WE GreenFee and Winter WE Green Fee - actually the same (note categorize)


## How to Run 

This application is packaged as a war which has Tomcat 8 embedded. No Tomcat or JBoss installation is necessary. You run it using the ```java -jar``` command.

* Clone this repository 
* Make sure you are using JDK 1.8 and Maven 3.x
* You can build the project and run the tests by running ```mvn clean package```
* Once successfully built, you can run the service by one of these two methods:
```
        java -jar -Dspring.profiles.active=test target/spring-boot-rest-example-0.5.0.war
or
        mvn spring-boot:run -Drun.arguments="spring.profiles.active=test"
```
* Check the stdout or boot_example.log file to make sure no exceptions are thrown

Once the application runs you should see something like this

```
2017-08-29 17:31:23.091  INFO 19387 --- [           main] s.b.c.e.t.TomcatEmbeddedServletContainer : Tomcat started on port(s): 8090 (http)
2017-08-29 17:31:23.097  INFO 19387 --- [           main] Application        : Started Application in 22.285 seconds (JVM running for 23.032)
```

## About the Service
The server will start at http://localhost:8090.

## API Documentation

### Get Total Sales Amount

* URL: localhost:8090/api/sales/total/amount
* Method: GET
* Success Response:
    * Code: 200 OK
    * Type: Double
    * Example: 187654.0
* Error Response:
    * Code: 500 Internal Server Error
    * Content: { "error": "An error occurred while processing your request: [error details]" }
* Description: Returns the total sales amount as a double. 


### Get Total Sales Quantity

* URL: localhost:8090/api/sales/total/quantity
* Method: GET
* Success Response:
    * Code: 200 OK
    * Type: Double 
    * Example: 87654.0
* Error Response:
    * Code: 500 Internal Server Error
    * Content: { "error": "An error occurred while processing your request: [error details]" }
* Description: Returns the total sales quantity as a double. 


### Get Best Selling Products by Quantity

* URL: localhost:8090/api/sales/bestselling/quantity
* Method: GET
* Success Response:
    * Code: 200 OK
    * Type: List of SalesQuantityData
    * Example:
  `[
      {
      "ItemDescription": "golf cart 1/2 18 holes",
      "Quantity": 22135
      },
      {
      "ItemDescription": "we green fee",
      "Quantity": 18313
      },
      {
      "ItemDescription": "key deposit",
      "Quantity": 17538
      },..]`
* Error Response:
    * Code: 500 Internal Server Error
    * Content: { "error": "An error occurred while processing your request: [error details]" }
* Description: Provides a list of products sorted by the quantity sold

### Get Best Selling Products by Sales Amount

* URL: localhost:8090/api/sales/bestselling/amount
* Method: GET
* Success Response:
    * Code: 200 OK
    * Type: List of SalesAmountData
    * Example:
      `[
      {
      "ItemDescription": "golf cart 1/2 18 holes",
      "TotalSaleAmount": 307993.0
      },
      {
      "ItemDescription": "we green fee",
      "TotalSaleAmount": 285111.0
      },
      {
      "ItemDescription": "wd green fee",
      "TotalSaleAmount": 175699.0
      },..]`
* Error Response:
    * Code: 500 Internal Server Error
    * Content: { "error": "An error occurred while processing your request: [error details]" }
* Description: Returns a list of products sorted by sales amount. Error handling ensures that any processing issues are communicated clearly to the client.


### Get Monthly Sales Trends

* URL: localhost:8090/api/sales/trends
* Method: GET
* Success Response:
    * Code: 200 OK
    * Type: List of MonthlySalesData
    * Example:
      `[
      {
      "Month": "2015-12",
      "TotalSaleAmount": 9015.0
      },
      {
      "Month": "2016-01",
      "TotalSaleAmount": 84543.0
      },
      {
      "Month": "2016-02",
      "TotalSaleAmount": 154082.0
      },..]`
* Error Response:
    * Code: 500 Internal Server Error
    * Content: { "error": "An error occurred while processing your request: [error details]" }
* Description: Provides monthly sales data, detailing the total sales amount for each month, with detailed error reporting.
