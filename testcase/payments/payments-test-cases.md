# PRZYPADKI TESTOWE DLA AGREGATU PAYMENT

## PAY-SMOKE-01 - Wylistowanie wszystkich płatności

**Endpoint:**

`/payments`

**Metoda:**

GET

**Dane wejściowe:**

Brak

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 200 OK oraz listę wszystkich płatności.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 200 OK oraz listę płatności.

**Dowód:**

<img width="1492" height="752" alt="01 Wylistowanie Płatności" src="https://github.com/user-attachments/assets/71e40275-7963-44f3-9926-1eb2dec50b9c" />

**Status:** PASS

**UWAGI**

Brak

---

## PAY-SMOKE-02 - Pobranie płatności po ID

**Endpoint:**

`/payments/{id}`

**Metoda:**

GET

**Dane wejściowe:**

paymentId = 1

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 200 OK oraz dane płatności o wskazanym ID.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 200 OK oraz dane płatności.

**Dowód:**

<img width="1493" height="407" alt="02 Płatność Po ID" src="https://github.com/user-attachments/assets/3c66fa7e-e87c-4e33-a898-b5870e4c1bb2" />

**Status:** PASS

**UWAGI**

Brak

---

## PAY-SMOKE-03 - Pobranie płatności po ID zamówienia

**Endpoint:**

`/payments/order/{orderId}`

**Metoda:**

GET

**Dane wejściowe:**

orderId = 2

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 200 OK oraz płatność przypisaną do wskazanego zamówienia.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 200 OK oraz dane płatności.

**Dowód:**

<img width="1633" height="412" alt="image" src="https://github.com/user-attachments/assets/ea466d57-ecd1-4152-b4c6-9733ed64c0af" />

**Status:** PASS

**UWAGI**

Brak

---

## PAY-POS-04 - Aktualizacja płatności

**Endpoint:**

`/payments/1`

**Metoda:**

PUT

**Dane wejściowe:**

```json
{
  "amount": 70,
  "method": "ONLINE",
  "orderId": 2
}
```

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 200 OK oraz zaktualizowane dane płatności.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 200 OK oraz zaktualizowane dane płatności.

**Dowód:**

<img width="1497" height="435" alt="PAY-04 Aktualizacja Płatności" src="https://github.com/user-attachments/assets/154dbf92-f6c1-492a-92e5-66dd8c726e30" />

**Status:** PASS

**UWAGI**

Brak

---

## PAY-POS-05 - Usunięcie płatności

**Endpoint:**

`/payments/{id}`

**Metoda:**

DELETE

**Dane wejściowe:**

paymentId = 3

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 204 No Content.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 204 No Content.

**Dowód:**

<img width="1642" height="368" alt="05 Usunięcie Płatności" src="https://github.com/user-attachments/assets/69971e6d-5b20-458d-b9b4-5f1e6944dc14" />

**Status:** PASS

**UWAGI**

Poprawność usunięcia należy dodatkowo zweryfikować przez wykonanie GET /payments/{id}, który powinien zwrócić 404 Not Found.

---

## PAY-POS-06 - Aktualizacja statusu płatności

**Endpoint:**

`/payments/{id}/status`

**Metoda:**

PATCH

**Dane wejściowe:**

paymentId = 1

status = REFUNDED

**Oczekiwany Wynik:**

Aplikacja zwraca status HTTP 200 OK oraz zaktualizowany status płatności na REFUNDED.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 200 OK oraz zaktualizowała status płatności na REFUNDED.

**Dowód:**

<img width="1636" height="428" alt="06 Aktualizacja statusu płatności" src="https://github.com/user-attachments/assets/a50e508a-851a-49ad-9ad6-6f66c46deeb7" />

**Status:** PASS

**UWAGI**

Brak

## PAY-NEG-01 - Nieistniejąca płatność

**Endpoint:**

`/payments/{id}`

**Metoda:**

GET

**Dane wejściowe:**

`paymentId = 9999`

**Oczekiwany wynik:**

Aplikacja zwraca status HTTP 404 Not Found oraz komunikat informujący o braku płatności.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 404 Not Found oraz komunikat:

`Payment not found with ID 9999`

**Dowód:**

![N01](../screenshots-Payments/N01%20Nieistniejąca%20Płatność.png)

**Status:** PASS

**UWAGI**

Brak

---

## PAY-NEG-02 - Nieistniejące zamówienie

**Endpoint:**

`/payments/order/{orderId}`

**Metoda:**

GET

**Dane wejściowe:**

`orderId = 9999`

**Oczekiwany wynik:**

