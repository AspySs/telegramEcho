# telegramEcho

Бот и веб управление к нему написаны на Java, был использован Spring и telegrambots-spring-boot-starter
В качестве БД был выбран PostgreSQL
исполняемый файл -> testTask-0.0.1-SNAPSHOT.jar
конфигурация для бота подставляется из файла -> application.yaml
Backup базы данных в файле -> backupBD.sql

Описание решения:

- для работы с БД использовался Hibernate и пара ручных запросов через JBDC
- для обеспечения многопоточности был выбран ThreadPoolExecutor и ArrayBlockingQueue
- при получении сообщения мы сразу передаем задание в пул тасков на выполнение в многопотоке
- после ответа на сообщение увеличиваем счетчик на пользователе
- в бд храним только последнее сообщение пользователя + счетчик + имя пользователя + настройка задержки


инструкция к запуску:
1. поднять бд из бэкапа
2. подключить ее в application.properties
3. изменить конфигурации бота
4. (в случае изменений) собрать Jar файл с помощью команды Maven -> mvn clean package
5. запустить с помощью командной строки -> java -jar <ПУТЬ К Jar ФАЙЛУ>

Тестирование:
- примеры запросов на веб часть приведены в файле -> telegramEcho-endpoints.http
