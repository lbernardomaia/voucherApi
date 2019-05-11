# voucherApi

This repository consists of two projects.

- voucher-api - The project aims to communicate with a third party API.

- voucher-client - The project is a CLI that interacts with the voucher-api in order to perform the operations.

![API Design](https://github.com/lbernardomaia/voucherApi/blob/master/api_design.jpg)

The main stack is:

-   Java 8
-   SpringBoot 2.1
-   Netflix Hystrix
-   Spring Shell
-   MapStruct
-   Lombok

## Getting Started

```
git clone https://github.com/lbernardomaia/voucherApi
cd voucherApi
```

<details>
  <summary><b>Running with Docker</b></summary> 

```
docker-compose up
```
```
docker attach voucher-client
```
</details>

<details>
  <summary><b>Running without Docker</b></summary>
 
```
mvn clean package  
```
**VoucherAPI**
```
java -jar voucher-api/target/voucher-api-0.0.1-SNAPSHOT.jar 
```
The API is running on port 8080.

E.g. http://localhost:8080/api/v1/client

**Voucher Client**
```
java -jar voucher-client/target/voucher-api-client-0.0.1-SNAPSHOT.jar 
```
</details>

## Interacting with the Client CLI

The command ```help```  display a list of commands available

<details>
  <summary><b>Client Commands</b></summary> 
  
**Search a client by ID**

```
client G7cwfHokOtDorjqFMuI3tA
```

**Search a client by email, firstName, last-name, phone. E.g. searchClient -first-name BAILEY:**

```
searchClient -first-name BAILEY
```

```
searchClient -email 0.109006269972581@example.com -last-name McCreary 
```

```
searchClient -phone 2273077387365 
```

</details>

<details>
  <summary><b>Voucher Commands</b></summary> 
  
**Search Voucher by clientId**
```
searchVoucher WwEaIb0m4bhJphVtm2VgIw 
```

**Search Voucher by clientId and SerialNumber**
```
searchVoucher WwEaIb0m4bhJphVtm2VgIw 10026
```

**Create Voucher**
```
createVoucher WwEaIb0m4bhJphVtm2VgIw 50.0
```

</details>

To leave the terminal ```quit``` 
