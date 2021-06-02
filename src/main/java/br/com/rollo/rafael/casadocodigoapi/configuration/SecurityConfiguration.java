package br.com.rollo.rafael.casadocodigoapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService usersService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/*/**.html", "/v2/api-docs", 
                "/webjars/**", "/configuration/**", 
                "/swagger-resources/**");
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(usersService)
	    	.passwordEncoder(passwordEncoder());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
            .antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
            .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
            .antMatchers("/api/auth").permitAll()
            .anyRequest().authenticated()
         .and()
            .cors()
        .and()
            .csrf().disable()
        .sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }
}
