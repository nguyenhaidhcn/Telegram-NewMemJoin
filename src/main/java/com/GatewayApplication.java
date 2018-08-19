package com;

import com.telegram.ThreadTelegram;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class GatewayApplication {



	private static final Logger log = LoggerFactory.getLogger(GatewayApplication.class);

	public static void main(String[] args) {


//
//		ShareObjectQuote.token =  token;
//		ShareObjectQuote.chat_id =  new Long(chat_id);
		ThreadTelegram threadTelegram = new ThreadTelegram();
		new Thread(threadTelegram).start();
		SpringApplication.run(GatewayApplication.class, args);
	}
}
