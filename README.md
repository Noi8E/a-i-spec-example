# 🕵️‍♂️Репозиторий с REST-API тестами для проверки используемых технологий.

## 👨‍💻Используемый стэк:
| Java | Selenide | Junit5 | Gradle | Selenoid | Jenkins | IntelliJ IDEA | Allure Report | Telegram |
|:------:|:----:|:------:|:------:|:--------:|:--------:|:-------------:|:---------:|:--------:|
| <img src="media/images/JAVA.svg" width="40" height="40"> | <img src="media/images/Selenide.svg" width="40" height="40"> | <img src="media/images/Gradle.svg" width="40" height="40"> | <img src="media/images/Junit5.svg" width="40" height="40"> | <img src="media/images/Selenoid.svg" width="40" height="40"> | <img src="media/images/Jenkins.svg" width="40" height="40"> | <img src="media/images/IDEA.svg" width="40" height="40"> | <img src="media/images/Allure Report.svg" width="40" height="40"> | <img src="media/images/Telegram.svg" width="40" height="40"> |



### 🔎Что проверяем:
* Добавление товаров в корзину без передачи cookie.
* Добавление товаров для существующего юзера (с использованием cookie)
* 

### 🚀Запуск тестов:

```gradle clean test```

### 🗒Сгенерировать отчет по тестам:

```allure serve build/allure results```