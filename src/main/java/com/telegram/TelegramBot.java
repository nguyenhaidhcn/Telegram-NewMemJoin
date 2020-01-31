package com.telegram;

import org.springframework.stereotype.Component;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.User;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicReference;


@Component
public class TelegramBot extends TelegramLongPollingBot  {

    public String botname;

    public String token;

    public String msg;


    @Override
    public void onUpdateReceived(Update update) {

        // We check if the update has a message and the message has text

//        System.out.println("Msg:" + update.getMessage().getText());
//
//        UserDB userDB1 = new UserDB();
//        userDB1.user_name = "@test";
//        Save(userDB1);


        if (update.getMessage() != null)
        {
           List<User> users = update.getMessage().getNewChatMembers();
            long chat_id = update.getMessage().getChatId();

            if(users != null && users.size() > 0)
           {
               System.out.println("New Member:" + users.toString());
               for(User user:users)
               {

                   //send msg

                   String text = "Chào mừng @%s " + msg;

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

           if(update.getMessage().hasText())
           {
               cmd(update);
           }
        }
    }

    private void cmd(Update update)
    {

        if(update.getMessage().getFrom().getBot() == true)
            return;

            ConcurrentHashMap<String, String> stringStringConcurrentHashMap = new ConcurrentHashMap<>();
//        website:https://snapbots.io
//        whitepaper:https://snapbots.io/docs/Whitepaper-Snapbots.pdf
//        bitcointalk:https://bitcointalk.org/index.php?topic=4248868
//        twitter:https://twitter.com/SnapBotsIO
//        reddit:https://www.reddit.com/r/SnapBotsIO
//        medium:https://medium.com/SnapBots
//        facebook:https://www.facebook.com/SnapBots
//        instagram:https://www.instagram.com/SnapBots
//        linkedin:https://www.linkedin.com/company/snapbots


        stringStringConcurrentHashMap.put("website", "https://snapbots.io");
        stringStringConcurrentHashMap.put("whitepaper", "https://snapbots.io/docs/Whitepaper-Snapbots.pdf");
        stringStringConcurrentHashMap.put("bitcointalk", "https://bitcointalk.org/index.php?topic=4248868");
        stringStringConcurrentHashMap.put("twitter", "https://twitter.com/SnapBotsIO");
        stringStringConcurrentHashMap.put("reddit", "https://www.reddit.com/r/SnapBotsIO");
        stringStringConcurrentHashMap.put("medium", "https://medium.com/SnapBots");
        stringStringConcurrentHashMap.put("facebook", "https://www.facebook.com/SnapBots");
        stringStringConcurrentHashMap.put("instagram", "https://www.instagram.com/SnapBots");
        stringStringConcurrentHashMap.put("linkedin", "https://www.linkedin.com/company/snapbots");

        stringStringConcurrentHashMap.forEach((s, s2) -> {
            if(update.getMessage().getText().contains(s))
            {
                SendMessage message_ugrent = new SendMessage() // Create a message object object
                        .setChatId(update.getMessage().getChatId())
                        .setText(s2).setReplyToMessageId(update.getMessage().getMessageId());

//                SendMessage message_ugrent = new SendMessage() // Create a message object object
//                        .setChatId(update.getMessage().getChatId())
//                        .setText(s2);
                try {
                    execute(message_ugrent); // Sending our message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        });

//
//        SendMessage message_ugrent = new SendMessage() // Create a message object object
//                .setChatId(update.getMessage().getChatId())
//                .setText(text.get()).setReplyToMessageId(update.getMessage().getMessageId());
//        try {
//            execute(message_ugrent); // Sending our message object to user
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
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
