package ru.kaiyo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendChatAction;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import ru.kaiyo.utils.Requests;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URI;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bot extends TelegramLongPollingBot {

    private final int PARSE_LIMIT = 1;

    public Bot(String token) {
        super(token);
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasCallbackQuery()) {
            String data = update.getCallbackQuery().getData();
            String chat_id = update.getCallbackQuery().getMessage().getChatId().toString();
            int messageId = update.getCallbackQuery().getMessage().getMessageId();
            if(data.equalsIgnoreCase("terms")) {
                execute(new SendMessage(chat_id, "здесь типо код"));
            } else if(data.equalsIgnoreCase("visual")) {
                execute(EditMessageReplyMarkup.builder()
                        .chatId(chat_id)
                        .replyMarkup(GeneralKeyboard.getVisualKeyboard())
                        .messageId(messageId).build());
            } else if(data.equalsIgnoreCase("water")) {
                String text = update.getMessage().getText();
                BufferedImage image = ImageIO.read(URI.create("http://10.10.2.100:5000/water/" + "hi my ass").toURL());

                ByteArrayOutputStream os = new ByteArrayOutputStream();
                ImageIO.write(image, "png", os);
                InputStream is = new ByteArrayInputStream(os.toByteArray());
                execute(SendPhoto.builder()
                        .photo(new InputFile(is, ""))
                        .chatId(chat_id)
                        .build());
            } else if(data.equalsIgnoreCase("famous")) {
            } else if(data.equalsIgnoreCase("back")) {
                execute(EditMessageReplyMarkup.builder()
                        .chatId(chat_id)
                        .replyMarkup(GeneralKeyboard.getArticleKeyboard())
                        .messageId(messageId).build());
            }
            return;
        }
        String chatId = update.getMessage().getChatId().toString();
        String text = update.getMessage().getText();
        switch (text) {
            case "/start":
                SendMessage message = new SendMessage(chatId, "какой-то интересный текстик");
                message.setReplyMarkup(GeneralKeyboard.getKeyboard());
                execute(message);
                return;
            case "Поиск статей":
                execute(new SendMessage(chatId, "ответ на запрос1"));
                return;
            case "Тренды по научным тематикам":
                execute(new SendMessage(chatId, "ответ за запрос2"));
                return;
            default: break;
        }
        try(ExecutorService service = Executors.newSingleThreadExecutor()) {
            service.submit(() -> {
                try {
                    Message message = execute(SendMessage.builder().text("Хронос обрабатывает ваш запрос...")
                            .chatId(chatId).build());
                    execute(SendChatAction.builder().chatId(chatId).action("typing").build());

                    String url = "http://127.0.0.1:10000/standard";
                    JsonObject object = new JsonObject();
                    object.addProperty("text", text);

                    String answer = new Requests().post(url, object).getAnswer();
                    execute(EditMessageText.builder().text(answer)
                            .messageId(message.getMessageId())
                            .chatId(message.getChatId())
                            .replyMarkup(GeneralKeyboard.getArticleKeyboard())
                            .build());
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            });
        }

    }

    @Override
    public String getBotUsername() {
        return "Biocad";
    }
}
