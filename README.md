# Bonly 📈

Android-приложение для расчета чистой доходности облигаций к погашению (YTM). Учитывает накопленный купонный доход (НКД), текущую рыночную цену, частоту выплат и налоги (НДФЛ 13%). Проект написан на современном Android-стеке с использованием Jetpack Compose и принципов Clean Architecture.

![Kotlin](https://img.shields.io/badge/Kotlin-1.9.0-blue.svg?logo=kotlin)
![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-Material%203-4CAF50?logo=android)
![Hilt](https://img.shields.io/badge/DI-Dagger%20Hilt-red)
![Architecture](https://img.shields.io/badge/Architecture-MVVM%20%7C%20Clean-orange)

## 📸 Скриншоты

| Светлая тема | Темная тема |
| :---: | :---: |
| <img width="256" height="571" alt="Screenshot_20260918_204704" src="https://github.com/user-attachments/assets/e3df86e3-bca0-45e7-9f89-aac99a2d07d6" /> | <img width="256" height="571" alt="Screenshot_20260918_204848" src="https://github.com/user-attachments/assets/cd665fc6-426a-418e-b9cc-ace062ae7af3"/>|

## 🚀 Основные возможности

* **Точный расчет доходности:** Вычисление годовой доходности к погашению и общей суммы купонных выплат.
* **Учет налогов и НКД:** Автоматический расчет чистой прибыли за вычетом НДФЛ (13%).
* **Интегрированный календарь:** Встроенный `DatePicker` для точного подсчета дней между датой покупки и датой погашения.
* **Удобный ввод:** Защита от неверного ввода и выпадающий список для выбора частоты выплат (1, 2, 4 или 12 раз в год).
* **Material 3:** Полная поддержка системных тем и адаптивного дизайна.

## 🛠 Технологический стек

* **Язык:** Kotlin
* **UI:** Jetpack Compose (Material 3)
* **Архитектура:** MVVM + Clean Architecture (Domain & Presentation слои)
* **Управление состоянием:** UDF (Unidirectional Data Flow) через `StateFlow`
* **Асинхронность:** Kotlin Coroutines
* **Внедрение зависимостей (DI):** Dagger-Hilt

## 🏗 Архитектура

Проект разбит на слои для удобства масштабирования и тестирования:
* **Presentation:** `CalculatorViewModel` отвечает за обработку UI-событий. Состояние экрана упаковано в единый неизменяемый `CalculatorState`.
* **Domain:** Инкапсулирует бизнес-правила и математику в UseCase-классы (например, `CalculateNetYieldUseCase`), делая расчеты независимыми от Android-фреймворка.

## 📦 Запуск проекта

1. Склонируй репозиторий:
   ```bash
   git clone [https://github.com/EgorKanatov/Bonly.git](https://github.com/EgorKanatov/Bonly.git)
