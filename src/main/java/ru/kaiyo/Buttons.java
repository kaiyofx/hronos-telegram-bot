package ru.kaiyo;

import lombok.Getter;

@Getter
public enum Buttons {

    TERMS("Пояснить термины"),
    VISUAL("Визуальный анализ"),
    SEARCH_ARTICLES("Поиск статей"),
    SEARCH_TRENDS("Тренды по научным тематикам"),

    // visual
    COUNT_WATER("Кол-во воды (%)"),
    FAMOUS_ARTICLE("Популярность статьи"),
    BACK("Вернуться");

    private final String name;

    Buttons(String name) {
        this.name = name;
    }
}
