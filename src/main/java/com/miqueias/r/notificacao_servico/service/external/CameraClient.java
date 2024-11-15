package com.miqueias.r.notificacao_servico.service.external;

import com.miqueias.r.notificacao_servico.domain.dto.CameraDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "camera-servico", url = "${camera.service.url}")
public interface CameraClient {
    @GetMapping(value = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<CameraDTO> findById(@PathVariable(value = "id") Long id);
}
