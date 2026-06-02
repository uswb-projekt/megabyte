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

