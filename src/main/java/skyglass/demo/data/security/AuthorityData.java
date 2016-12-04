package skyglass.demo.data.security;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import skyglass.demo.data.model.security.Authority;

@Transactional(readOnly = true) 
public interface AuthorityData extends JpaRepository<Authority, Long> {
	
	public Authority findByName(String name);

}