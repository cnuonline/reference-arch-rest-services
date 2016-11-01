package com.arch.api.restful.boot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan({"com.arch.api.restful.boot","com.jcabi"})
@EnableSwagger2
@EnableAspectJAutoProxy
//@ImportResource( { "classpath:logback-spring.xml" } )
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);


    }

    @Bean
    public Docket newsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("restapis")
                .apiInfo(apiInfo()) //.pathMapping(any())
                .select()
                .paths(PathSelectors.ant("/wishes/*"))
               // .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(" API Restful Services and documentation ")
                .description("API Restful Services and documentation ")
                .termsOfServiceUrl("https://cnuonline.github.io/")
                .contact("Srini Kopparapo")
                .license("Apache License Version 2.0")
                .licenseUrl("https://cnuonline.github.io/")
                .version("2.0")
                .build();
    }

}
