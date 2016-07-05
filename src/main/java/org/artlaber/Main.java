package org.artlaber;

import org.artlaber.updateshandlers.impl.HoroskopeHandler;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.bots.AbsSender;

/**
 * Created by artem on 7/4/16.
 */
public class Main {
    public static void main(String[] args) {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
        try {
            telegramBotsApi.registerBot(new HoroskopeHandler());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
