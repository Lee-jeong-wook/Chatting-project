package org.example.chp.domain.socket;

import org.example.chp.domain.chatting.domain.Chatting;
import org.example.chp.domain.chatting.service.ChattingService;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WebSocketHandler extends TextWebSocketHandler {
    private static final ConcurrentHashMap<String, WebSocketSession> CLIENTS = new ConcurrentHashMap<String, WebSocketSession>();
    private final ChattingService chattingService;

    public WebSocketHandler(ChattingService chattingService) {
        this.chattingService = chattingService;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        CLIENTS.put(session.getId(), session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        CLIENTS.remove(session.getId());
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        Chatting chatting = new Chatting();
        chatting.setUserId(session.getId()); // 세션 ID를 사용하여 사용자 식별
        chatting.setMsg(message.getPayload());
        chattingService.saveChat(chatting);
        String id = session.getId();
        CLIENTS.entrySet().forEach( arg->{
            if(!arg.getKey().equals(id)) {
                try {
                    arg.getValue().sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
