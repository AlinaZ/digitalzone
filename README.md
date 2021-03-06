Задание:
Требуется реализовать REST-сервис по сбору статистики посещаемости WEB-сайта. Сервис должен поддерживать два метода:
1.	Создание события посещения сайта пользователем. Параметры:
a.	Идентификатор пользователя
b.	Идентификатор страницы сайта
Ответ должен содержать (в формате JSON):
a.	Общее количество посещений за текущие сутки
b.	Количество уникальных пользователей за текущие сутки
2.	Получение статистики посещения за произвольный период. Параметр запроса: 
a.	период  учёта 
Ответ должен содержать (в формате JSON):
a.	Общее количество посещений за указанный период
b.	Количество уникальных пользователей за указанный период
c.	Количество постоянных пользователей за указанный период (пользователей, которые за период просмотрели не менее 10 различных страниц).

Сборка и запуск
Перейти в директорию проекта (где pom файл)
mvn compile
mvn spring-boot:run

База данных H2 встроенная, подключение настроено в application.properties, если порт 8080 занят, изменить можно там
В базе содержатся 3 пользователя с id 1 2 3 и 10 страниц с id от 1 до 10

Сервис имеет два реста для POST-запросов

visit/add - добавить посещение сайта сообщением JSON формата:
{
"userid":1,
"pageid":1
}

Приходит ответ формата 
{
    "result": "success",
    "todayVisits": 18,
    "uniqueVisitersToday": 3
}

Сервис записывает текущее время посещения пользователем

visit/stats - получить требуемую статистику за период, для чего необходимо отправить сообщение формата 
{
"firstDate":"2020-07-17",
"secondDate":"2039-07-19"
}

В ответ приходит статистика за запрашиваемый период в формате

{
    "totalVisits": 15,
    "totalUniqueVisits": 3,
    "totalRegularVisitors": 0
}