Aplikacja zwraca status HTTP 404 Not Found oraz komunikat informujący o braku płatności dla wskazanego zamówienia.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 404 Not Found oraz komunikat:

`Payment not found for order ID 9999`

**Dowód:**

![N02](../screenshots-Payments/N02%20Nieistniejące%20Zamówienie.png)

**Status:** PASS

**UWAGI**

Brak

## PAY-NEG-03 - Utworzenie płatności dla zamówienia z istniejącą płatnością

**Endpoint:**

`/payments`

**Metoda:**

POST

**Dane wejściowe:**

```json
{
  "amount": 36,
  "method": "ONLINE",
  "orderId": 1
}
```

**Oczekiwany wynik:**

Aplikacja nie pozwala na utworzenie kolejnej płatności dla zamówienia, które posiada już przypisaną płatność.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 500 Internal Server Error.

**Dowód:**

![N03](../screenshots-Payments/N03%20Utworzenie%20płatności%20dla%20zamówienia%20z%20istniejącą%20płatnością.png)

**Status:** PASS

**UWAGI**

System zablokował utworzenie drugiej płatności dla tego samego zamówienia.

---

## PAY-NEG-04 - Aktualizacja płatności z istniejącym orderId

**Endpoint:**

`/payments/{id}`

**Metoda:**

PUT

**Dane wejściowe:**

```json
{
  "amount": 36,
  "method": "ONLINE",
  "orderId": 1
}
```

oraz

`paymentId = 1`

**Oczekiwany wynik:**

Aplikacja nie pozwala przypisać płatności do zamówienia posiadającego już inną płatność.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 500 Internal Server Error.

**Dowód:**

![N04](../screenshots-Payments/N04%20Aktualizacja%20płatności%20z%20istniejącym%20orderId.png)

**Status:** PASS

**UWAGI**

System poprawnie zablokował operację powodującą konflikt danych.

---

## PAY-NEG-05 - Usunięcie nieistniejącej płatności

**Endpoint:**

`/payments/{id}`

**Metoda:**

DELETE

**Dane wejściowe:**

`paymentId = 9999`

**Oczekiwany wynik:**

Aplikacja zwraca status HTTP 404 Not Found.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 404 Not Found oraz komunikat:

`Payment not found with ID 9999`

**Dowód:**

![N05](../screenshots-Payments/N05%20Usunięcie%20nieistniejącej%20płatności.png)

**Status:** PASS

**UWAGI**

Brak.

---

## PAY-NEG-06 - Aktualizacja nieistniejącej płatności

**Endpoint:**

`/payments/{id}`

**Metoda:**

PUT

**Dane wejściowe:**

```json
{
  "amount": 70,
  "method": "ONLINE",
  "orderId": 2
}
```

oraz

`paymentId = 9999`

**Oczekiwany wynik:**

Aplikacja zwraca status HTTP 404 Not Found.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 404 Not Found oraz komunikat:

`Payment not found with ID 9999`

**Dowód:**

![N06](../screenshots-Payments/N06%20-%20Aktualizacja%20nieistniejącej%20płatności.png)

**Status:** PASS

**UWAGI**

Brak.

---

## PAY-NEG-07 - Utworzenie płatności dla nieistniejącego zamówienia

**Endpoint:**

`/payments`

**Metoda:**

POST

**Dane wejściowe:**

```json
{
  "amount": 50,
  "method": "ONLINE",
  "orderId": 9999
}
```

**Oczekiwany wynik:**

Aplikacja zwraca status HTTP 404 Not Found informujący o braku zamówienia.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 404 Not Found oraz komunikat:

`Order not found with ID 9999`

**Dowód:**

![N07](../screenshots-Payments/N07%20-%20Utworzenie%20płatności%20dla%20nieistniejącego%20zamówienia.png)

**Status:** PASS

**UWAGI**

Brak.

---

## PAY-NEG-08 - Aktualizacja statusu nieistniejącej płatności

**Endpoint:**

`/payments/{id}/status`

**Metoda:**

PATCH

**Dane wejściowe:**

`paymentId = 9999`

`status = REFUNDED`

**Oczekiwany wynik:**

Aplikacja zwraca status HTTP 404 Not Found.

**Wynik rzeczywisty:**

Aplikacja zwróciła status HTTP 404 Not Found oraz komunikat:

`Payment not found with ID 9999`

**Dowód:**

![N08](../screenshots-Payments/N08%20-%20Aktualizacja%20statusu%20nieistniejącej%20płatności.png)

**Status:** PASS

**UWAGI**

Brak.
