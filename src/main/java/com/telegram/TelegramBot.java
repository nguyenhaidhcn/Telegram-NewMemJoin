package com.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot  {

    public String botname;

    public String token;

    public String msg;


    @Override
    public void onUpdateReceived(Update update) {

        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text

//        System.out.println("Msg:" + update.getMessage().getText());
//
//        UserDB userDB1 = new UserDB();
//        userDB1.user_name = "@test";
//        Save(userDB1);


        if (update.getMessage() != null)
        {
           List<User> users = update.getMessage().getNewChatMembers();
           if(users != null && users.size() > 0)
           {
               System.out.println("New Member:" + users.toString());
               for(User user:users)
               {

                   //send msg

                   String text = "Hi @%s\n" + msg;

                   if(user.getUserName() != null)
                        text = String.format(text, user.getUserName());
                   else
                       text = String.format(text, user.getFirstName() + user.getLastName());
                   SendMessage message_ugrent = new SendMessage() // Create a message object object
                           .setChatId(chat_id)
                           .setText(text);
                   try {
                       execute(message_ugrent); // Sending our message object to user
                   } catch (TelegramApiException e) {
                       e.printStackTrace();
                   }





               }

           }
        }
    }




    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @TelegramBot, it must return 'TelegramBot'
        return botname;
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
//        return ShareObjectQuote.token;
        return token;

    }





}
