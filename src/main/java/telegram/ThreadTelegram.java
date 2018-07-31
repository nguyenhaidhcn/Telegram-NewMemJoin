package telegram;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

@Component
public class ThreadTelegram  implements Runnable{

    final
    ShareObject shareObject;


    @Override
    public void run() {
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {

            String token = shareObject.token;
            shareObject.telegramBot = new TelegramBot();


            botsApi.registerBot(shareObject.telegramBot);

            Thread.sleep(7);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    public ThreadTelegram(ShareObject shareObject) {
        this.shareObject = shareObject;
    }
}
