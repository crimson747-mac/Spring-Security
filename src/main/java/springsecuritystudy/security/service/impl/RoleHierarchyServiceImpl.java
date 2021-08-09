package springsecuritystudy.security.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsecuritystudy.security.domain.entity.RoleHierarchy;
import springsecuritystudy.security.repository.RoleHierarchyRepository;
import springsecuritystudy.security.service.RoleHierarchyService;

import javax.transaction.Transactional;
import java.util.Iterator;
import java.util.List;

@Service
public class RoleHierarchyServiceImpl implements RoleHierarchyService {

    @Autowired
    private RoleHierarchyRepository roleHierarchyRepository;

    @Transactional
    @Override
    public String findAllHierarchy() {

        List<RoleHierarchy> rolesHierarchy = roleHierarchyRepository.findAll();

        Iterator<RoleHierarchy> itr = rolesHierarchy.iterator();
        StringBuilder concatedRoles = new StringBuilder();
        while (itr.hasNext()) {
            RoleHierarchy roleHierarchy = itr.next();
            if (roleHierarchy.getParentName() != null) {
                concatedRoles.append(roleHierarchy.getParentName().getChildName());
                concatedRoles.append(" > ");
                concatedRoles.append(roleHierarchy.getChildName());
                concatedRoles.append("\n");
            }
        }
        return concatedRoles.toString();

    }
}