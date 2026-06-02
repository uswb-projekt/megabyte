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

<img width="1551" height="424" alt="03 Utworzenie Restauracji" src="https://github.com/user-attachments/assets/99f37bbd-9d52-4c40-8370-7a5d93db0d0e" />

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

<img width="1542" height="350" alt="01 Wylistowanie Restauracji" src="https://github.com/user-attachments/assets/0915fa38-eb1c-4117-9724-d055a577f5de" />

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

<img width="1554" height="406" alt="02 Restauracja Po ID" src="https://github.com/user-attachments/assets/ea44c62a-4679-4e89-9d8e-87da1add0eb2" />

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

<img width="1556" height="441" alt="04 Zaktualizowanie Restauracji" src="https://github.com/user-attachments/assets/fb7fc843-7ba0-41ff-98c3-726c1dc03689" />

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

<img width="1558" height="790" alt="05 - wylistowanie produktów w danej restauracji" src="https://github.com/user-attachments/assets/3d8287fa-e298-4bb2-a0cc-ff3f236d468d" />

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

<img width="1556" height="558" alt="06 - Dodanie nowego produktu do restauracji" src="https://github.com/user-attachments/assets/6b5d0c9b-966a-4da8-861d-3f490d6df22d" />

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

<img width="1565" height="649" alt="07 - Usunięcie Produktu z Oferty" src="https://github.com/user-attachments/assets/1067607f-87f8-4a5a-9a38-b6cfb555e02b" />

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

<img width="1562" height="459" alt="08 Usuniecie Restauracji" src="https://github.com/user-attachments/assets/5b6cd423-275d-432f-8603-e3d821128cff" />

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

<img width="1661" height="304" alt="N01 - wyszukanie nieistniejącej restauracji" src="https://github.com/user-attachments/assets/fdaa60a3-612e-4822-8dd0-714b526a4ea3" />


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

```json
{
  "message": "Błąd walidacji: [name: Nazwa restauracji nie może być pusta]",
  "status": 400,
  "timestamp": "2026-06-02T20:07:44.629652400Z"
}
```

Status: PASS

Dowód:

<img width="1663" height="331" alt="N02 - utworzenie restauracji z pustą nazwą" src="https://github.com/user-attachments/assets/7a8bee08-2f00-400c-862a-ee38870fb249" />


UWAGI

Brak

## RES-NEG-03 - Utworzenie restauracji bez adresu

Endpoint:

/restaurants

Metoda:

POST

Dane wejściowe:

```json
{
  "name": "Invalid Restaurant",
  "openingHours": "10:00-22:00",
  "contactInfo": "123456789"
}
```

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ pole address jest wymagane.

Wynik rzeczywisty:

```json

{
  "message": "Błąd walidacji: [address: Adres restauracji nie może być pusty]",
  "status": 400,
  "timestamp": "2026-06-02T20:10:31.021642300Z"
}
```

Status: PASS

Dowód:

<img width="1668" height="402" alt="N03 - utworzenie restauracji bez adresu" src="https://github.com/user-attachments/assets/1ea7b38d-14ec-449c-b437-62f35ff03c2a" />

UWAGI

Brak

RES-NEG-04 - Aktualizacja nieistniejącej restauracji

Endpoint:

/restaurants/{id}

Metoda:

PUT

Dane wejściowe:
```json

restaurantId = 99

{
  "name": "Restauracja Rzeszowska",
  "address": "Nieistniejaca 1, Rzeszow",
  "openingHours": "09:00-21:00",
  "contactInfo": "000000000"
}
```

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ restauracja o podanym ID nie istnieje i nie może zostać zaktualizowana.

Wynik rzeczywisty:
```json

{
  "message": "Restaurant not found with ID 99",
  "status": 404,
  "timestamp": "2026-06-02T20:15:31.045454Z"
}
```

Status: PASS

Dowód:

<img width="1667" height="398" alt="N04  -aktualizacja nieistniejącej restauracji" src="https://github.com/user-attachments/assets/c91b9d93-6a7f-4b9a-bdd0-873c5f2e26e9" />

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
```json

{
  "message": "Restaurant not found with ID 99",
  "status": 404,
  "timestamp": "2026-06-02T20:17:46.342350Z"
}
```

