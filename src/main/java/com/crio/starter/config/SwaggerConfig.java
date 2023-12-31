package com.crio.starter.config;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket memeApi() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.basePackage("com.crio.starter.controller"))
                .paths(PathSelectors.regex("/memes.*")).build().apiInfo(metadata());
    }

    private ApiInfo metadata() {
        return new ApiInfoBuilder().title("Spring Boot Rest Api")
                .description("\"Spring Boot Rest Api for XMEME App\"")
                .contact(new Contact("Blank",
                        "https://www.crio.do/learn/portfolio/rishabhatre/?edit=true",
                        "rishabhatre@gmail.com"))
                .build();

    }
}
