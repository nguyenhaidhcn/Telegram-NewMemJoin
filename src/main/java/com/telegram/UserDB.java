package com.telegram;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;


@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class UserDB {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Integer user_id; ///< Unique identifier for this user or bot
    public String firstName; ///< User‘s or bot’s first name
    public Boolean isBot; ///< True, if this user is a bot
    public String lastName; ///< Optional. User‘s or bot’s last name
    public String userName; ///< Optional. User‘s or bot’s username
    public String languageCode; ///< Optional. IETF language tag of the user's language

    public UserDB() {
    }

    @Override
    public String toString() {
        return "UserDB{" +
                "user_id=" + user_id +
                ", firstName='" + firstName + '\'' +
                ", isBot=" + isBot +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", languageCode='" + languageCode + '\'' +
                '}';
    }
}

