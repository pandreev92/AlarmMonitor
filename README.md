# AlarmMonitor
Требования:

1.Приложение должно отображать список аварийных событий телекоммуникационного оборудования (далее Аварии), которые случайным образом появляются и отменяются. Атрибуты объекта Авария: ID (Уникальный идентификатор - число), Alarm Number (Номер Аварии - число), Alarm Text (Описание аварии - текст), Alarm Time (Время возникновения Аварии), Cancel Time (Время отмены Аварии), Acknowledge Time (Время обработки Аварии оператором).

2.Список аварий необходимо отобразить в виде таблицы с перечисленными выше колонками. Аварии должны быть отсортированы в порядке появления (Alarm Time)

3.Аварии должны появляться случайным образом с заполненными атрибутами по желанию. Вновь появившаяся Авария должна иметь обязательно пустые значения (null) для атрибутов: Cancel Time, Acknowledge Time

4.Также Аварии должны отменяться случайным образом. Отмена Аварии значит, что она более не активна и атрибут Cancel Time имеет конкретное значение.

5.В таблице должны отображаться все активные Аварии и отмененные за последний час. Не попадающие под данные критерии Аварии можно удалить.

6.В среднем должно быть от 10 до 40 активных Аварий. Время жизни Аварий в среднем от 1 до 10 мин.

7.У оператора должна быть возможность обработать Аварию, проставив значение Acknowledge Time.

8.Интерфейс предлагаю реализовать на Swing
