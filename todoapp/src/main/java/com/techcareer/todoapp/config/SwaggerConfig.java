package com.techcareer.todoapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.service.Parameter;

import java.security.Principal;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket todoApi()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .forCodeGeneration(true)
                .ignoredParameterTypes(Principal.class)
                .globalOperationParameters(globalParameterList())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.techcareer.todoapp"))
                .build();
    }

    private List<Parameter> globalParameterList()
    {
        Parameter authTokenHandler =
                new ParameterBuilder()
                        .name("Authorization")
                        .modelRef(new ModelRef("string"))
                        .required(false)
                        .parameterType("header")
                        .description("Bearer <token>")
                        .build();

        return Collections.singletonList(authTokenHandler);
    }
}
