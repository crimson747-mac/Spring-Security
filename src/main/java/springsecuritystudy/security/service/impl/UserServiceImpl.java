package springsecuritystudy.security.service.impl;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springsecuritystudy.security.domain.Account;
import springsecuritystudy.security.repository.UserRepository;
import springsecuritystudy.security.service.UserService;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override @Transactional
    public void createUser(Account account) {
        userRepository.save(account);
    }
}
