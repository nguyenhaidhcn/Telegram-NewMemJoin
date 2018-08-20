package com.telegram;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "user_telegram")
@EntityListeners(AuditingEntityListener.class)
public class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

//    public Integer user_id; ///< Unique identifier for this user or bot
//    public String first_name; ///< User‘s or bot’s first name
    public Boolean is_bot; ///< True, if this user is a bot
//    public String last_name; ///< Optional. User‘s or bot’s last name
    public String user_name; ///< Optional. User‘s or bot’s username
//    public String language_code; ///< Optional. IETF language tag of the user's language

    public UserDB() {
    }


}

