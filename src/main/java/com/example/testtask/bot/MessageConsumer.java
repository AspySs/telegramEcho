package com.example.testtask.bot;

import bd.Utils;
import com.example.testtask.entity.User;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class MessageConsumer implements Runnable{
    Update update;
    Utils bdUtils = new Utils();
    Bot bot;
    Long delay;
    public MessageConsumer(Update update, Bot bot, Long delay){this.update = update; this.bot = bot; this.delay = delay;}
    @Override
    public void run() {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        SendMessage msg = new SendMessage();
        msg.setChatId(update.getMessage().getChatId().toString());
        User usr = bdUtils.getUser(update.getMessage().getFrom().getUserName());
        //User usr = bot.usrService.findByName(update.getMessage().getFrom().getUserName());
        msg.setText(update.getMessage().getText() + " " + usr.getCounter());
        //bot.usrService.updateCounter(usr.getCounter()+1, usr.getUsername());
        bdUtils.updateCounterByUsername(usr.getUsername(),usr.getCounter()+1);

        try {
            bot.execute(msg);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
