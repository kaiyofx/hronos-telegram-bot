package ru.kaiyo;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class GeneralKeyboard {

    public static InlineKeyboardMarkup getVisualKeyboard() {
        return InlineKeyboardMarkup.builder()
                .keyboard(List.of(
                        List.of(
                                InlineKeyboardButton.builder()
                                        .text(Buttons.COUNT_WATER.getName())
                                        .callbackData("water")
                                        .build(),
                                InlineKeyboardButton.builder()
                                        .text(Buttons.FAMOUS_ARTICLE.getName())
                                        .callbackData("famous")
                                        .build()
                        ),
                        List.of(InlineKeyboardButton.builder()
                                .text(Buttons.BACK.getName())
                                .callbackData("back")
                                .build())
                )).build();
    }

    public static InlineKeyboardMarkup getArticleKeyboard() {
        return InlineKeyboardMarkup.builder()
                .keyboardRow(List.of(
                        InlineKeyboardButton.builder()
                                .text(Buttons.TERMS.getName())
                                .callbackData("terms")
                                .build(),
                        InlineKeyboardButton.builder()
                                .text(Buttons.VISUAL.getName())
                                .callbackData("visual")
                                .build()
                ))
                .build();
    }

    public static ReplyKeyboardMarkup getKeyboard() {
        KeyboardRow row = new KeyboardRow(List.of(
                new KeyboardButton(Buttons.SEARCH_ARTICLES.getName()),
                new KeyboardButton(Buttons.SEARCH_TRENDS.getName())
        ));
        return ReplyKeyboardMarkup.builder()
                .keyboardRow(row)
                .resizeKeyboard(false)
                .selective(true)
                .build();
    }

}
