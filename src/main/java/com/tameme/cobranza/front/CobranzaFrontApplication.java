package com.tameme.cobranza.front;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"com.tameme.cobranza.common.controller", "com.tameme"})
@EntityScan({"com.tameme.cobranza.common.entity", "com.tameme.cobranza.common.entity.view"})
@EnableJpaRepositories({"com.tameme.cobranza.common.dao"})
public class CobranzaFrontApplication {

	public static void main(String[] args) {
		SpringApplication.run(CobranzaFrontApplication.class, args);
	}

}
