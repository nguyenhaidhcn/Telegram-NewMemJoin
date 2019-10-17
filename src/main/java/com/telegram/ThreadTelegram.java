//package com.telegram;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.telegram.telegrambots.ApiContextInitializer;
//
//import org.telegram.telegrambots.meta.TelegramBotsApi;
//
//@Component
//public class ThreadTelegram  implements Runnable{
//
//
//
//    public TelegramBotsApi botsApi;
//    public TelegramBot telegramBot;
//    @Override
//    public void run() {
////        ApiContextInitializer.init();
//
////        // Instantiate Telegram Bots API
////        TelegramBotsApi botsApi = new TelegramBotsApi();
////
////        // Register our bot
//        try {
//
//
//            botsApi.registerBot(telegramBot);
//
//            Thread.sleep(7);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Autowired
//    public ThreadTelegram() {
//    }
//}
