package br.com.rollo.rafael.casadocodigoapi.domain.users;

import java.util.Optional;

import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserRepository extends Repository<User, Long>{

	Optional<User> findByEmail(String email);
}
