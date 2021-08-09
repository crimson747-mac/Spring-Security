package springsecuritystudy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecuritystudy.security.domain.entity.RoleHierarchy;

public interface RoleHierarchyRepository extends JpaRepository<RoleHierarchy, Long> {
    RoleHierarchy findByChildName(String roleName);
}
