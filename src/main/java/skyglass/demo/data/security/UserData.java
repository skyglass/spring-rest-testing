package skyglass.demo.data.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.security.User;

@Transactional(readOnly = true) 
public interface UserData extends JpaRepository<User, Long> {
    User findByLogin(String login);

}
