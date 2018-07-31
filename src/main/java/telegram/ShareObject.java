package telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ShareObject {

    private final static ShareObject instance = new ShareObject();



    public String token;

    public ShareObject() {};


    public TelegramBot telegramBot;

//    @Value("${chat.id}")
    public long chat_id =-295025948;

    public long chat_id_balance =-237206539;

    public long chat_id_hedge =-195924043;

}
