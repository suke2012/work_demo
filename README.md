# MetadataGuard Demo (Java 8)

## Run tests

```bash
mvn clean test
```

## Run application (includes demo REST endpoint)

```bash
mvn spring-boot:run
```

Hit the endpoint:

```bash
curl -X POST http://localhost:8080/accounts \
  -H "Content-Type: application/json" \
  -d '{"source":"ACCOUNT_API","extInfo":{"amount":"123"},"creditAccount":{"extInfo":{"foo":"bar"}}}'
```
