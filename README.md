# Тестовое задание

---

## Стек технологий

- **Kotlin Multiplatform**
- **Compose Multiplatform**
- **Clean Architecture**
- **MVI**
- **Ktor**
- **SqlDelight**
- **Kodein**

---

## Архитектура проекта

Проект состоит из нескольких основных модулей: `buildSrc`, `common`, `composeApp` и `iosApp`.

- **Модуль `buildSrc`** содержит convention плагины, которые подключаются в другие модули приложения.
- **Модуль `common`** состоит из следующих подмодулей: `core`, `core-database`, `features` и `umbrella`.
    - **Модули `core` и `core-database`** являются корневыми модулями приложения и содержат базовые компоненты, такие как `BaseViewModel` и базовые классы для навигации. Модуль `core-database` содержит базу данных приложения, чтобы избежать её подключения в модули, где она не нужна.
    - **Модуль `features`** включает в себя основные фичи приложения: `:cart`, `:main` и `:shopping-list`. Каждая фича разделена на три модуля: `:data`, `:domain` и `:presentation`. Архитектура презентационного слоя построена по принципу MVI и использует базовую `ViewModel` — `BaseViewModel` (содержится в модуле `core`).
    - **Модуль `umbrella`** служит связующим модулем приложения, где настраивается Dependency Injection (DI) и навигация.

## Скриншоты приложения

<p align="center">
  <img src="https://i.imgur.com/RGJ8ufB.png" alt="Скриншот 1" width="200"/>
  <img src="https://i.imgur.com/w7beHln.png" alt="Скриншот 2" width="200"/>
  <img src="https://i.imgur.com/oT5anXn.png" alt="Скриншот 3" width="200"/>
</p>

---
