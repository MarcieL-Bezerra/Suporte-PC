package br.com.marciel.sosnet;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.marciel.sosnet.controller"))
				.paths(PathSelectors.any())
				.build();

	}

	private ApiInfo apiInfo() {
		return new ApiInfo("SOS API",
				"Api para registros de chamados referentes a suportes de informatica!",
				"1.0.0",
				"https://www.simuladoronline.com/termos-de-uso/",
				new Contact("Marciel Bezerra", "https://leicram.000webhostapp.com/index.html",
						"marciel.bezerra@gmail.com"),
				"Licença de APi",
				"https://www.simuladoronline.com/termos-de-uso/",
				Collections.emptyList());
	}

}
