package br.com.rollo.rafael.casadocodigoapi.application.output;

import br.com.rollo.rafael.casadocodigoapi.domain.users.User;

public class UserAuthenticationOutput {

	private Long id;
	private String email;
    private String name;
    private String role;
    private Authentication authentication;
    
    public UserAuthenticationOutput(User user, String token) {
    	this.id = user.getId();
    	this.email = user.getEmail();
        this.name = user.getName();
        this.role = user.getPrimaryRoleName();
        this.authentication = new Authentication("Bearer", token);
    }

	public Long getId() {
		return id;
	}

	public String getEmail() {
		return email;
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
