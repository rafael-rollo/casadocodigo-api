package br.com.rollo.rafael.casadocodigoapi.application.output;

public class AuthenticationTokenOutput {

    private String tokenType;
    private String token;

    public AuthenticationTokenOutput(String tokenType, String token) {
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
