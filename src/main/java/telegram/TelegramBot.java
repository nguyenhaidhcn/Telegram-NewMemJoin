package telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.User;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.telegram.telegrambots.api.interfaces.BotApiObject;
import org.telegram.telegrambots.api.objects.games.Game;
import org.telegram.telegrambots.api.objects.payments.Invoice;
import org.telegram.telegrambots.api.objects.payments.SuccessfulPayment;
import org.telegram.telegrambots.api.objects.stickers.Sticker;

import java.util.List;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class TelegramBot extends TelegramLongPollingBot  {


    @Autowired
    UserRepository userRepository;

    @Override
    public void onUpdateReceived(Update update) {

//        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text


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
                   userRepository.save(userDB);
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


}
