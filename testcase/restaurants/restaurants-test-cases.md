# PRZYPADKI TESTOWE DLA AGREGATU RESTAURANT

## RES-POS-03 - Utworzenie nowej restauracji

**Endpoint:** 

/restaurants  

**Metoda:**

POST

**Dane wejściowe:**

```json
{
  "name": "Test Restaurant",
  "address": "Testowa 1, Rzeszow",
  "openingHours": "10:00-22:00",
  "contactInfo": "123456789"
}
```

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 201 Created oraz dane utworzonej restauracji z nadanym ID.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 201 Created. Utworzono restaurację o ID 33.

```json
{
  "id": 33,
  "version": 0,
  "name": "Test Restaurant",
  "address": "Testowa 1, Rzeszow",
  "openingHours": "10:00-22:00",
  "contactInfo": "123456789",
  "products": []
}
```

**Status: PASS**

**Dowód:**

scr

Status: PASS

**UWAGI**

Brak

## RES-SMOKE-01 - Wylistowanie wszystkich restauracji

**Endpoint:**

/restaurants

**Metoda**

GET

**Dane wejsciowe**

Brak

**Oczekiwany Wynik**

200 OK oraz lista restauracji, wraz z ich atrybutami oraz listą produktów dostępnych w restauracji

**Wynik rzeczywisty:**

200 OK, zwrócono listę restauracji wraz z produktami

**Dowód:**

scr

Status: PASS

**UWAGI**

Brak

## RES-SMOKE-02 - Wyszukanie Restauracji po numerze ID

**Endpoint:**

/restaurants/{id}

**Metoda**

GET

**Dane wejsciowe**

Brak

**Oczekiwany Wynik**

200 OK oraz restauracja wybrana do wylistowania poprzez podanie numeru ID (testcase = 33)

**Wynik rzeczywisty:**

200 OK, zwrócono jedną, konkretną restauracje o ID 33 wraz z jej atrybutami

**Dowód:**

scr

Status: PASS

**UWAGI**

Brak

## RES-POS-04 - Aktualizacja Restauracji

**Endpoint:**

/restaurants/{id}

**Metoda**

PUT

**Dane wejsciowe**

```json
{
  "name": "Test Restaurant",
  "address": "Testowa 1, Rzeszow",
  "openingHours": "10:00-22:00",
  "contactInfo": "123456789"
}
```

**Oczekiwany Wynik**

200 OK oraz dane restauracji po aktualizacji danych

**Wynik rzeczywisty:**

200 OK, zwrócono nowe, zaktualizowane dane restauracji

```json
{
  "id": 33,
  "version": 1,
  "name": "Updated Test Restaurant",
  "address": "Zmieniona 2, Rzeszow",
  "openingHours": "09:00-21:00",
  "contactInfo": "987654321",
  "products": []
}
```

**Dowód:**

scr

Status: PASS

**UWAGI**

Brak

## RES-SMOKE-05 - Wylistowanie produktów w danej restauracji

**Endpoint:**

/restaurants/{id}/products

**Metoda**

GET 

**Dane wejsciowe**

Brak

**Oczekiwany Wynik**

200 OK, zapytanie zwraca liste produktów w restauracji

**Wynik rzeczywisty:**

200 OK, zapytanie zwraca liste produktów wraz z ich atrybutami które są przypisane do odpowiedniej restauracji 

**Dowód:**

scr

**UWAGI**

Status: PASS

Brak

## RES-POS-06 - Dodanie produktu do restauracji

**Endpoint:**

/restaurants/{id}/products

**Metoda**

POST

**Dane wejsciowe**

```json
{
  "name": "Testowa Sałatka",
  "description": "Testowa sałatka dodana przez Bruno",
  "category": "TEST",
  "price": 19.99,
  "available": true
}
```

**Oczekiwany Wynik**

200 OK, zapytanie dodaje do danych restauracji nowy produkt w niej dostępny

**Wynik rzeczywisty:**

200 OK, zwraca zaktualizowane dane o nowym produkcie w restauracji 

