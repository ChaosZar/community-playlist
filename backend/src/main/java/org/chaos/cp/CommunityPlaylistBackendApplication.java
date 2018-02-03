package org.chaos.cp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class CommunityPlaylistBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommunityPlaylistBackendApplication.class, args);
    }
}
