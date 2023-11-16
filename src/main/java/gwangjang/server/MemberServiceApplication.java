package gwangjang.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.web.bind.annotation.CrossOrigin;

@EnableFeignClients
@SpringBootApplication
@EnableDiscoveryClient
@EnableJpaAuditing
public class MemberServiceApplication {

    public static void main(String[] args) {

        SpringApplication.run(MemberServiceApplication.class, args);
    }

}
