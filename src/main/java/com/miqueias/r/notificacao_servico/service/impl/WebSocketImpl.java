package com.miqueias.r.notificacao_servico.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miqueias.r.notificacao_servico.domain.dto.MessagemDTO;
import com.miqueias.r.notificacao_servico.service.WebSocket;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
public class WebSocketImpl extends TextWebSocketHandler implements WebSocket {
    private Set<WebSocketSession> sessions = new CopyOnWriteArraySet<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        sessions.add(session);
    }

    @Override
    public void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String receivedMessage = message.getPayload();
        System.out.println("Recebido do WebSocket do Flask: " + receivedMessage);

        MessagemDTO messagemDTO = objectMapper.readValue(receivedMessage, MessagemDTO.class);

        broadcastMessage(messagemDTO);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }

    private void broadcastMessage(MessagemDTO messagemDTO) throws Exception {
        String messageJson = objectMapper.writeValueAsString(messagemDTO);
        TextMessage message = new TextMessage(messageJson);

        for (WebSocketSession session : sessions) {
            if (session.isOpen()) {
                session.sendMessage(message);
            }
        }
    }
}
