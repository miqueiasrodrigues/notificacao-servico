package com.miqueias.r.notificacao_servico;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;

@SpringBootApplication
@EnableFeignClients
public class NotificacaoServicoApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotificacaoServicoApplication.class, args);
	}

}
