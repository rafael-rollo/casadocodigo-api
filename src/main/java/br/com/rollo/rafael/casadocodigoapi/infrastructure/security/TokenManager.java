package br.com.rollo.rafael.casadocodigoapi.infrastructure.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import br.com.rollo.rafael.casadocodigoapi.domain.users.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager {

	@Value("${cdc.security.jwt.secret}")
	private String secret;
	
	@Value("${cdc.security.jwt.expiration}")
	private long expirationInMillis;
	
	public String generateToken(Authentication authentication) {
		User user = (User) authentication.getPrincipal();
				
		final Date now = new Date();
        final Date expiration = new Date(now.getTime() + 
                this.expirationInMillis);

		return Jwts.builder()
			.setIssuer("Casa do Código API")
			.setSubject(Long.toString(user.getId()))
			.setIssuedAt(now)
			.setExpiration(expiration)
			.signWith(SignatureAlgorithm.HS256, this.secret)
			.compact();
	}
	
    public boolean isValid(String jwt) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(jwt);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public Long getUserIdFromToken(String jwt) {
        Claims claims = Jwts.parser().setSigningKey(this.secret)
            .parseClaimsJws(jwt).getBody();

        return Long.parseLong(claims.getSubject());
    }

}
