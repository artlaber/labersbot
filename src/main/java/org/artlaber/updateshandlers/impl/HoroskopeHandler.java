package org.artlaber.updateshandlers.impl;

import org.artlaber.Config;
import org.artlaber.services.horo.HoroskopeService;
import org.artlaber.services.horo.impl.HoroskopeServiceImpl;
import org.telegram.telegrambots.TelegramApiException;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.logging.BotLogger;

import javax.validation.constraints.Null;

/**
 * Created by artem on 7/4/16.
 */
public class HoroskopeHandler extends TelegramLongPollingBot {

    private HoroskopeService horoService = new HoroskopeServiceImpl();

    @Override
    public void onUpdateReceived(Update update) {
        try {
            handleRequest(update);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return Config.BOTNAME;
    }

    @Override
    public String getBotToken() {
        return Config.TOKEN;
    }

    public void handleRequest(Update update) {
        Message incomeUpdate = update.getMessage();
        String messageBody = incomeUpdate.getText().toLowerCase();

        if (incomeUpdate != null && incomeUpdate.hasText()) {

            String[] commands = messageBody.split(" ");

            if (commands.length > 0 && Config.SUPPORTED_COMMANDS.contains(commands[0])) {

                SendMessage replyRequest = new SendMessage();
                replyRequest.setChatId(incomeUpdate.getChatId().toString());

                if (commands[0].equals("гороскоп") && commands.length > 1) {

                    try {
                        replyRequest.setText("@" + incomeUpdate.getFrom().getUserName() + "\n" +
                                "" + horoService.getCommonDailyHoro(commands[1]));
                    } catch (NullPointerException e) {
                        replyRequest.setText("Знак не найден ((( \n" +
                                "Используйте команду: \n" +
                                "гороскоп <знак> \n" +
                                "\n" +
                                "где вместо <знак> необходимо подставить желаемый знак зодиака, классическое его название. К примеру: гороскоп козерог");
                    } catch (Exception e) {
                        replyRequest.setText("Знак не найден ((( \n" +
                                "Используйте команду: \n" +
                                "гороскоп <знак> \n" +
                                "\n" +
                                "где вместо <знак> необходимо подставить желаемый знак зодиака, классическое его название. К примеру: гороскоп козерог");
                    }
                } else {
                    replyRequest.setText("Используйте команду: \n" +
                            "гороскоп <знак> \n" +
                            "\n" +
                            "где вместо <знак> необходимо подставить желаемый знак зодиака, классическое его название. К примеру: гороскоп козерог");
                }

                try {
                    sendMessage(replyRequest);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
