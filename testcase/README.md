# Testcase - Megabyte

Folder `testcase` zawiera dokumentację testową oraz kolekcję Bruno przygotowaną do testowania API projektu **Megabyte**.

Testy dotyczą trzech głównych agregatów aplikacji:

* `Restaurant` - restauracje oraz produkty/menu restauracji,
* `FoodOrder` - zamówienia,
* `Payment` - płatności.

Testy są wykonywane na aplikacji uruchomionej lokalnie pod adresem:

```text
http://localhost:8080
```

Dokumentacja endpointów jest dostępna w Swagger UI:

```text
http://localhost:8080/swagger-ui/index.html
```

## Struktura folderu

```text
testcase/
├── bruno/
│   ├── environments/
│   ├── restaurants/
│   ├── food-orders/
│   └── payments/
│
├── restaurants/
│   └── restaurants-test-cases.md
│
├── food-orders/
│   └── food-orders-test-cases.md
│
├── payments/
│   └── payments-test-cases.md
│
├── screenshots/
│   └── restaurants/
│
├── checklist.md
├── test-report.md
└── README.md
```

## Zawartość folderów

### `bruno/`

Folder zawiera kolekcję Bruno z requestami API oraz środowiskiem testowym.

Kolekcja jest podzielona według agregatów:

* `bruno/restaurants` - requesty dla agregatu `Restaurant`,
* `bruno/food-orders` - requesty dla agregatu `FoodOrder`,
* `bruno/payments` - requesty dla agregatu `Payment`.

Środowisko lokalne znajduje się w:

```text
testcase/bruno/environments/
```

Podstawowa zmienna środowiskowa:

```text
baseUrl = http://localhost:8080
```

### `restaurants/`

Folder zawiera przypadki testowe dla agregatu `Restaurant`.

Zakres testów obejmuje między innymi:

* pobranie listy restauracji,
* pobranie restauracji po ID,
* utworzenie nowej restauracji,
* aktualizację danych restauracji,
* usunięcie restauracji,
* pobranie produktów restauracji,
* dodanie produktu do restauracji,
* usunięcie produktu z restauracji,
* przypadki negatywne i walidacyjne.

### `food-orders/`

Folder zawiera przypadki testowe dla agregatu `FoodOrder`.

Zakres testów obejmuje między innymi:

* tworzenie zamówienia,
* pobieranie zamówień,
* pobieranie zamówienia po ID,
* aktualizację zamówienia,
* usuwanie zamówienia,
* obsługę statusów zamówienia,
* przypadki negatywne i walidacyjne.

### `payments/`

Folder zawiera przypadki testowe dla agregatu `Payment`.

Zakres testów obejmuje między innymi:

* tworzenie płatności,
* pobieranie płatności,
* pobieranie płatności po ID,
* aktualizację płatności,
* usuwanie płatności,
* przypadki negatywne i walidacyjne.

### `screenshots/`

Folder zawiera zrzuty ekranu dokumentujące wykonanie testów.

Zrzuty ekranu powinny być przechowywane w podfolderach odpowiadających agregatom, np.:

```text
testcase/screenshots/restaurants/
testcase/screenshots/food-orders/
testcase/screenshots/payments/
```

Zalecany format nazw plików:

```text
ID_TESTU_krótki_opis.png
```

Przykład:

```text
RES-POS-03_create_restaurant.png
RES-NEG-02_create_restaurant_empty_name.png
```

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

## Zasady dokumentowania przypadków testowych

Każdy przypadek testowy powinien zawierać:

* ID testu,
* nazwę testu,
* endpoint,
* metodę HTTP,
* dane wejściowe,
* oczekiwany wynik,
* wynik rzeczywisty,
* status testu,
* dowód w postaci zrzutu ekranu,
* uwagi.

Przykładowy status testu:

```text
PASS
FAIL
BLOCKED
TODO
```

## Rodzaje testów

W projekcie wykonywane są następujące typy testów:

* testy smoke - sprawdzenie dostępności endpointów,
* testy pozytywne - poprawne operacje na danych,
* testy negatywne - błędne dane wejściowe lub nieistniejące zasoby,
* testy walidacyjne - sprawdzenie poprawności obsługi błędnych requestów,
* testy CRUD - tworzenie, odczyt, aktualizacja i usuwanie danych.

## Uwagi organizacyjne

* Plików lokalnej bazy H2 z folderu `data/` nie należy commitować do repozytorium.
* Zmiany w testach Bruno należy commitować razem z odpowiednimi przypadkami testowymi.
* Screeny powinny dokumentować tylko istotne wyniki testów.
* Każdy tester odpowiada za swój agregat i aktualizuje odpowiedni folder w `testcase`.
* Dokumentacja końcowa w Word powinna bazować na plikach znajdujących się w folderze `testcase`.
