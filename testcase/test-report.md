# Raport z testów - Megabyte

## Zakres testów

Testy obejmują weryfikacje poprawności zaprogramowanych agregatów, połączeń między nimi i walidacji danych

- Restaurant
- FoodOrder
- Payment

Testy wykonano lokalnie dla aplikacji uruchomionej pod adresem:

http://localhost:8080

## Podsumowanie testów Restaurant

Wykonano testy API dla agregatu Restaurant z użyciem Bruno.

Sprawdzono następujące obszary:

pobieranie listy restauracji,
pobieranie restauracji po ID,
tworzenie nowej restauracji,
aktualizacja danych restauracji,
usuwanie restauracji,
pobieranie produktów restauracji,
dodawanie produktu do restauracji,
usuwanie produktu z restauracji,
obsługa nieistniejących ID,
walidacja niepoprawnych danych wejściowych.

Przykładowe testowane endpointy:

GET /restaurants
GET /restaurants/{id}
POST /restaurants
PUT /restaurants/{id}
DELETE /restaurants/{id}
GET /restaurants/{id}/products
POST /restaurants/{id}/products
DELETE /restaurants/{id}/products/{productId}

Wyniki testów zostały udokumentowane w pliku:

restaurants/restaurants-test-cases.md

Dowody wykonania testów zostały zapisane w folderze:

screenshots/restaurants/

## Podsumowanie testów FoodOrder

Wykonano testy API dla agregatu FoodOrder.

Zakres testów obejmował operacje związane z obsługą zamówień, w tym tworzenie, pobieranie, aktualizację i usuwanie zamówień oraz sprawdzenie wybranych przypadków negatywnych.

Wyniki testów zostały udokumentowane w pliku:

food-orders/food-orders-test-cases.md

Dowody wykonania testów zostały zapisane w folderze:

screenshots/food-orders/

## Podsumowanie testów Payment

Wykonano testy API dla agregatu Payment.

Zakres testów obejmował operacje związane z płatnościami, w tym tworzenie płatności, pobieranie płatności, aktualizację, usuwanie oraz przypadki negatywne.

Wyniki testów zostały udokumentowane w pliku:

payments/payments-test-cases.md

Dowody wykonania testów zostały zapisane w folderze:

screenshots/payments/

## Rodzaje wykonanych testów
Testy smoke

Sprawdzono, czy podstawowe endpointy są dostępne i zwracają odpowiedzi HTTP.

Przykład:

GET /restaurants
GET /food-orders
GET /payments
Testy pozytywne

Sprawdzono poprawne scenariusze działania aplikacji, np.:

utworzenie restauracji,
aktualizacja restauracji,
dodanie produktu do restauracji,
utworzenie zamówienia,
utworzenie płatności.
Testy negatywne

Sprawdzono reakcję aplikacji na błędne dane lub nieistniejące zasoby, np.:

pobranie nieistniejącej restauracji,
utworzenie restauracji z pustą nazwą,
dodanie produktu z niepoprawną ceną,
operacje na nieistniejących ID.
Testy walidacyjne

Sprawdzono, czy aplikacja zwraca odpowiednie błędy dla niepoprawnych danych wejściowych.
