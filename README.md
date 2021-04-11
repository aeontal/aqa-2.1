# CardOrder [![Java CI with Gradle](https://github.com/aeontal/aqa-2.1/actions/workflows/gradle.yml/badge.svg)](https://github.com/aeontal/aqa-2.1/actions/workflows/gradle.yml)

### Задача

Ключевой кейс: Вам необходимо автоматизировать тестирование формы заказа карты

Требования к содержимому полей:

1. Поле Фамилия и имя - разрешены только русские буквы, дефисы и пробелы.
2. Поле телефон - только цифры (11 цифр), символ + (на первом месте).
3. Флажок согласия должен быть выставлен.

Тестируемая функциональность: отправка формы.

### Решение

1. Созданы необходимые тесты с использованием Selenium.
2. Созданы необходимые тесты с использованием Selenide.
3. Выявлены следующие [баги](https://github.com/aeontal/aqa-2.1/issues).

### Баги

1. [Форма Заявки на дебетовую карту не принимает букву "Ё" в имени Клиента ](https://github.com/aeontal/aqa-2.1/issues/1)
