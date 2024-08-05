package gist.pilldispenser.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url="/", description = "Default Server url")
        },
        info = @Info(title = "알약 프로젝트",
                description = "치매 노인 알약 모니터링 서비스",
                version = "V1")
)
@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .version("v1.0.0")
                        .title("알약 프로젝트 API")
                        .description("치매 노인 알약 모니터링 서비스 API"));
    }
}
