package com.miqueias.r.notificacao_servico.service.external;

import com.miqueias.r.notificacao_servico.domain.dto.MessagemDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "flaskClient", url = "http://localhost:5000")
public interface CameraProcessamentoClient {
    @PostMapping("/imagem-processada")
    void sendMessageToFlask(@RequestBody MessagemDTO messagemDTO);
}
