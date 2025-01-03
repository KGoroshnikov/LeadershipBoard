# Система управления лидербордом 🎮

Эта система предназначена для управления таблицами лидеров и рейтингами в онлайн-игре. Приложение построено на **Spring Framework** с использованием **MVC-архитектуры** и базы данных **MySQL**. Здесь есть всё: фильтрация, сортировка, профили игроков, административные панели и система авторизации! 🚀

---

## Основные функции 🌟

### Для пользователей:
- Регистрация и вход: надёжная авторизация с разграничением ролей (администратор или пользователь).
- Лидерборд: просмотр таблиц лидеров с удобной фильтрацией и сортировкой по очкам, регионам и статусу подписки.
- Профили игроков: доступ к личной информации, истории игр и визуализации прогресса.

### Для администраторов:
- Управление игроками: добавляйте, редактируйте и удаляйте профили игроков.
- Управление результатами игр: записывайте результаты матчей.
- Админ-панель: централизованное управление системой.

### API Возможности:
- CRUD-операции для игроков, результатов игр и таблиц лидеров.
- Эндпоинты для фильтрации, сортировки и получения данных.

---

## Технологии 🛠️

- **Бэкенд**: Java, Spring Framework (Spring Boot, Spring Data, Spring Security).
- **База данных**: MySQL.
- **Фронтенд**: Thymeleaf-шаблоны, HTML, CSS.
- **Сборка**: Maven.
- **Тестирование**: JUnit, MockMvc.

---

## Как установить? 🤔

1. Клонируйте репозиторий:
   ```bash
   git clone https://github.com/KGoroshnikov/leadership-board.git
   cd leadership-board
   ```

2. Настройте базу данных:
   - Создайте базу MySQL с именем `leaderboard`.
   - Укажите данные доступа в файле `application.properties`.

3. Скомпилируйте и запустите приложение:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

4. Перейдите в браузере по адресу `http://localhost:8080`. Готово! 🎉
