# Przypadki testowe - Payment

## PAY-SMOKE-01 - Sprawdzenie dostępności endpointu GET /payments

Warunki wstępne:
- aplikacja jest uruchomiona lokalnie,
- Swagger UI jest dostępny,
- endpoint /payments jest widoczny w Swaggerze.

Kroki:
1. Wysłać żądanie GET na endpoint /payments.
2. Sprawdzić kod odpowiedzi HTTP.
3. Sprawdzić body odpowiedzi.

Oczekiwany rezultat:
- aplikacja zwraca status HTTP 200 OK,
- odpowiedź jest w formacie JSON,
- odpowiedź zawiera pola status oraz message.

Wynik rzeczywisty:
- HTTP 200 OK,
- odpowiedź:
  {
    "message": "payment-ok",
    "status": "OK"
  }

Status:
- PASS.

## PAY-SMOKE-02 - Sprawdzenie dostępności endpointu POST /payments

Warunki wstępne:
- aplikacja jest uruchomiona lokalnie,
- endpoint POST /payments jest widoczny w Swaggerze.

Kroki:
1. Wysłać żądanie POST na endpoint /payments.
2. Przekazać przykładowe dane testowe.
3. Sprawdzić kod odpowiedzi HTTP.
4. Sprawdzić body odpowiedzi.

Oczekiwany rezultat:
- aplikacja zwraca status HTTP 200 OK,
- odpowiedź zawiera komunikat potwierdzający wykonanie operacji.

Status:
- Do wykonania.

## PAY-FUNC-01 - Utworzenie płatności z poprawnymi danymi

Status:
- Nie można wykonać na obecnym etapie.

Powód:
- endpoint nie zapisuje jeszcze realnej płatności w bazie danych.

## PAY-FUNC-02 - Pobranie płatności po ID

Status:
- Nie można wykonać na obecnym etapie.

Powód:
- brak endpointu GET /payments/{id}.

## PAY-FUNC-03 - Aktualizacja statusu płatności

Status:
- Nie można wykonać na obecnym etapie.

Powód:
- brak endpointu PUT/PATCH dla aktualizacji płatności.

## PAY-FUNC-04 - Usunięcie płatności

Status:
- Nie można wykonać na obecnym etapie.

Powód:
- brak endpointu DELETE /payments/{id}.
