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
        System.out.println("Mensagem recebida: Arquivo: " + messagemDTO.arquivo().getOriginalFilename() +
                ", Envolvidos: " + messagemDTO.envolvidos() +
                ", DataHora: " + messagemDTO.dataHora());

        String responseMessage = "Envolvidos: " + messagemDTO.envolvidos() +
                ", Data e Hora: " + messagemDTO.dataHora().toString();

        // Enviar mensagem para todos os clientes conectados
        for (WebSocketSession webSocketSession : sessions) {
            if (webSocketSession.isOpen()) {
                webSocketSession.sendMessage(new TextMessage("Nova mensagem recebida: " + responseMessage));
            }
        }
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        sessions.remove(session);
    }
}
