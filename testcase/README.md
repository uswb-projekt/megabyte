# Testcase - Megabyte

Folder zawiera dokumentację testową przygotowaną dla projektu Megabyte.

Aktualnie aplikacja udostępnia podstawowe endpointy dla dwóch agregatów:

- FoodOrder
- Payment

Dostępne endpointy:

- GET /food-orders
- POST /food-orders
- GET /payments
- POST /payments

Na obecnym etapie możliwe jest wykonanie podstawowych testów smoke, czyli sprawdzenie, czy endpointy są dostępne i czy zwracają odpowiedź HTTP 200 OK.

Pełne testy funkcjonalne nie są jeszcze możliwe, ponieważ endpointy zwracają głównie statyczne komunikaty typu "OK" i nie obsługują jeszcze pełnych operacji na danych w bazie.

Do dalszych testów wymagane jest dodanie:

- pobierania obiektu po ID,
- pobierania realnej listy obiektów z bazy,
- zapisu danych do bazy,
- aktualizacji obiektów,
- usuwania obiektów,
- walidacji danych wejściowych,
- odpowiedzi JSON zawierających dane konkretnych obiektów.

<img width="1904" height="775" alt="image" src="https://github.com/user-attachments/assets/f274049e-3e0b-4caa-a746-5f847c28734c" />
