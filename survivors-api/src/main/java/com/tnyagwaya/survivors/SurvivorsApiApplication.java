package com.tnyagwaya.survivors;

import com.tnyagwaya.survivors.robot.ConfigProperties;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@OpenAPIDefinition(info = @Info(title = "ROBOT apocalypse", version = "1.0.0",
        description = "RESTful endpoints for storing information about the survivors, as well as the resources they own."),
        servers = {
                @Server(
                        description = "Local Environment",
                        url = "http://localhost:8080/")},
        security = {
                @SecurityRequirement(name = "None")}
)
@SpringBootApplication
@EnableConfigurationProperties(value = {ConfigProperties.class})
public class SurvivorsApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(SurvivorsApiApplication.class, args);
    }
}
