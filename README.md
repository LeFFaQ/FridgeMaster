# Fridge Master
Мобильный холодильник в вашем кармане!

## Об приложении
Удобное приложение для контроля содержимого вашего холодильника. Трекер срока годности, рекомендация рецептов и многое другое. Созданное в рамках индивидуального проекта для IT-школы Samsung.

## Используемый стек
- Чистая MVVM архитектура
- Jetpack Compose
- Kotlin Flow
- Retrofit + OkHttp
- Room
- Dagger Hilt

## Дорожная карта
Текущее состояние: Немного все-таки работает :)

- [x] Интеграция с Роскачеством
- [x] Интеграция с Spoonacular API
- [ ] Многомодульность
- [x] DI
- [ ] Unit-/UI- Тесты
- [x] Презентация проекта (в том числе этот README)

Также можно посмотреть [тут](https://github.com/users/LeFFaQ/projects/1).

## Скриншоты

Отсутствуют.

## Сборка приложения

В local.properties добавить строки:

```
# Создайте ключ Spoonacular API (два разных - не обязательно)
release.spoon.apikey=...
debug.spoon.apikey=...
```

