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

    private void handleRequest(Update update) {
        Message incomeUpdate = update.getMessage();
        String messageBody = incomeUpdate.getText().toLowerCase();

        if (incomeUpdate != null && incomeUpdate.hasText()) {

            String [] commands = messageBody.split(" ");

            if (commands.length > 0 && Config.SUPPORTED_COMMANDS.contains(commands[0])) {

                SendMessage replyRequest = new SendMessage();
                replyRequest.setChatId(incomeUpdate.getChatId().toString());

                if (commands[0].equals("гороскоп") && commands.length > 1) {

                    try {
                        if (commands.length < 3) {
                            replyRequest.setText("@" + incomeUpdate.getFrom().getUserName() + "\n" +
                                    "" + horoService.getCommonDailyHoro(commands[1], 0));
                        }
                        else {
                            replyRequest.setText("@" + incomeUpdate.getFrom().getUserName() + "\n" +
                                    "" + horoService.getCommonDailyHoro(commands[1], Integer.valueOf(commands[2])));
                        }
                    } catch (NullPointerException e) {
                        replyRequest.setText("Знак не найден (((");
                    } catch (Exception e) {
                        replyRequest.setText("Something went wrong: " + e.getMessage());
                    }
                }
                else {
                    replyRequest.setText("Введите знак!\n" +
                            "Поддерживаемые команды:\n" +
                            "1. гороскоп знак 1 - общий (или просто гороскоп знак)\n" +
                            "2. гороскоп знак 2 - любовний\n" +
                            "3. гороскоп знак 3 - семейный\n" +
                            "4. гороскоп знак 4 - флирт\n");
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
