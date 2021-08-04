package springsecuritystudy.security.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Data
public class Account {

    @Id @GeneratedValue
    private Long id;

    private String username;
    private String password;
    private String email;
    private String age;
    private String role;

    public Account() {}

    public Account(String username, String password, String email, String age, String role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.role = role;
    }

}
