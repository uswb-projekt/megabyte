# Checklist testera

## Uruchomienie aplikacji

- [x] Projekt został uruchomiony lokalnie
- [x] Aplikacja działa na porcie 8080
- [x] Dostępna jest konsola H2
- [x] Dostępny jest Swagger UI
- [x] Swagger pokazuje endpointy FoodOrder i Payment

## Endpointy FoodOrder

- [x] GET /food-orders jest dostępny
- [ ] POST /food-orders został sprawdzony
- [ ] GET /food-orders/{id} - brak endpointu
- [ ] PUT/PATCH /food-orders/{id} - brak endpointu
- [ ] DELETE /food-orders/{id} - brak endpointu

## Endpointy Payment

- [x] GET /payments jest dostępny
- [ ] POST /payments został sprawdzony
- [ ] GET /payments/{id} - brak endpointu
- [ ] PUT/PATCH /payments/{id} - brak endpointu
- [ ] DELETE /payments/{id} - brak endpointu

## Uwagi

Aktualnie możliwe są tylko podstawowe testy dostępności API. Pełne testy funkcjonalne będą możliwe po rozbudowie kontrolerów REST.
