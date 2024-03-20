package org.example.chp.domain.chatting.repository;

import org.example.chp.domain.chatting.domain.Chatting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChattingRepository extends JpaRepository<Chatting, Long> {

}
