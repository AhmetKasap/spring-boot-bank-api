package com.banking.bank.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
            info = @Info(
                    contact = @Contact(
                            name = "Ahmet",
                            email = "aahmetkasap@gmail.com"
                    ),
                    description = "Open API Docs",
                    title = "Spring Boot Bank API",
                    version = "1.0",
                    license = @License(
                            name="name"
                    )
            ),

            servers = {
                    @Server(
                            description = "LOCAL",
                            url = "http:/localhost:5000/api/v1"
                    ),
                    @Server(
                            description = "PROD",
                            url = "http:/192.168.1.1/api/v1"
                    )
            }
)
public class OpenApiConfig {
}
