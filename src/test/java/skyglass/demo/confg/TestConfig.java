package skyglass.demo.confg;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;

import skyglass.demo.config.SecurityConfig;
import skyglass.demo.controller.RestPublisher;
import skyglass.demo.controller.RestPublisherImpl;

@Configuration
@ComponentScan("skyglass.demo")
public class TestConfig {

    public static final String SERVER_URL = "http://localhost:8080";
    
	@Autowired
	UserDetailsService userDetailsService;    

    @Bean
    public RestPublisher getRestPublisher(){
        return new RestPublisherImpl(SERVER_URL, "admin", "admin");
    }
    
    @Configuration
	@Order(SecurityProperties.DEFAULT_FILTER_ORDER)
	protected static class SecurityConfiguration extends SecurityConfig {

    	@Override
    	protected void configure(HttpSecurity http) throws Exception {
			http.httpBasic().and().authorizeRequests()
			.antMatchers("/index.html", "/")
			.permitAll().anyRequest().authenticated().and().csrf().disable();
    	}
	}
    
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		return authenticationProvider;
	}    
}
