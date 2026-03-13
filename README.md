# Unit Converter

A Spring Boot web application for converting between units of measurement across multiple categories.

## Features

- **Length** — meters, kilometers, miles, feet, inches, and more
- **Weight** — kilograms, grams, pounds, ounces, and more
- **Temperature** — Celsius, Fahrenheit, Kelvin
- **Volume** — liters, milliliters, gallons, cups, and more

The app includes a web UI and a REST API.

## Requirements

- Java 17+
- Maven

## How to start

```bash
./mvnw spring-boot:run
```

The application starts on `http://localhost:8080`.

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
  -d '{"category": "LENGTH", "fromUnit": "METER", "toUnit": "FOOT", "value": 1}'
```

## Running tests

```bash
./mvnw test
```