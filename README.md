# REST-SOAP Adapter Configuration and Usage

## Project Structure
```
rest-soap-adapter/
├── pom.xml
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── RestSoapAdapterApplication.java
│   │   │   └── org/example/rest/
│   │   │       ├── CalculatorController.java
│   │   │       ├── CalculatorService.java
│   │   │       ├── CalculatorRequest.java
│   │   │   └── org/example/soap/
│   │   │       ├── SoapClient.java
│   │   └── resources/
│   │       ├── application.yml
│   │       └── wsdl/
│   │           └── calculator.wsdl
│   └── test/
│       └── java/
│           └── org/example/rest/
│               └── CalculatorControllerTest.java
│           └── org/example/soap/
│               └── SoapClientTest.java
└── target/
    └── generated-sources/
        └── wsimport/
            └── org/example/webservice/client/
```
## Step-by-Step Setup
### 1. Create Maven Project
```bash
mvn archetype:generate -DgroupId=com.example -DartifactId=wsdl-jaxws-client \
    -DarchetypeArtifactId=maven-archetype-quickstart -DinteractiveMode=false
cd wsdl-jaxws-client
```

### 2. Replace pom.xml
Use the JAX-WS specific pom.xml from the artifact above.

### 3. Create Directory Structure
```bash
mkdir -p src/main/resources/wsdl
mkdir -p src/test/java/com/example/client
```

### 4. Add WSDL Files (if using local files)
```bash
# Place your WSDL files in src/main/resources/wsdl/
cp your-service.wsdl src/main/resources/wsdl/
```

### 5. Generate Classes
```bash
mvn clean generate-sources
```

### 6. Check Generated Classes
```bash
# Generated classes will be in:
ls target/generated-sources/wsimport/
```
### 7. Compile and Run
```bash
mvn compile
mvn exec:java
```
### Manual wsimport (for testing)
```bash
# If you have JDK 8
wsimport -keep -s target/generated-sources/wsimport \
  -p com.example.test http://example.com/service?wsdl

# If you have JDK 11+, use Maven plugin instead
```
## Common wsimport Parameters

| Parameter | Description | Example |
|-----------|-------------|---------|
| `-keep` | Keep generated source files | `<keep>true</keep>` |
| `-verbose` | Enable verbose output | `<verbose>true</verbose>` |
| `-p` | Package name for generated classes | `<packageName>com.example</packageName>` |
| `-s` | Source destination directory | `<sourceDestDir>target/generated</sourceDestDir>` |
| `-d` | Destination directory for class files | Not commonly used with Maven |
| `-extension` | Enable JAX-WS 2.0 extensions | `<extension>true</extension>` |
| `-target` | Generate code for JAX-WS version | `<target>2.0</target>` |

## Configuration Files

### application.yml
```yaml
server:
  port: 8080
  servlet:
    context-path: /

spring:
  application:
    n: rest-soap-adapter
  jackson:
    default-property-inclusion: NON_NULL
    serialization:
      write-dates-as-timestamps: false

# SOAP Service Configuration
soap:
  calculator:
    endpoint: http://localhost:8080/calculator  # Mock service for development
    timeout:
      connect: 30000  # 30 seconds
      request: 60000  # 60 seconds

# Actuator Configuration
management:
  endpoints:
    web:
      exposure:
        include: health,info,metrics
  endpoint:
    health:
      show-details: always

# Logging Configuration
logging:
  level:
    org.example.rest: DEBUG
    org.springframework.web: INFO
    root: INFO
```

## REST API Endpoints

### 1. POST Endpoints (with JSON body)

#### Add Two Numbers
```bash
curl --location 'http://localhost:8080/api/calculator/add' \
--header 'Content-Type: application/json' \
--data '{"a":5,"b":5}'
```

#### Subtract
```bash
curl --location 'http://localhost:8080/api/calculator/subtract' \
--header 'Content-Type: application/json' \
--data '{"a":5,"b":5}'
```

#### Multiply
```bash
curl --location 'http://localhost:8080/api/calculator/multiply' \
--header 'Content-Type: application/json' \
--data '{"a":5,"b":5}'
```

#### Divide
```bash
curl --location 'http://localhost:8080/api/calculator/divide' \
--header 'Content-Type: application/json' \
--data '{"a":5,"b":5}'
```

## Response Format

### Success Response
```json
{
    "result": 25,
    "operandA": 5,
    "operandB": 5,
    "expression": "5 * 5 = 25",
    "success": true,
    "operation": "multiply"
}
```

## Test Class

### CalculatorControllerTest.java
### SoapClientTest.java

## Build and Run Instructions

### 1. Build the Project
```bash
# Generate SOAP client classes and compile
mvn clean generate-sources compile

# Run tests
mvn test
```

### 2. Run the Application
```bash
# Using Maven
mvn spring-boot:run
```

### 3. Access the Application
- **REST API**: http://localhost:8080/api/calculator/