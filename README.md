# Unit Converter

A Spring Boot web application for converting between units of measurement across multiple categories.

Project roadmap.sh url: https://roadmap.sh/projects/unit-converter

## Features

- **Length** — millimeter, centimeter, meter, kilometer, inch, foot, yard, mile
- **Weight** — milligram, gram, kilogram, ounce, pound
- **Temperature** — Celsius, Fahrenheit, Kelvin
- **Volume** — liters, milliliters, gallons, cups, and more

Each unit category has its own dedicated page. The app also exposes a REST API for programmatic conversions.

## Requirements

- Java 17+
- Maven

## How to start

```bash
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

| Page | URL |
|------|-----|
| Home | `http://localhost:8080/` |
| Length | `http://localhost:8080/length` |
| Weight | `http://localhost:8080/weight` |
| Temperature | `http://localhost:8080/temperature` |

## REST API

| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/categories` | List all unit categories |
| GET | `/api/units/{category}` | List units for a category |
| POST | `/api/convert` | Convert a value between units |

### Convert example

```bash
curl -X POST http://localhost:8080/api/convert \
  -H "Content-Type: application/json" \
  -d '{"category": "LENGTH", "sourceUnit": "meter", "targetUnit": "foot", "value": 1}'
```

## API Documentation (Swagger)

Interactive API documentation is available via Swagger UI once the application is running.

| Resource | URL |
|----------|-----|
| Swagger UI | `http://localhost:8080/swagger-ui/index.html` |
| OpenAPI JSON | `http://localhost:8080/v3/api-docs` |
| OpenAPI YAML | `http://localhost:8080/v3/api-docs.yaml` |

## Running tests

```bash
./mvnw test
```