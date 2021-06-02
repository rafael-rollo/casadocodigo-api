package br.com.rollo.rafael.casadocodigoapi.domain.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserLoading implements UserDetailsService {

	@Autowired
	private UserRepository users;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> possibleUser = users.findByEmail(username);
		
		return possibleUser.orElseThrow(() -> 
			new UsernameNotFoundException("Não foi encontrado usuário para o e-mail: " + username));
	}

	public UserDetails loadUserById(Long id) {
        Optional<User> possibleUser = users.findById(id);

        return possibleUser.orElseThrow(
            () -> new UsernameNotFoundException("Não foi encontrado usuário para o id: " + id));

	}
}
