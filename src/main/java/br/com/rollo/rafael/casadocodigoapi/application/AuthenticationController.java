package br.com.rollo.rafael.casadocodigoapi.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rollo.rafael.casadocodigoapi.application.input.SignInInput;
import br.com.rollo.rafael.casadocodigoapi.application.output.AuthenticationTokenOutput;
import br.com.rollo.rafael.casadocodigoapi.infrastructure.security.TokenManager;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenManager tokenGeneration;

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AuthenticationTokenOutput> authenticate(@RequestBody SignInInput signInInfo) {
		UsernamePasswordAuthenticationToken authenticationToken = signInInfo.build();
		
		try {
			Authentication authentication = authManager
					.authenticate(authenticationToken);
			
			String jwt = tokenGeneration.generateToken(authentication);	
			AuthenticationTokenOutput tokenOutput = new AuthenticationTokenOutput("Bearer", jwt);
			
			return ResponseEntity.ok(tokenOutput);
		
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}
	}
}
