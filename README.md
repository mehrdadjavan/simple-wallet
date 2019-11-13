# Spring Boot Rest "Microservice" Wallet Project
REST APIs is implemented by using of Spring Boot and Maven Project

###Prerequisite And Run App
To use this project, you need;
```
Java JDK 8 (1.8)
Maven compatibile with JDK 8 to build the application
Any Java IDE
```
## How to Run

* Build the project by running `mvn clean install` inside simple-wallet
* Once successfully built, run the service by using the following command:

Start application by:
``` 
mvn spring-boot:run
```
Or instead you can run the jar by:
``` 
java -jar  simple-wallet/target/simple-wallet-0.1-SNAPSHOT.jar
```
## To check that application started successfully go to:
```
http://localhost:8080/api/v1/wallet/test
```

## REST APIs Endpoints

```
Get http://localhost:8080/api/v1/wallet/getAccountByPlayerId?id=1
Accept: application/json
Content-Type: application/json
response:
{
    "errorCode": 0,
    "errorMessage": null,
    "data": {
        "id": 1,
        "accountName": "test account1",
        "balance": 111.00
    }
}

```
```
Get http://localhost:8080/api/v1/wallet/getBalanceByAccountId?id=1
Accept: application/json
Content-Type: application/json
response:
{
    "errorCode": 0,
    "errorMessage": null,
    "data": {
        "id": 1,
        "accountName": "test account1",
        "balance": 100.00
    }
}

```
```
POST http://localhost:8080/api/v1/wallet/credit
Accept: application/json
Content-Type: application/json
request:
{
	"playerId": 1,
	"requestId": 1,
	"amount": 4
}

response:
{
    "errorCode": 0,
    "errorMessage": null,
    "data": {
        "id": 1,
        "accountName": "test account1",
        "balance": 104.00
    }
}
```
```
POST http://localhost:8080/api/v1/wallet/withdrawal
Accept: application/json
Content-Type: application/json
request:
{
	"playerId": 1,
	"requestId": 2,
	"amount": 4
}

response:
{
    "errorCode": 0,
    "errorMessage": null,
    "data": {
        "id": 1,
        "accountName": "test account1",
        "balance": 104.00
    }
}
```

```
Get http://localhost:8080/api/v1/transaction/getTransactionsByPlayerId?id=1
Accept: application/json
Content-Type: application/json
response:
{
    "errorCode": 0,
    "errorMessage": null,
    "data": [
        {
            "id": 1,
            "requestId": 1,
            "playerId": 1,
            "accountId": 1,
            "balance": 111.00,
            "amount": 11.00,
            "transactionTypeInfo": "withdrawal",
            "transactionResult": 1,
            "createDate": "2019-10-16T14:54:15.502+0000"
        }
    ]
}
```
# Information about api urls.
```
Get http://localhost:8080/api/v1/wallet/getAccountByPlayerId?id=1
Get http://localhost:8080/api/v1/wallet/getBalanceByAccountId?id=1
POST http://localhost:8080/api/v1/wallet/credit
POST http://localhost:8080/api/v1/wallet/withdrawal
Get http://localhost:8080/api/v1/transaction/getTransactionsByPlayerId?id=1
```
#JSON Error Code and Message:
#####If ErrorCode == 0, the process is successful
```
    APPROVE_CODE = 0;
    INTERNAL_ERROR = -1;
    BALANCE_NOT_ENOUGH = 10002;
    REQUEST_ID_DUPLICATE = 10003;
    INVALID_ID = 10004;
```

######Run the server and browse to localhost:8080/
