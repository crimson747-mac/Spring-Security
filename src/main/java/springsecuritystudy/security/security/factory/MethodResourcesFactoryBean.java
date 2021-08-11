package springsecuritystudy.security.security.factory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.util.matcher.RequestMatcher;
import springsecuritystudy.security.service.SecurityResourceService;

import java.util.LinkedHashMap;
import java.util.List;

public class MethodResourcesFactoryBean implements FactoryBean<LinkedHashMap<String, List<ConfigAttribute>>> {


    private SecurityResourceService securityResourceService;
    private LinkedHashMap<String, List<ConfigAttribute>> resourceMap;
    private String resourceType;

    public void setSecurityResourceService(SecurityResourceService securityResourceService) {
        this.securityResourceService = securityResourceService;
    }
    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    @Override
    public LinkedHashMap<String, List<ConfigAttribute>> getObject(){

        if(resourceMap == null) {
            if("method".equals(resourceType)) {
                resourceMap = securityResourceService.getMethodResourceList();
            } else if("pointcut".equals(resourceType)) {
                resourceMap = securityResourceService.getPointcutResourceList();
            }
        }

        return resourceMap;
    }

    private void init() {
        resourceMap = securityResourceService.getMethodResourceList();
    }

    @Override
    public Class<?> getObjectType() {
        return LinkedHashMap.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }


}
