package springsecuritystudy.security.service;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import springsecuritystudy.security.domain.entity.Resources;
import springsecuritystudy.security.repository.AccessIpRepository;
import springsecuritystudy.security.repository.ResourcesRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class SecurityResourceService {

    private ResourcesRepository resourcesRepository;
    private AccessIpRepository accessIpRepository;

    public SecurityResourceService(ResourcesRepository resourcesRepository, AccessIpRepository accessIpRepository) {
        this.resourcesRepository = resourcesRepository;
        this.accessIpRepository = accessIpRepository;
    }

    public LinkedHashMap<RequestMatcher, List<ConfigAttribute>> getResourceList() {
        LinkedHashMap<RequestMatcher, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllResources();
        resourcesList.forEach( resource -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            resource.getRoleSet().forEach( role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(new AntPathRequestMatcher(resource.getResourceName()), configAttributeList);
        });
        return result;
    }

    public LinkedHashMap<String, List<ConfigAttribute>> getMethodResourceList() {
        LinkedHashMap<String, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllMethodResources();
        resourcesList.forEach( resource -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            resource.getRoleSet().forEach( role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(resource.getResourceName(), configAttributeList);
        });
        return result;
    }

    public LinkedHashMap<String, List<ConfigAttribute>> getPointcutResourceList() {
        LinkedHashMap<String, List<ConfigAttribute>> result = new LinkedHashMap<>();
        List<Resources> resourcesList = resourcesRepository.findAllPointcutResources();
        resourcesList.forEach( resource -> {
            List<ConfigAttribute> configAttributeList = new ArrayList<>();
            resource.getRoleSet().forEach( role -> {
                configAttributeList.add(new SecurityConfig(role.getRoleName()));
            });
            result.put(resource.getResourceName(), configAttributeList);
        });
        return result;
    }

    public List<String> getAccessIpList() {
        List<String> accessIpList = accessIpRepository.findAll().stream().map(accessIp -> accessIp.getIpAddress()).collect(Collectors.toList());
        return accessIpList;
    }
}