Status: PASS

Dowód:

<img width="1667" height="274" alt="N05 - Usuniecie nieistniejacej restauracji" src="https://github.com/user-attachments/assets/9dc34e42-72b6-4b99-a8fd-5fa46355bccd" />

UWAGI

Brak

## RES-NEG-06 - Dodanie produktu z pustą nazwą do restauracji

Endpoint:

/restaurants/{id}/products

Metoda:

POST

Dane wejściowe:

restaurantId = 1
```json

{
  "name": "",
  "description": "Produkt Nienazwany",
  "category": "TEST",
  "price": 19.99,
  "available": true
}
```

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ pole name produktu jest wymagane i nie może być puste.

Wynik rzeczywisty:

```json

{
  "message": "Błąd walidacji: [name: Nazwa produktu nie może być pusta]",
  "status": 400,
  "timestamp": "2026-06-02T20:20:32.120912800Z"
}
```

Status: PASS

Dowód:

<img width="1661" height="281" alt="N06 - dodanie produktu bez nazwy" src="https://github.com/user-attachments/assets/3a6c0fcb-c269-4766-a9b1-79aa7f1ff09b" />

UWAGI

Brak

## RES-NEG-07 - Dodanie produktu z ujemną ceną do restauracji

Endpoint:

/restaurants/{id}/products

Metoda:

POST

Dane wejściowe:

restaurantId = 1
```json
{
  "name": "Kurczak w sosie Deflacji",
  "description": "Produkt Deflacyjny!!",
  "category": "TEST",
  "price": -10.00,
  "available": true
}
```
Oczekiwany Wynik:

Aplikacja zwraca status HTTP 400 Bad Request, ponieważ cena produktu musi być wartością dodatnią.

Wynik rzeczywisty:

```json

{
  "message": "Błąd walidacji: [price: Cena produktu musi być dodatnia]",
  "status": 400,
  "timestamp": "2026-06-02T20:23:05.678262500Z"
}
```

Status: PASS

Dowód:

<img width="1670" height="294" alt="N07 - dodanie produktu z ujemną ceną" src="https://github.com/user-attachments/assets/5f88613b-9f43-40a8-b76b-39b1a635f80b" />

UWAGI

Brak

## RES-NEG-08 - Dodanie produktu do nieistniejącej restauracji

Endpoint:

/restaurants/{id}/products

Metoda:

POST

Dane wejściowe:
```json

restaurantId = 99999

{
  "name": "Serek wiejski z paluchem pizzowym",
  "description": "Bajecznie dobry",
  "category": "TEST",
  "price": 19.99,
  "available": true
}
```

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ restauracja o podanym ID nie istnieje i nie można dodać do niej produktu.

Wynik rzeczywisty:

```json
{
  "message": "Restaurant not found with ID 9999",
  "status": 404,
  "timestamp": "2026-06-02T20:27:04.065743200Z"
}
```

Status: PASS

Dowód:

<img width="1668" height="300" alt="N08 - dodanie produktu do nieistniejącej restauracji" src="https://github.com/user-attachments/assets/12c88689-bcce-408d-be2e-e4ffb739f6ba" />

UWAGI

Brak

## RES-NEG-09 - Usunięcie nieistniejącego produktu z restauracji

Endpoint:

/restaurants/{id}/products/{productId}

Metoda:

DELETE

Dane wejściowe:

```json

restaurantId = 1
productId = 700
```

Oczekiwany Wynik:

Aplikacja zwraca status HTTP 404 Not Found, ponieważ produkt o podanym ID nie istnieje w menu danej restauracji.

Wynik rzeczywisty:

```json
{
  "message": "Product not found with ID 700 in restaurant 1",
  "status": 404,
  "timestamp": "2026-06-02T20:30:30.371158800Z"
}
```

Status: PASS

Dowód:

<img width="1663" height="386" alt="N09 - Usuniecie nieistniejącego produktu" src="https://github.com/user-attachments/assets/6e0ddf28-c603-4cbb-9d1c-aaf8fe0a4868" />

UWAGI

Może zdarzyć się że aplikacja zwróci 200 OK mimo braku produktu, jeśli API uznaje usunięcie nieistniejącego produktu za operację neutralną

