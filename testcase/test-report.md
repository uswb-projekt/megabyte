# Raport z testów - Megabyte

## Zakres testów

Testy obejmują weryfikacje poprawności zaprogramowanych agregatów, połączeń między nimi i walidacji danych

- Restaurant
- FoodOrder
- Payment

Testy wykonano lokalnie dla aplikacji uruchomionej pod adresem:

http://localhost:8080

## Podsumowanie testów Restaurant

Wykonano testy API dla agregatu Restaurant z użyciem Bruno. Sprawdzono pobieranie listy restauracji, pobieranie restauracji po ID, tworzenie, aktualizację i usuwanie restauracji, a także pobieranie, dodawanie i usuwanie produktów z menu restauracji. Wykonano również przypadki negatywne obejmujące nieistniejące ID oraz niepoprawne dane wejściowe. Wyniki testów zostały udokumentowane w pliku **restaurants-test-cases.md** oraz potwierdzone zrzutami ekranu.
