package skyglass.demo.data.security;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.security.Token;

@Transactional(readOnly = true) 
public interface TokenData extends JpaRepository<Token, String> {
	
    List<Token> findByUserLogin(String userLogin);
	
}
