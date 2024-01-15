package com.xingyun.easypayadmin.config;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2WebMvc
public class SwaggerConfig {


    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("lottery管理后台").description("接口说明")
                .termsOfServiceUrl("http://www.baidu.com")
                // .contact(contact)
                .version("1.0.0").build();
    }

    @Bean
    public Docket app() {

        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> params = new ArrayList<>();
        parameterBuilder.name("token").description("token必须传").required(true)
                .defaultValue("")
                .modelRef(new ModelRef("String")).parameterType("header").required(false).build();
        params.add(parameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(params)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .build()
                .apiInfo(apiInfo())
                .groupName("lottery")
                .forCodeGeneration(true)
                //.extensions(openApiExtensionResolver.buildExtensions("WoWarriors"))
                ;
    }
}