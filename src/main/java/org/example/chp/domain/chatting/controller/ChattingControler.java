package org.example.chp.domain.chatting.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(value = "/api/v1/chat")
public interface ChattingControler {
    @PostMapping("/send")
    public String sendMessage(String contents, String userId);

    @PostMapping("/get")
    public String getMessage(String userId);
}
