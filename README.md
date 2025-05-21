# SI_2025_lab2_213152
## Христијан Богданоски, бр. на индекс 213152
# Фотографија од CFG
![CFG-softversko drawio (1)](https://github.com/user-attachments/assets/4bc7de85-1b0b-4331-a07e-53ab63d8c971)

# Цикломатска комплексност

Начин 1 - Користење на код
Со анализа на изворниот код и броење на сите точки на одлучување, како што се if наредбите и логичките оператори (&&, ||), ја пресметавме цикломатската сложеност користејќи ја формулата:
Цикломатска сложеност=Број на одлуки + 1
CC=P+1. Во случајoв P=8, па цикломатската комплексност изнесува 9.

if (item == null || item.getName() == null) → 1 точка на одлучување со 1 || → се брои за 2 точки на одлучување

if (item.getPrice() <= 0) → 1 точка на одлучување

if (item.getBarcode() == null || !item.getBarcode().matches("[0-9]+")) → 1 точка на одлучување со 1 || → се брои за 2 точки на одлучување

if (!allBarcodes.contains(item.getBarcode())) → 1 точка на одлучување

if (item.getName().startsWith("0") && item.getDiscount() > 0) → 1 точка на одлучување со 1 && → се брои за 2 точки на одлучување

При што бројот на одлуки е 8, од тука CC = 8 + 1 = 9.

Начин 2 - Користење на Control Flow Graph

Кога се користи График на Контролен Тек (CFG), цикломатската комплексност се пресметува со формулата:
Цикломатска Сложеност = 𝐸 − 𝑁 + 2𝑃
Каде што:
E = Број на рабови
N = Број на јазли
P = Број на поврзани компоненти 
Од графот гледаме дека имаме 22 јазли и 29 рабови
од тука по формулата CC = 29 - 22 + 2 = 9

# Every Statement Condition

|  # | Тест                                              |  Резултат                 | 
| -: | ------------------------------------------------- | ------------------------- | 
|  1 | `List == null`                                    | `RuntimeException`        | 
|  2 | Invalid item name (`null` or empty)               | `RuntimeException`        | 
|  3 | Discounted item (`Price > 300` & `Quantity > 10`) | `-30 + sum with discount` | 
|  4 | Item without discount                             | `Price * Quantity`        | 
|  5 | Invalid card number                               | `RuntimeException`        | 
|  6 | Invalid character in card number                  | `RuntimeException`        | 

За да се постигне критериумот Every Statement, потребни се најмалку 4 до 5 тест случаи, кои заедно ќе ги извршат сите изјави (statements) во методата checkCart.
# Multiple Condition Coverage

| Цена > 300 | Попуст > 0 | Количина > 10 | Резултат                       |
| ---------- | ---------- | ------------- | ------------------------------ |  
| false      | true       | false         |   Влегува                      |  
| false      | false      | true          | 	Влегува                      |
| false      | true       | true          | 	Влегува                      |  
| false      | false      | false         | 	НЕ влегува во if             |  
| true       | false      | false         | 	Влегува                      |  
| true       | false      | true          | 	Влегува                      |  
| true       | true       | false         | 	Влегува                      |  
| true       | true       | true          | 	Влегува                      |

За Multiple Condition критериумот, мора да се тестираат сите можни комбинации од вистинито/невистинито (true/false) за тие три услови.
Бројот на можни комбинации е 8.


# Објаснување на тестовите
## Every Statement Criterion

test_NullItemList_ThrowsException
Го активира if (allItems == null) → фрла исклучок. Овој тест проверува дали функцијата правилно фрла грешка ако allItems е null.

test_InvalidItemName_ThrowsException
Го активира if (item.getName() == null || item.getName().length() == 0) → фрла исклучок.
Овој тест покрива случај кога има невалидно име (празен стринг).

test_DiscountedItem_TriggersPenalty
Активира if (price > 300 || discount > 0 || quantity > 10) → вистинито → -30.
Ги активира и изразите за калкулација со попуст.
Овозможува покриеност на наредбите во гранките со попуст и казна.

test_ItemWithoutDiscount
Покрива гранка каде попуст е 0 → калкулација без попуст.
Ги активира само позитивните патеки (без казна).

test_InvalidCardNumber_Length
Активира else во if (cardNumber != null && cardNumber.length() == 16) → фрла грешка.

test_InvalidCardNumber_Character
Активира внатрешната for-проверка на дозволени карактери → фрла грешка.

## Multiple Condition Criterion
Секој тест ги користи валидни влезни податоци.
Проверува дали се применува попуст и казна според очекуваната логика.
Ја тестира точноста на изразот if (condition1 || condition2 || condition3) со точно една комбинација од логички вредности.
