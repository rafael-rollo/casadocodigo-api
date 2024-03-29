package br.com.rollo.rafael.casadocodigoapi.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import br.com.rollo.rafael.casadocodigoapi.domain.users.UserLoading;
import br.com.rollo.rafael.casadocodigoapi.infrastructure.security.JWTAuthenticationFilter;
import br.com.rollo.rafael.casadocodigoapi.infrastructure.security.TokenManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private TokenManager tokenManager;
	
	@Autowired
	private UserLoading userLoading;
	
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
        web.ignoring().antMatchers("/swagger-ui/**", "/v2/api-docs", 
                "/webjars/**", "/configuration/**", 
                "/swagger-resources/**");
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(userLoading)
	    	.passwordEncoder(passwordEncoder());
	}

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	JWTAuthenticationFilter jwtAuthenticationFilter = 
    			new JWTAuthenticationFilter(tokenManager, userLoading);
    	
        http.authorizeRequests()
        	.antMatchers(HttpMethod.GET, "/api/author/**").permitAll()
        	.antMatchers("/api/author/**").hasRole("ADMIN")
        	.antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
            .antMatchers("/api/book/**").hasRole("ADMIN")
            .antMatchers("/api/auth").permitAll()
            .anyRequest().authenticated()
         .and()
            .cors()
        .and()
            .csrf().disable()
        .sessionManagement()
        	.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        	.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        	.authenticationEntryPoint(new JWTAuthenticationEntryPoint());
    }
    
    private static class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {

    	private static final Logger logger = LoggerFactory
                .getLogger(JWTAuthenticationEntryPoint.class);
    	
		@Override
		public void commence(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException authException) throws IOException, ServletException {
			
			logger.error("Um acesso não autorizado foi verificado. Mensagem: {}", 
                    authException.getMessage());

			response.sendError(HttpServletResponse.SC_UNAUTHORIZED, 
                    "Não é possível acessar esse recurso.");

		}
    }
}
