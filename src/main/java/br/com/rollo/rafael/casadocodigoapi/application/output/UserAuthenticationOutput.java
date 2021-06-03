package br.com.rollo.rafael.casadocodigoapi.application.output;

import br.com.rollo.rafael.casadocodigoapi.domain.users.User;

public class UserAuthenticationOutput {
	
    private String name;
    private String role;
    private Authentication authentication;
    
    public UserAuthenticationOutput(User user, String token) {
        this.name = user.getName();
        this.role = user.getPrimaryRoleName();
        this.authentication = new Authentication("Bearer", token);
    }

	public String getName() {
		return name;
	}

	public String getRole() {
		return role.split("_")[1];
	}

	public Authentication getAuthentication() {
		return authentication;
	}
	
	public static class Authentication {
		private String tokenType;
	    private String token;

	    public Authentication(String tokenType, String token) {
	        this.tokenType = tokenType;
	        this.token = token;
	    }

	    public String getToken() {
	        return token;
	    }

	    public String getTokenType() {
	        return tokenType;
	    }
	}

}
