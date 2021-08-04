package springsecuritystudy.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springsecuritystudy.security.domain.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
}
