# SI_2025_lab2_213152
Христијан Богданоски, бр. на индекс 213152
# Фотографија од CFG
![CFG-softversko drawio (1)](https://github.com/user-attachments/assets/4bc7de85-1b0b-4331-a07e-53ab63d8c971)

# Цикломатска комплексност

Начин 1 - Користење на код
Со анализа на изворниот код и броење на сите точки на одлучување, како што се if наредбите и логичките оператори (&&, ||), ја пресметавме цикломатската сложеност користејќи ја формулата:
Цикломатска сложеност=Број на одлуки + 1
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

# Multiple Condition Coverage

| Цена > 300 | Попуст > 0 | Количина > 10 | Резултат                       |
| ---------- | ---------- | ------------- | ------------------------------ |  
| false      | true       | false         |   Влегува                      |  
| false      | true       | false         | 	Влегува                      |  
| false      | false      | false         | 	НЕ влегува во if             |  
| true       | false      | false         | 	Влегува                      |  
| true       | false      | true          | 	Влегува                      |  
| true       | true       | false         | 	Влегува                      |  
| true       | true       | true          | 	Влегува                      |


