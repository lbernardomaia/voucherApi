# voucherApi

The project interact with a third part API. 

Modules:

- The VoucherAPI Project aims to communicate with a third party API.

- The Client API Project is a CLI that interacts with the VoucherAPI in order to perform the operations.

The main stack is:

-   SpringBoot 2.1
-   Netflix Hystrix
-   Spring Shell
-   MapStruct
-   Lombok

## Getting Started


```
git clone https://github.com/lbernardomaia/voucherApi
cd voucherApi
mvn clean package  
```

### VoucherAPI
```
java -jar voucher-api/target/voucher-api-0.0.1-SNAPSHOT.jar 
```
The API is running on port 8080.

E.g. http://localhost:8080/api/v1/client

### ClientAPI
```
java -jar voucher-client/target/voucher-api-client-0.0.1-SNAPSHOT.jar 
```
## Interacting with the Client API

The command ```help```  display a list of commands available

###Client Commands
-   Search a client by ID
```
client G7cwfHokOtDorjqFMuI3tA
```

-   Search a client by email, firstName, last-name, phone. E.g. searchClient -first-name BAILEY

```
searchClient -first-name BAILEY
```

```
searchClient -email 0.109006269972581@example.com -last-name McCreary 
```

```
searchClient -phone 2273077387365 
```
###Voucher Commands

- Search Voucher by clientId

```
searchVoucher WwEaIb0m4bhJphVtm2VgIw 
```
- Search Voucher by clientId and SerialNumber

```
searchVoucher WwEaIb0m4bhJphVtm2VgIw 10026
```

- Create Voucher

```
createVoucher WwEaIb0m4bhJphVtm2VgIw 50.0
```

To leave the terminal ```quit``` 
