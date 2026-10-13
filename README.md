# java_for_test — автотесты на Java

Учебные проекты курса «Программирование на Java для тестировщиков» (Software-Testing.Ru, 2025).

| Каталог | Что внутри |
|---|---|
| [`addressbook_web_tests`](addressbook_web_tests) | UI-автотесты веб-приложения addressbook: Selenium WebDriver, JUnit 5, Allure, проверки через БД (JDBC, Hibernate) |
| [`mantis_tests`](mantis_tests) | тесты баг-трекера MantisBT: UI, HTTP, REST API, SOAP, регистрация пользователя с подтверждением по почте |
| [`sandbox`](sandbox) | первые упражнения: классы на Java и модульные тесты на JUnit 5 |

## addressbook_web_tests

**Стек:** Java, Gradle, Selenium WebDriver, JUnit 5, Allure, Hibernate, JDBC (MySQL), Jackson, JCommander.

**Что проверяется:** создание, изменение и удаление групп и контактов; добавление контакта в группу и удаление из неё; совпадение телефонов, адресов и email на главной странице с данными в БД.

**Как устроено:**
- `ApplicationManager` и хелперы (`LoginHelper`, `GroupHelper`, `ContactHelper`) отделяют работу со страницами от тестов.
- Состояние «до» и «после» читается из БД через Hibernate, ожидаемый результат сравнивается с фактическим.
- После каждого теста SQL-запрос через JDBC проверяет целостность связей «контакт — группа».
- Параметризованные тесты (`@ParameterizedTest`, `@MethodSource`): позитивные и негативные наборы, случайные данные.
- Генератор тестовых данных (`generator.Generator`, параметры JCommander) создаёт группы и контакты в JSON, YAML или XML.
- Шаги в отчёте Allure (`Allure.step`).
- Браузер Chrome или Edge, локально или через Selenium Server (`RemoteWebDriver`, параметр `seleniumServer` в `ci.properties`).
- Прогон в Jenkins: Git → `gradlew clean test` → публикация JUnit-отчёта. Скриншоты настройки джоба и отчёта Allure — в [`src/test/java/screenshots`](addressbook_web_tests/src/test/java/screenshots).

**Запуск** (нужен развёрнутый addressbook, настройки — в `local.properties`):

```bash
cd addressbook_web_tests
./gradlew test                                        # Chrome, local.properties
./gradlew test -Pbrowser=edge -Ptarget=ci.properties  # Edge через Selenium Server
allure serve allure-results                           # отчёт Allure (нужен Allure CLI)
```

## mantis_tests

**Стек:** Java, Gradle, JUnit 5, Selenium WebDriver, OkHttp, Swagger Codegen, SOAP-клиент MantisConnect, Jakarta Mail, Apache James.

**Что проверяется:**
- вход в MantisBT HTTP-запросами, без браузера;
- регистрация пользователя от начала до конца: почтовый ящик в Apache James (через CLI или REST API) → регистрация в UI → письмо с подтверждением (POP3) → переход по ссылке из письма → вход новым пользователем;
- создание пользователя администратором с временным ящиком на developermail.com;
- создание задачи через SOAP API; клиент REST API сгенерирован из `swagger.json`.

**Запуск** (нужны локальные MantisBT и Apache James, настройки — в `local.properties`):

```bash
cd mantis_tests
./gradlew test
```

## sandbox

Классы геометрических фигур и тесты к ним на JUnit 5, упражнения с коллекциями.
