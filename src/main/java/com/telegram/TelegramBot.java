package com.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;


@Component
public class TelegramBot extends TelegramLongPollingBot  {


    @Autowired
    UserRepository userRepository;

    @Override
    public void onUpdateReceived(Update update) {

        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text


//        UserDB userDB1 = new UserDB();
//        userDB1.firstName ="test";
//        userDB1.lastName = "test";
//        userDB1.user_id = 2144;
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

                   String msg = "Hi @%s\n" +
                           "Thank you for expressing interest in our Reefic Protocol project.";
                   msg = String.format(msg, user.getUserName());
                   SendMessage message_ugrent = new SendMessage() // Create a message object object
                           .setChatId(chat_id)
                           .setText(msg);
                   try {
                       execute(message_ugrent); // Sending our message object to user
                   } catch (TelegramApiException e) {
                       e.printStackTrace();
                   }


                   UserDB userDB = new UserDB();
//                   userDB.first_name = user.getFirstName();
//                   userDB.last_name = user.getLastName();
                   userDB.user_name = user.getUserName();
//                   userDB.user_id = user.getId();
                   Save(userDB);


               }

           }
        }
    }




    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @TelegramBot, it must return 'TelegramBot'
        return "ReeficBot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
//        return ShareObjectQuote.token;
        return "695906149:AAHR8UYCLBehY3eSQSjUlKilT2CBqtlRJpM";

    }


    public void Save(UserDB userDB)
    {
        try {

            URL url = new URL("http://localhost:8091/api/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"user_name\":\"%s\"}";;
            input = String.format(input,userDB.user_name);

            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != HttpURLConnection.HTTP_CREATED) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            String output;
            System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                System.out.println(output);
            }

            conn.disconnect();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }


}
