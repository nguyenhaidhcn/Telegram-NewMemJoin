package com.telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;


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

//        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text


        UserDB userDB1 = new UserDB();
        userDB1.firstName ="test";
        userDB1.lastName = "test";
        userDB1.user_id = 2144;
        Save(userDB1);


        if (update.getMessage() != null)
        {
           List<User> users = update.getMessage().getNewChatMembers();
           if(users != null && users.size() > 0)
           {
               System.out.println("New Member:" + users.toString());
               for(User user:users)
               {
                   UserDB userDB = new UserDB();
                   userDB.firstName = user.getFirstName();
                   userDB.lastName = user.getLastName();
                   userDB.user_id = user.getId();
                   Save(userDB);
               }

           }
        }
    }




    @Override
    public String getBotUsername() {
        // Return bot username
        // If bot username is @TelegramBot, it must return 'TelegramBot'
        return "sys_snap_bot";
    }

    @Override
    public String getBotToken() {
        // Return bot token from BotFather
//        return ShareObjectQuote.token;
        return "525968331:AAHjiranH89hLS60L02FMW7wOaWB0gVjiIw";

    }


    public void Save(UserDB userDB)
    {
        try {

            URL url = new URL("http://localhost:8091/api/user");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            String input = "{\"user_id\":%d,\"firstName\":\"%s\", \"lastName\":\"%s\", \"languageCode\":\"%s\"}";;
            input = String.format(input, userDB.user_id, userDB.firstName, userDB.lastName, userDB.languageCode);

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
