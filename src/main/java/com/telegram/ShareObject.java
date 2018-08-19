package com.telegram;

import org.springframework.stereotype.Component;

@Component
public class ShareObject {

    private final static ShareObject instance = new ShareObject();


    public static String botName;
    public static String token;

    public ShareObject() {};


    public static TelegramBot telegramBot;

//    @Value("${chat.id}")
    public static long chat_id =-295025948;

    public static long chat_id_balance =-237206539;

    public static long chat_id_hedge =-195924043;

}
