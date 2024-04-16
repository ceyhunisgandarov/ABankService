# ABankService - Payment Service Mock API

ABankService is a Java-based backend application developed as a sample project to simulate the API service for a fictional company named "ABank". This README focuses on the APIs provided by the `ABankService` project.

## Overview

The `ABankService` project serves as a mock API service for "ABank" company to demonstrate payment transactions. It provides endpoints that simulate bank card operations and payment transactions.

## API Endpoints

### 1. Get Bank Card Details

- **HTTP Method**: `POST`
- **Endpoint**: `/payment/bankcard/get`
- **Description**: Retrieves bank card details based on the provided request.
- **Request Body**: `ReqBankCard` object.
- **Response**: `Response<RespBankCard>` object.

### 2. Pay Amount to Bank Card

- **HTTP Method**: `PUT`
- **Endpoint**: `/payment/bankcard/pay`
- **Description**: Processes payment to the bank card.
- **Request Body**: `ReqBankCard` object.
- **Response**: `Response<RespTransaction>` object.

## Installation

### Prerequisites

- Java Development Kit (JDK)
- Maven

### Steps

1. Clone the `ABankService` repository:
    ```bash
    git clone https://github.com/kullanici_adi/ABankService.git
    ```
2. Navigate to the `ABankService` directory.
3. Build the project using Maven:
    ```bash
    mvn install
    ```

## Configuration

Before running the `ABankService`, you may need to configure the mock data and endpoints to suit your testing scenarios.

### Configuration File

Update the `application.properties` file with the required configurations.

```properties
# Mock API Configuration
mock.api.endpoint=http://localhost:8081/mock-payment
