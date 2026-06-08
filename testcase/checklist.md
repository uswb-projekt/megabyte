# Checklist testera - Megabyte

## 1. Uruchomienie aplikacji

* [x] Projekt został pobrany z repozytorium GitHub
* [x] Projekt został uruchomiony lokalnie
* [x] Aplikacja działa na porcie `8080`
* [x] Dostępna jest konsola H2
* [x] Dostępny jest Swagger UI
* [x] Swagger pokazuje endpointy dla agregatów:

  * [x] Restaurant
  * [x] FoodOrder
  * [x] Payment
* [x] Aplikacja umożliwia wykonywanie requestów przez Bruno

## 2. Środowisko Bruno

* [x] Utworzono kolekcję Bruno w folderze `testcase/bruno`
* [x] Dodano środowisko lokalne
* [x] Ustawiono zmienną `baseUrl = http://localhost:8080`
* [x] Przygotowano foldery Bruno dla agregatów:

  * [x] restaurants
  * [x] food-orders
  * [x] payments
* [x] Requesty Bruno zostały zapisane w repozytorium
* [x] Kolekcję Bruno można otworzyć przez `Open Collection`

## 3. Dokumentacja testowa

* [x] Utworzono folder `testcase`
* [x] Utworzono README dla części testowej
* [x] Utworzono pliki przypadków testowych dla agregatów:

  * [x] `restaurants/restaurants-test-cases.md`
  * [x] `food-orders/food-orders-test-cases.md`
  * [x] `payments/payments-test-cases.md`
* [x] Utworzono zbiorczy raport testów `test-report.md`
* [x] Dodano folder na zrzuty ekranu
* [x] Zrzuty ekranu zostały pogrupowane według agregatów

## 4. Agregat Restaurant

### Endpointy podstawowe

* [x] GET `/restaurants` - pobranie listy restauracji
* [x] GET `/restaurants/{id}` - pobranie restauracji po ID
* [x] POST `/restaurants` - utworzenie nowej restauracji
* [x] PUT `/restaurants/{id}` - aktualizacja restauracji
* [x] DELETE `/restaurants/{id}` - usunięcie restauracji

### Endpointy produktów restauracji

* [x] GET `/restaurants/{id}/products` - pobranie produktów restauracji
* [x] POST `/restaurants/{id}/products` - dodanie produktu do restauracji
* [x] DELETE `/restaurants/{id}/products/{productId}` - usunięcie produktu z restauracji

### Testy Restaurant

* [x] Wykonano testy smoke
* [x] Wykonano testy pozytywne
* [x] Wykonano testy negatywne
* [x] Sprawdzono odpowiedzi `200 OK`
* [x] Sprawdzono odpowiedzi `201 Created`
* [x] Sprawdzono odpowiedzi `204 No Content`
* [x] Sprawdzono odpowiedzi `400 Bad Request`
* [x] Sprawdzono odpowiedzi `404 Not Found`
* [x] Udokumentowano wyniki w `restaurants-test-cases.md`
* [x] Dodano zrzuty ekranu jako dowody wykonania testów

## 5. Agregat FoodOrder

* [x] Przygotowano requesty Bruno dla agregatu FoodOrder
* [x] Przygotowano przypadki testowe dla agregatu FoodOrder
* [x] Wykonano testy pozytywne
* [x] Wykonano testy negatywne
* [x] Udokumentowano wyniki w `food-orders-test-cases.md`
* [x] Dodano zrzuty ekranu jako dowody wykonania testów

## 6. Agregat Payment

* [x] Przygotowano requesty Bruno dla agregatu Payment
* [x] Przygotowano przypadki testowe dla agregatu Payment
* [x] Wykonano testy pozytywne
* [x] Wykonano testy negatywne
* [x] Udokumentowano wyniki w `payments-test-cases.md`
* [x] Dodano zrzuty ekranu jako dowody wykonania testów

## 7. Testy negatywne i walidacyjne

* [x] Sprawdzono obsługę nieistniejących ID
* [x] Sprawdzono walidację pustych pól
* [x] Sprawdzono walidację niepoprawnych danych wejściowych
* [x] Sprawdzono reakcję API na błędne requesty
* [x] Udokumentowano przypadki `400 Bad Request`
* [x] Udokumentowano przypadki `404 Not Found`

## 8. Raport i dokumentacja końcowa

* [x] Wyniki testów zostały opisane w plikach `.md`
* [x] Dowody testów zostały zapisane jako zrzuty ekranu
* [x] Przygotowano podstawę do dokumentacji zbiorczej w Wordzie
* [x] Folder `testcase` zawiera kompletną część testową projekt

Do testowania API wykorzystano narzędzie Bruno oraz dokumentację Swagger UI.

