package com.miqueias.r.notificacao_servico.config;

import com.miqueias.r.notificacao_servico.service.impl.WebSocketImpl;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(new WebSocketImpl(), "/ws/notificacao").setAllowedOrigins("*");
    }
}
