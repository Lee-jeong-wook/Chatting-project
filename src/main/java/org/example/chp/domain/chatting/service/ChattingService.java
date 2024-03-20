package org.example.chp.domain.chatting.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.chp.domain.chatting.domain.Chatting;
import org.example.chp.domain.chatting.repository.ChattingRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChattingService {
    private final ChattingRepository chattingRepository;
    @Transactional
    public Chatting saveChat(Chatting chatting) {
        return chattingRepository.save(chatting);
    }
}
