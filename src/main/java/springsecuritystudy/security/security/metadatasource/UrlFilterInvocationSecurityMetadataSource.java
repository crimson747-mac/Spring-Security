package springsecuritystudy.security.security.metadatasource;

import org.springframework.core.log.LogMessage;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class UrlFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private LinkedHashMap<RequestMatcher, List<ConfigAttribute>> requestMap = new LinkedHashMap<>();

    @Override //실질적인 구현부
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        requestMap.put(
            new AntPathRequestMatcher("/mypage"),
            Arrays.asList(new SecurityConfig("ROLE_USER"))
        );

        if(requestMap != null) {
            Set<Map.Entry<RequestMatcher, List<ConfigAttribute>>> entries = requestMap.entrySet();
            for (Map.Entry<RequestMatcher, List<ConfigAttribute>> entry : entries) {
                RequestMatcher matcher = entry.getKey();
                if(matcher.matches(request)) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    @Override //DefaultFilterInvocationSecurityMetadataSource 에서 가져온다.
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        Set<ConfigAttribute> allAttributes = new HashSet<>();
        this.requestMap.values().forEach(allAttributes::addAll);
        return allAttributes;
    }

    @Override //DefaultFilterInvocationSecurityMetadataSource 에서 가져온다.
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}