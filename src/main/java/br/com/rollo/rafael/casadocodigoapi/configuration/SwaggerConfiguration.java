package br.com.rollo.rafael.casadocodigoapi.configuration;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.rollo.rafael.casadocodigoapi.domain.users.User;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private static String SCANNED_PACKAGE = "br.com.rollo.rafael.casadocodigoapi.application";

    Logger logger = LoggerFactory.getLogger(SwaggerConfiguration.class);

    @Bean
    public Docket builder() {	
        logger.info("Creating Swagger docket configuration.");
        
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SwaggerConfiguration.SCANNED_PACKAGE))
                .paths(PathSelectors.ant("/api/**"))
                .build()
                
                .ignoredParameterTypes(User.class)  
                .globalRequestParameters(
                	Arrays.asList(
                		new RequestParameterBuilder()
                			.name("Authorization")
                			.description("Authorization Bearer Token")
                			.in(ParameterType.HEADER)
                			.query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                			.build()));
    }
}
