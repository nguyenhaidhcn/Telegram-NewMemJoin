package telegram;

import telegram.message.QuoteRequest;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.Date;
import java.util.Map;

@Component
public class TelegramBot extends TelegramLongPollingBot  {


    @Override
    public void onUpdateReceived(Update update) {

        long chat_id = update.getMessage().getChatId();
        // We check if the update has a message and the message has text
        if (update.hasMessage() && update.getMessage().hasText()) {
            // Set variables

            String message_text = update.getMessage().getText();

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