```json
{
  "id": 33,
  "version": 2,
  "name": "Updated Test Restaurant",
  "address": "Zmieniona 2, Rzeszow",
  "openingHours": "09:00-21:00",
  "contactInfo": "987654321",
  "products": [
    {
      "id": 33,
      "name": "Testowa Sałatka",
      "description": "Testowa sałatka dodana przez Bruno",
      "category": "TEST",
      "price": 19.99,
      "available": true,
      "variants": []
    }
  ]
}
```

**Dowód:**

scr

Status: PASS

**UWAGI**

Brak

## RES-POS-07 - Test Usunięcia produktu z restauracji

**Endpoint:**

/restaurants/{id}/products/{productId}

**Metoda**

DELETE

**Dane wejsciowe**

restaurantId
productId

**Oczekiwany Wynik**

200 OK, zapytanie usuwa z listy produktów wybrany produkt (dane wejściowe) z oferty restauracji

**Wynik rzeczywisty:**

200 OK, zwraca Zaktualizowaną oferte restauracji pomniejszoną o produkt z danych wejściowych

```json
{
  "id": 33,
  "version": 4,
  "name": "Updated Test Restaurant",
  "address": "Zmieniona 2, Rzeszow",
  "openingHours": "09:00-21:00",
  "contactInfo": "987654321",
  "products": [
    {
      "id": 33,
      "name": "Testowa Sałatka",
      "description": "Testowa sałatka dodana przez Bruno",
      "category": "TEST",
      "price": 19.99,
      "available": true,
      "variants": []
    }
  ]
}
```

**Dowód:**

scr

Status: PASS

**UWAGI**


Brak

## RES-POS-08 - Test usunięcia testowej restauracji

**Endpoint:**

/restaurants/{{restaurantId}}

**Metoda**

DELETE

**Dane wejsciowe**

Brak

**Oczekiwany Wynik**

204 No content, zapytanie usuwa z listy restauracji Restauracje o danym ID

**Wynik rzeczywisty:**

204 No content, zwraca pusty response 

...

**Dowód:**

scr

Status: PASS

**UWAGI**

Poprawność usunięcia należy dodatkowo potwierdzić przez wykonanie testu GET /restaurants/{id}, który powinien zwrócić 404 Not Found.

# PRZYPADKI TESTOWE - NEGATYWNE DLA AGREGATU RESTAURANT

## RES-NEG-01 - Wyszukanie nieistniejącej restauracji po numerze ID

Endpoint:

/restaurants/{id}

Metoda:

GET

Dane wejściowe:

restaurantId = 99999

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ restauracja o podanym ID nie istnieje w bazie danych.

Wynik rzeczywisty:

```json
{
  "message": "Restaurant not found with ID 99999",
  "status": 404,
  "timestamp": "2026-06-02T19:57:43.792145500Z"
}
```

Status: PASS

Dowód:

scr

UWAGI

Brak

## RES-NEG-02 - Utworzenie restauracji z pustą nazwą

Endpoint:

/restaurants

Metoda:

POST

Dane wejściowe:

```json
{
  "name": "",
  "address": "Barowa 67, Rzeszow",
  "openingHours": "10:00-22:00",
  "contactInfo": "12344412"
}
```

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ pole name jest wymagane i nie może być puste.

Wynik rzeczywisty:

{
  "message": "Błąd walidacji: [name: Nazwa restauracji nie może być pusta]",
  "status": 400,
  "timestamp": "2026-06-02T20:07:44.629652400Z"
}

Status: PASS

Dowód:

scr

UWAGI

Brak

## RES-NEG-03 - Utworzenie restauracji bez adresu

Endpoint:

/restaurants

Metoda:

POST

Dane wejściowe:

{
  "name": "Invalid Restaurant",
  "openingHours": "10:00-22:00",
  "contactInfo": "123456789"
}

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ pole address jest wymagane.

Wynik rzeczywisty:

{
  "message": "Błąd walidacji: [address: Adres restauracji nie może być pusty]",
  "status": 400,
  "timestamp": "2026-06-02T20:10:31.021642300Z"
}

Status: PASS

Dowód:

scr

