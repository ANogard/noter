# Noter
Тестовое задание для интенсива Backend/WEB Simbirsoft

Приложение для сохранения приватных заметок

Для развёртывания достаточно в корне выполнить
"docker-compose up".

Приложение хранит пользователей и записи в Postgres.
Условия выполнения соблюдены.

На текущий момент есть следующие недоработки:
1) Пароли пользователей не кодируются
2) Не осуществляется проверка на пустую строку пароля
3) Не осуществляется проверка имени пользовтеля на соответсвие шаблону email по regex

Из возможных доработок:
1) Добавление админ-панели (и, соответственно, роли)
2) Изменение шаблонизатора
3) Сохранение сессии