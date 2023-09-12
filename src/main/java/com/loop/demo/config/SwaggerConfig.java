package com.loop.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.List;

/**
 * ip地址:端口号/swagger-ui.html
 * @author Administrator
 */
@Configuration
@EnableWebMvc
@EnableSwagger2// 该注解开启Swagger2的自动配置
public class SwaggerConfig {


    //配置了swagger的Docket的bean实例
    @Bean
    public Docket createDocketApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getApiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.loop.demo.controller"))
                .paths(PathSelectors.any())
                .build()  //设置API分组
                .securitySchemes(Collections.singletonList(apiKey()))
                .securityContexts(Collections.singletonList(securityContext()))
                .groupName("对外接口文档")
                //是否开启swagger 如果是false，浏览器将无法访问，默认是true
                .enable(true)
                ;
    }


    private ApiInfo getApiInfo() {
        return new ApiInfoBuilder().title("SpringBoot开发demo构建的API文档").description("接口文档详情信息").version("1.0")
                .contact(new Contact("zjh","https://mp.csdn.net/","17638419612@163.com"))
                //许可
                .license("")
                //许可链接
                .licenseUrl("https://mp.csdn.net/")
                .build();
    }

    private ApiKey apiKey() {
        return new ApiKey("JWT", "Authorization", "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = {authorizationScope};
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }

}
