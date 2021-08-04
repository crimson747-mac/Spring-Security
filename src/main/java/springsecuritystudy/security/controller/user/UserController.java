package springsecuritystudy.security.controller.user;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import springsecuritystudy.security.domain.Account;
import springsecuritystudy.security.domain.AccountDto;
import springsecuritystudy.security.service.UserService;

@Controller
@AllArgsConstructor
public class UserController {

    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @GetMapping("/mypage")
    public String myPage() {
        return "user/mypage";
    }

    @GetMapping("/users")
    public String createUser() {
        return "user/login/register";
    }

    @PostMapping("/users")
    public String createUser(@ModelAttribute AccountDto accountDto) {

        Account account = new Account(
                accountDto.getUsername(),
                accountDto.getPassword(),
                accountDto.getEmail(),
                accountDto.getAge(),
                accountDto.getRole()
        );
        account.setPassword(passwordEncoder.encode(account.getPassword()));
        userService.createUser(account);

        return "redirect:/";
    }
}
