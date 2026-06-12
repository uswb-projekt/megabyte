# Aplikacja Megabyte (System obsługi zamówień z dostawą)

## Dziedzina i konteksty

Aplikacja modeluje dziedzinę zamawiania i logistyki dostarczania produktów z restauracji oraz sklepów. W modelu wyodrębniono 8 poddziedzin: zamówienia (dziedzina główna), autentykacja, dostarczanie, marketing, oferta, płatności, reklamacje oraz restauracje.

W każdej z poddziedzin zlokalizowany jest kontekst ograniczony: Zamówienia (Food Orders), Autentykacja (Authentication), Dostarczanie (Delivery), Marketing (Marketing), Oferta (Menu), Płatności (Payments), Reklamacje (Complaints), Restauracje (Restaurants).

## Uruchomienie projektu

Aby uruchomić projekt lokalnie, należy przejść do głównego folderu projektu i wykonać:

```bash
.\gradlew.bat clean bootRun
```

Na systemach Linux/macOS:

```bash
./gradlew clean bootRun
```

Po uruchomieniu aplikacji należy sprawdzić Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

Jeżeli Swagger działa, można wykonywać testy API w Bruno.

## Uruchomienie kolekcji Bruno

1. Pobrać i zainstalować Bruno:

```text
https://www.usebruno.com/downloads
```

2. Uruchomić aplikację Megabyte lokalnie.

3. Otworzyć Bruno.

4. Wybrać opcję:

```text
Open Collection
```

5. Wskazać folder:

```text
megabyte/testcase/bruno
```

6. Wybrać środowisko:

```text
local
```

7. Wykonać pierwszy request testowy, np.:

```text
GET {{baseUrl}}/restaurants
```

Oczekiwany wynik:

```text
HTTP 200 OK
```
