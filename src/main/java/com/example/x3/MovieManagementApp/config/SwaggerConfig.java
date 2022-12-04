package com.example.x3.MovieManagementApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;
import java.util.Collections;

@Configuration
public class SwaggerConfig {

    private ApiInfo apiInfo(){
        return new ApiInfo(
                "Movie Management App REST APIs",
                "Movie Management App REST API Documentation",
                "1",
                "",
                new Contact("Nataliia B., Ca'Nese C., Chris L., Felix P.", "https://github.com/canesecurvin/MovieManagementApp", ""),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                Collections.emptyList()
        );
    }

    @Bean
    public Docket apiDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.x3.MovieManagementApp"))
                .paths(PathSelectors.any())
                .build();
    }

}
