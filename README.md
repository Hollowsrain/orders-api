# Build Status [![CircleCI](https://dl.circleci.com/status-badge/img/gh/Hollowsrain/orders-api/tree/master.svg?style=svg)](https://dl.circleci.com/status-badge/redirect/gh/Hollowsrain/orders-api/tree/master)
# End-points for api usages

### Service
| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- |---------------------------|
| GET    | /services | Retrieve all services |                           |
| GET    | /services/{id} | Retrieve a specific service by ID | [JSON](#fullCustomerGet)    |
| POST   | /services | Create a new service | [JSON](#createservice)    |
| PUT    | /services/{id} | Update a specific service by ID | [JSON](#createservice)    |
| DELETE | /services/{id} | Delete a specific service by ID |                           |

### Customer
| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- |---------------------------|
| GET    | /customers | Retrieve all customers |                           |
| GET    | /customers/{id} | Retrieve a specific customer by ID |                           |
| POST   | /customers | Create a new customer | [JSON](#createCust)       |
| PUT    | /customers/{id} | Update a specific customer by ID | [JSON](#createCust)       |
| DELETE | /customers/{id} | Delete a specific customer by ID |                           |

### Account
| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- |---------------------------|
| GET    | /customers/{customerId}/accounts | Retrieve all accounts for a specific customer |                           |
| GET    | /accounts/{id} | Retrieve a specific account by ID |                           |
| POST   | /customers/{customerId}/accounts | Create a new account for a specific customer | [JSON](#createAcc)        |
| PUT    | /accounts/{id} | Update a specific account by ID | [JSON](#createAcc)        |
| DELETE | /accounts/{id} | Delete a specific account by ID |                           |

### MSISDN
| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- |---------------------------|
| GET    | /accounts/{accountId}/msisdns | Retrieve all MSISDNs for a specific account |                           |
| GET    | /msisdns/{id} | Retrieve a specific MSISDN by ID |                           |
| POST   | /accounts/{accountId}/msisdns | Create a new MSISDN for a specific account | [JSON](#createNr)         |
| PUT    | /msisdns/{id} | Update a specific MSISDN by ID | [JSON](#createNr)         |
| DELETE | /msisdns/{id} | Delete a specific MSISDN by ID |                           |

### OrderedService
| Method | Url | Description | Sample Valid Request Body |
| ------ | --- | ----------- |---------------------------|
| GET    | /msisdns/{msisdnId}/ordered-services | Retrieve all ordered services for a specific MSISDN |                           |
| GET    | /ordered-services/{id} | Retrieve a specific ordered service by ID |                           |
| POST   | /msisdns/{msisdnId}/ordered-services | Create a new ordered service for a specific MSISDN | [JSON](#createOrd)        |
| PUT    | /ordered-services/{id} | Update a specific ordered service by ID | [JSON](#createOrd)        |
| DELETE | /ordered-services/{id} | Delete a specific ordered service by ID |                           |
---
##### <a id="createCust">Create/Update Customer</a>
```json
{
    "name": "John",
    "surname": "Snow",
    "companyName": "ABC Inc.",
    "companyCode": "11111",
    "personalCode": 98765452111,
    "accounts": []
}
```

##### <a id="createAcc">Create/Update Account</a>
```json
{
  "name": "Account1",
  "address": "1234 Elm Street",
  "msisdns": [
  ]
}
```

##### <a id="createNr">Create/Update MSISDN</a>
```json
{
  "id": 1,
  "activeFrom": "2023-04-15T12:34:56Z",
  "activeTo": "2023-04-15T23:59:59Z",
  "orderedServices": [
  ]
}
```

##### <a id="createOrd">Create/Update OrderedService</a>
```json
{
    "id": 1,
    "name": "Ordered Service 1",
    "activeFrom": "2023-04-14T23:31:49.337Z",
    "activeTo": "2023-04-14T23:31:49.337Z",
    "service": {
        "id": 1,
        "name": "Sample Service",
        "type": "Type A",
        "description": "This is a sample service"
    }
}
```

##### <a id="createservice">Create/Update Service</a>
```json
{
  "name": "Sample Service",
  "type": "Type A",
  "description": "This is a sample service"
}
```

##### <a id="fullCustomerGet">Get response for customer with account MSISDN and ordered service</a>
```json
    {
  "id": 1,
  "name": "John",
  "surname": "Snow",
  "companyName": "HBO",
  "companyCode": "11111",
  "personalCode": 98765452111,
  "accounts": [
    {
      "id": 1,
      "name": "Account1",
      "address": "1234 Elm Street",
      "msisdns": [
        {
          "id": 1,
          "activeFrom": "2023-04-15T15:34:56+03:00",
          "activeTo": "2023-04-16T02:59:59+03:00",
          "orderedServices": [
            {
              "id": 1,
              "name": "Ordered Service 1",
              "activeFrom": "2023-04-15T02:31:49.337+03:00",
              "activeTo": "2023-04-15T02:31:49.337+03:00",
              "service": {
                "id": 1,
                "name": "Sample Service",
                "type": "Type A",
                "description": "This is a sample service"
              }
            }
          ]
        }
      ]
    }
  ]
}
```



