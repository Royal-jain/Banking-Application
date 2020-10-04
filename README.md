#Banking Application
Spring, H2, Hibernate, Rest API
  Build Restful API for a simple bank application using Spring, H2 and Hibernate.

**Requirements**
  Java - 1.7.x
  Maven - 3.x.x

**Setup**
1. Clone the application
2. Build app using maven run whith Apache Tomcat 7.0.x


The app will start running at http://localhost:8080.


**Explore Rest APIs:**

**To create new account**
POST  http://localhost:8080/Bank/myBank/createAccounts

**To diposite ammount**
PUT  http://localhost:8080/Bank/myBank/cashDeposits

**TO withdow ammount** 
PUT http://localhost:8080/Bank/myBank/balanceEnquiry

**For balance inquiry**
GET http://localhost:8080/Bank/myBank/cashWithdrawals

You can test them using postman or any other rest client.
