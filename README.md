# Banking Application
  ## Spring, H2, Hibernate, Rest API
  Build Restful API for a simple bank application using Spring, H2 and Hibernate.

**Requirements**
  * Java - 1.7.x
  * Maven - 3.x.x

**Setup**
1. Clone the application
2. Create table and sequnce 
3. Build app using maven run whith Apache Tomcat 7.0.x

**Refer snaps for folder structure and test cases plased at https://github.com/Royal-jain/Banking-Application/blob/master/snap.rtf**

The app will start running at http://localhost:8080/.

**Explore Rest APIs:**

**To create new account**
* POST  /Bank/myBank/createAccounts

**To diposite ammount**
* PUT  /Bank/myBank/cashDeposits

**TO withdow ammount** 
* PUT /Bank/myBank/balanceEnquiry

**For balance inquiry**
* GET /Bank/myBank/cashWithdrawals

You can test them using postman or any other rest client and please find there respective sample request at https://github.com/Royal-jain/Banking-Application/blob/master/Sample.txt.
