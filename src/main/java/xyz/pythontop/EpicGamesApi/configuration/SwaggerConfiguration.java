package xyz.pythontop.EpicGamesApi.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class SwaggerConfiguration {

    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .info(new Info().title("EpicGames API")
                        .description("Custom EpicGames API for check free games")
                        .version("v0.0.1"));
    }
}