UWAGI

Brak

RES-NEG-04 - Aktualizacja nieistniejącej restauracji

Endpoint:

/restaurants/{id}

Metoda:

PUT

Dane wejściowe:

restaurantId = 99

{
  "name": "Restauracja Rzeszowska",
  "address": "Nieistniejaca 1, Rzeszow",
  "openingHours": "09:00-21:00",
  "contactInfo": "000000000"
}

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ restauracja o podanym ID nie istnieje i nie może zostać zaktualizowana.

Wynik rzeczywisty:

{
  "message": "Restaurant not found with ID 99",
  "status": 404,
  "timestamp": "2026-06-02T20:15:31.045454Z"
}

Status: PASS

Dowód:

scr

UWAGI

Brak

## RES-NEG-05 - Usunięcie nieistniejącej restauracji

Endpoint:

/restaurants/{id}

Metoda:

DELETE

Dane wejściowe:

restaurantId = 99

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ restauracja o podanym ID nie istnieje i nie może zostać usunięta.

Wynik rzeczywisty:

{
  "message": "Restaurant not found with ID 99",
  "status": 404,
  "timestamp": "2026-06-02T20:17:46.342350Z"
}

Status: PASS

Dowód:

UWAGI

Brak

## RES-NEG-06 - Dodanie produktu z pustą nazwą do restauracji

Endpoint:

/restaurants/{id}/products

Metoda:

POST

Dane wejściowe:

restaurantId = 1

{
  "name": "",
  "description": "Produkt Nienazwany",
  "category": "TEST",
  "price": 19.99,
  "available": true
}

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ pole name produktu jest wymagane i nie może być puste.

Wynik rzeczywisty:

{
  "message": "Błąd walidacji: [name: Nazwa produktu nie może być pusta]",
  "status": 400,
  "timestamp": "2026-06-02T20:20:32.120912800Z"
}

Status: PASS

Dowód:

scr

UWAGI

Brak

## RES-NEG-07 - Dodanie produktu z ujemną ceną do restauracji

Endpoint:

/restaurants/{id}/products

Metoda:

POST

Dane wejściowe:

restaurantId = 1

{
  "name": "Kurczak w sosie Deflacji",
  "description": "Produkt Deflacyjny!!",
  "category": "TEST",
  "price": -10.00,
  "available": true
}

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ cena produktu musi być wartością dodatnią.

Wynik rzeczywisty:

{
  "message": "Błąd walidacji: [price: Cena produktu musi być dodatnia]",
  "status": 400,
  "timestamp": "2026-06-02T20:23:05.678262500Z"
}

Status: PASS

Dowód:

scr

UWAGI

Brak

## RES-NEG-08 - Dodanie produktu do nieistniejącej restauracji

Endpoint:

/restaurants/{id}/products

Metoda:

POST

Dane wejściowe:

restaurantId = 99999

{
  "name": "Serek wiejski z paluchem pizzowym",
  "description": "Bajecznie dobry",
  "category": "TEST",
  "price": 19.99,
  "available": true
}

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ restauracja o podanym ID nie istnieje i nie można dodać do niej produktu.

Wynik rzeczywisty:

{
  "message": "Restaurant not found with ID 9999",
  "status": 404,
  "timestamp": "2026-06-02T20:27:04.065743200Z"
}

Status: PASS

Dowód:

scr

UWAGI

Brak

## RES-NEG-09 - Usunięcie nieistniejącego produktu z restauracji

Endpoint:

/restaurants/{id}/products/{productId}

Metoda:

DELETE

Dane wejściowe:

restaurantId = 1
productId = 700

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ produkt o podanym ID nie istnieje w menu danej restauracji.

Wynik rzeczywisty:

{
  "message": "Product not found with ID 700 in restaurant 1",
  "status": 404,
  "timestamp": "2026-06-02T20:30:30.371158800Z"
}

Status: PASS

Dowód:

scr

UWAGI

Może zdarzyć się że aplikacja zwróci 200 OK mimo braku produktu, jeśli API uznaje usunięcie nieistniejącego produktu za operację neutralną

