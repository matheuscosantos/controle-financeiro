package com.estudos.controle_financeiro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication()
@EnableCaching
@EnableSwagger2
@EntityScan(basePackageClasses = {ControleFinanceiroApplication.class, Jsr310JpaConverters.class})
public class ControleFinanceiroApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ControleFinanceiroApplication.class, args);
		
	}

}
