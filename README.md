# Simulation 

"Simulation" — это пошаговая симуляция 2D-мира, населенного травоядными и хищниками. 

![image](https://github.com/user-attachments/assets/f4670c5a-3e8b-47c6-838c-7d5b1aea0835)


## Структура симуляции

- 2D-мир населен хищниками, травяодными и растительностью, где хищники охотятся на травоядных, а травоядные поедают траву.
- Все объекты (хищники, травоядные и трава) появляются в случайных точках на карте. 
- Для каждого типа существ реализован алгоритм поиска пути к цели (прим. хищник охотится за травоядным). Алгоритм поиска пути основан на BFS(поиск в ширину), который учитывает различные факторы. Например, травоядные стараются избегать хищников, но при этом их главная цель - найти траву.
- Есть возможность поставить симуляцию мира на "паузу", и возобновить ее после выхода из "паузы".
- Предусмотрено, что при изменении размеров 2D-мира, объекты мира автоматически размещаются в новых пределах, при этом существа не выйдут за границы карты и вся логика симуляции мира останется рабочей.
- Симуляция заканчивается когда в 2D-мире не останется травоядных, т.е. они будут съедены хищниками.



