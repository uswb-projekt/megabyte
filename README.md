# System obsługi zamówień z dostawą

## Brudnopis
https://docs.google.com/document/d/1_yS38bcsTMefnQcKZWZIMeikfWNOddROzf7ziSlS2r8/edit?usp=sharing

## Opis wymagań
System umożliwia składanie zamówień przez klientów na produkty oferowane przez restauracje i sklepy, a następnie obsługę procesu ich realizacji i dostawy przez kurierów. Każde zamówienie jest przypisywane do konkretnego klienta oraz restauracji/sklepu. Zawiera listę wybranych produktów wraz z ich ilością, ceną oraz ewentualnymi uwagami klienta.

Zamówienie rejestrowane jest w systemie z uwzględnieniem daty i czasu złożenia zamówienia, przewidywanego czasu realizacji, statusu zamówienia, form płatności oraz adresu dostawy. System umożliwia śledzenie statusu zamówienia na każdym etapie jego realizacji.

Każda restauracja/sklep posiada w systemie swoją kartotekę, w której przechowywane są informacje (nazwa, adres, godziny otwarcia, dane kontaktowe, lista oferowanych produktów). Produkty posiadają swoją nazwę, opis, kategorię, cenę oraz dostępność. W przypadku restauracji możliwe jest określenie wariantów produktu.

Klient systemu posiada swoją kartotekę zawierającą dane osobowe oraz kontaktowe (imię, nazwisko, numer telefonu, adres e-mail, adresy dostawy). Klient może posiadać wiele adresów dostawy oraz historię zamówień przechowywaną w systemie.

W procesie realizacji zamówienia udział bierze kurier, który jest odpowiedzialny za odbiór zamówienia z restauracji/sklepu oraz dostarczenie go do klienta. Każdy kurier posiada swoją kartotekę zawierającą dane identyfikacyjne, kontaktowe oraz informacje o statusie dostępności. Kurierowi przypisywane są zamówienia wraz z informacją o czasie odbioru oraz dostawy.

System umożliwia przypisanie zamówień do kurierów na podstawie ich dostępności oraz lokalizacji. Rejestrowane są czasy odbioru zamówienia z restauracji/sklepu oraz dostarczenia do klienta, co pozwala na analizę efektywności dostaw.

W przypadku pierwszego zamówienia klienta wymagane jest utworzenie konta w systemie poprzez wprowadzenie danych osobowych oraz adresowych. Natomiast w przypadku dodania nowej restauracji/sklepu, administrator systemu wprowadza jego dane oraz ofertę produktową.

System umożliwia również obsługę płatności za zamówienia, zarówno online, jak i przy odbiorze. Rejestrowane są informacje o płatności, takie jak kwota, metoda płatności oraz status transakcji.

## Diagram klas
<img width="2558" height="1247" alt="image" src="https://github.com/user-attachments/assets/cfbaed1b-a8cc-4f51-89ab-ff0b21197ec8" />

### Komentarz
Centralnym pojęciem w rozpatrywanej dziedzinie jest zamówienie. Każde zamówienie jest składane przez konkretnego klienta, zatem w przypadku częstego korzystania z systemu, przypisana do klienta historia może zawierać wiele zamówień (relacja jeden-do-wielu od strony klienta do zamówienia). Zamówienie jest zawsze skierowane do konkretnej restauracji lub sklepu (dostawcy), przy czym dany dostawca realizuje w systemie wiele zamówień (relacja jeden-do-wielu od restauracji do zamówienia).

W ramach zamówienia rejestrowane są wybrane przez klienta produkty, co tworzy listę pozycji zamówienia (relacja jeden-do-wielu od zamówienia do pozycji zamówienia). Każda taka pozycja precyzuje wybrany produkt, jego zamawianą ilość, zamrożoną cenę oraz ewentualne uwagi (relacja jeden-do-wielu od produktu do pozycji). Produkty są przypisane do kartoteki konkretnej restauracji lub sklepu, która oferuje ich wiele (relacja jeden-do-wielu od restauracji do produktu). W przypadku restauracji produkt może dodatkowo posiadać zdefiniowane warianty (relacja jeden-do-wielu od produktu do wariantu).

