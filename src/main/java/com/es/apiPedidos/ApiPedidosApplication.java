package com.es.apiPedidos;

import com.es.apiPedidos.security.RsaKeyProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaKeyProperties.class)
public class ApiPedidosApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiPedidosApplication.class, args);
	}

}
