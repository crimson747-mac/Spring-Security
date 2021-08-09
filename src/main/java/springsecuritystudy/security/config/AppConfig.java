package springsecuritystudy.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springsecuritystudy.security.repository.AccessIpRepository;
import springsecuritystudy.security.repository.ResourcesRepository;
import springsecuritystudy.security.service.SecurityResourceService;

@Configuration
public class AppConfig {

    @Bean
    public SecurityResourceService securityResourceService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        return new SecurityResourceService(resourcesRepository, accessIpRepository);
    }
}
