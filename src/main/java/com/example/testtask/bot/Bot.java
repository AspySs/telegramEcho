package com.example.testtask.bot;

import bd.Utils;
import com.example.testtask.entity.User;
import com.example.testtask.service.SettingsService;
import com.example.testtask.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String BOT_TOKEN;
    @Value("${bot.name}")
    private String BOT_NAME;
    private Long delay;

    @Autowired
    UserService usrService;
    @Autowired
    SettingsService stngsService;

    ArrayBlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(500);

    private final ExecutorService executor = new ThreadPoolExecutor(8,16,10,
            TimeUnit.SECONDS, tasks);

    public Bot(){}

    @Override
    public String getBotUsername() {
        return BOT_NAME;
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }

    @Override
    public void onUpdateReceived(Update update) {
        delay = stngsService.findSettingsByName("delay").getDelay();
        if(update.hasMessage() && update.getMessage().hasText()){
            String username = update.getMessage().getFrom().getUserName();
            String txt = update.getMessage().getText();
            if(usrService.userIsExist(username)){
                usrService.updateMessageByUsername(txt, username);
            }else{
                usrService.add(new User(username, 1L, txt));
            }
            executor.submit(new MessageConsumer(update, this, delay));
        }
    }
}
