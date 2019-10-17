package com;

import com.google.gson.Gson;


//import com.telegram.ThreadTelegram;
import com.telegram.TelegramBot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.logging.BotLogger;
import org.telegram.telegrambots.meta.logging.BotsFileHandler;


import java.io.IOException;
import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@Service
public class TelegramService {

    private static final Logger log = LoggerFactory.getLogger(TelegramService.class);

    private Gson gson = new Gson();


	@Value("${bot.name}")
	private String botName;

	@Value("${bot.token}")
	private String botToken;

	@Value("${bot.msg}")
	private String botMsg;




//	private ThreadTelegram threadTelegram = new ThreadTelegram();
	@PostConstruct
	private void setupBot()
	{

		ApiContextInitializer.init();

        // Register our bot
        try {


			TelegramBotsApi telegramBotsApi = new TelegramBotsApi();
			TelegramBot telegramBot = new TelegramBot();
			telegramBot.token = botToken;
			telegramBot.botname = botName;
			telegramBot.msg = botMsg;
//			threadTelegram.botsApi = botsApi;
//			threadTelegram.telegramBot = telegramBot;

//			new Thread(threadTelegram).start();

			telegramBotsApi.registerBot(telegramBot);

//            Thread.sleep(7);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
	}



}
