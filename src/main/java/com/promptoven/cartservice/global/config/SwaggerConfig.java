package com.promptoven.cartservice.global.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    //	@Bean
    //	public OpenAPI openAPI() {
    //		return new OpenAPI()
    //			.info(new Info()
    //				.title("API Test")
    //				.description("product client docs")
    //				.version("1.0.0"))
    //			.servers(List.of(new Server().url("/")));
    //	}
    @Bean
    public OpenAPI customOpenApi() {

        // Security Secheme명
        String jwtSchemeName = "jwtAuth";

        // API
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);

        // SecuritySchemes 설정
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER) //
                        .name("Authorization"));

        return new OpenAPI()
                .info(new Info()
                        .title("API Test")
                        .description("cart service docs")
                        .version("1.0.0"))
                .addSecurityItem(securityRequirement)
                .components(components);
    }
}