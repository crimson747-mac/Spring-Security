package springsecuritystudy.security.security.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;
import springsecuritystudy.security.domain.entity.RoleHierarchy;
import springsecuritystudy.security.service.RoleHierarchyService;

@Component
public class SecurityInitializer implements ApplicationRunner {

    @Autowired
    private RoleHierarchyService roleHierarchyService;

    @Autowired
    private RoleHierarchyImpl roleHierarchy;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String allHierarchy = roleHierarchyService.findAllHierarchy();
        roleHierarchy.setHierarchy(allHierarchy);
    }
}