Użytkownikiem zamawiającym jest klient, który posiada w systemie własną kartotekę z danymi osobowymi i kontaktowymi. Klient musi posiadać utworzone konto dostępowe (relacja jeden-do-jednego między kontem a klientem). W systemie klient może zdefiniować więcej niż jeden adres dostawy (relacja jeden-do-wielu od klienta do adresu), natomiast każde konkretne zamówienie jest powiązane z dokładnie jednym, wybranym adresem (relacja jeden-do-wielu od adresu do zamówienia).

Za fizyczną realizację procesu dostarczenia odpowiada kurier. Kurier posiada własną kartotekę określającą jego dostępność i dane kontaktowe. Może on mieć przypisanych wiele zamówień do doręczenia w ramach swojej historii pracy (relacja jeden-do-wielu od kuriera do zamówienia), przy czym konkretne zamówienie może być w danej chwili obsługiwane najwyżej przez jednego kuriera, który jest do niego przypisywany na podstawie lokalizacji (relacja zero-lub-jeden-do-wielu, gdyż nowo złożone zamówienie może jeszcze nie mieć przypisanego kuriera).

Dodatkowo, proces finalizuje płatność. Każde zamówienie posiada powiązaną ze sobą transakcję płatniczą, określającą kwotę, status oraz metodę – online lub przy odbiorze (relacja jeden-do-jednego pomiędzy zamówieniem a płatnością). Zarządzaniem całokształtem danych, w tym dodawaniem nowych kartotek restauracji i sklepów oraz ich asortymentu, zajmuje się przypisany administrator systemu.

## Diagram przypadków użycia
<img width="493" height="1131" alt="image" src="https://github.com/user-attachments/assets/faa830de-c21f-45af-a901-c808b99d7d25" />

## Model projektowy w stylu Domain-Driven Design

### Kontekst Zamówienie
<img width="1308" height="730" alt="zamówienie" src="https://github.com/user-attachments/assets/2e49ff10-275d-4b89-bfac-034fb6736537" />
<img width="450" height="369" alt="Diagram Przypadków Użycia - Zamówienia" src="https://github.com/user-attachments/assets/7939c932-290a-4dfc-9f9d-ea7ce405f3eb" />

### Kontekst Dostarczanie
<img width="1195" height="439" alt="dostarczanie" src="https://github.com/user-attachments/assets/f6a5be9b-403c-4b0e-837d-891f209f6271" />
<img width="483" height="563" alt="Diagram Przypadków Użycia - Dostarczanie" src="https://github.com/user-attachments/assets/74fb66dc-e147-45d7-8506-ee364c38f890" />

### Kontekst Autentykacja
<img width="762" height="340" alt="autentykacja" src="https://github.com/user-attachments/assets/63b589de-13ba-433f-9d9a-f52fa12bb9a8" />
<img width="616" height="374" alt="Diagram Przypadków Użycia - Autentykacja" src="https://github.com/user-attachments/assets/e5dca8f6-e8c7-47ef-8f69-a8e25e4c4a24" />

### Kontekst Marketing
<img width="719" height="407" alt="marketing" src="https://github.com/user-attachments/assets/dba70d17-ceab-4feb-b0c0-75cace50bf6f" />
<img width="515" height="293" alt="Diagram Przypadków Użycia - Marketing" src="https://github.com/user-attachments/assets/5f511755-11dd-439e-8162-39485eace9a1" />

### Kontekst Reklamacja
<img width="916" height="454" alt="reklamacja" src="https://github.com/user-attachments/assets/3f533d9d-342d-45c0-ab53-b81e55c4d003" />
<img width="510" height="371" alt="Diagram Przypadków Użycia - Reklamacje" src="https://github.com/user-attachments/assets/1c14bb23-ee55-449a-b141-171883679f0f" />

### Kontekst Restauracje
<img width="804" height="423" alt="restauracje" src="https://github.com/user-attachments/assets/2fb57865-b045-4636-bf29-c8d8ee23738a" />
<img width="495" height="376" alt="Diagram Przypadków Użycia - Restauracje" src="https://github.com/user-attachments/assets/03e5d212-9376-4642-b14b-49f7b31b31e0" />

### Kontekst Oferta
<img width="900" alt="oferta" src="(https://github.com/uswb-projekt/megabyte/blob/main/docs/ERD/konteksty/oferta.png?raw=true)" />
<img width="500" alt="Diagram Przypadków Użycia - Oferta" src="LINK_DO_DIAGRAMU_UC" />
