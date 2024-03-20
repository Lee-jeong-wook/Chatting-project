package org.example.chp.domain.chatting.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Chatting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    private String userId;
    private String msg;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
