package com.example.testtask.bot;

import com.example.testtask.entity.User;
import com.example.testtask.service.SettingsService;
import com.example.testtask.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@RequiredArgsConstructor
@Component
public class Bot extends TelegramLongPollingBot {
    @Value("${bot.token}")
    private String BOT_TOKEN;
    @Value("${bot.name}")
    private String BOT_NAME;

    @Autowired
    private final UserService usrService;
    @Autowired
    private final SettingsService stngsService;
    private static final int BLOCKING_QUEUE_CAPACITY = 500;
    private final ArrayBlockingQueue<Runnable> tasks = new ArrayBlockingQueue<>(BLOCKING_QUEUE_CAPACITY);
    private final ExecutorService executor = new ThreadPoolExecutor(
            8,
            16,
            10,
            TimeUnit.SECONDS,
            tasks
    );

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
        Long delay = stngsService.findSettingsByName("delay").getDelay();
        if (update.hasMessage() && update.getMessage().hasText()) {
            String username = update
                    .getMessage()
                    .getFrom()
                    .getUserName();

            String txt = update.getMessage().getText();

            if (usrService.userExists(username)) {
                usrService.updateMessageByUsername(txt, username);
            } else {
                usrService.add(new User(username, 1L, txt));
            }
            executor.submit(new MessageConsumer(update, this, delay));
        }
    }
}
