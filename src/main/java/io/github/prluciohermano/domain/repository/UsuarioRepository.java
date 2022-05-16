<<<<<<< HEAD
package io.github.prluciohermano.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.prluciohermano.domain.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByLogin(String login);

}
=======
package io.github.prluciohermano.domain.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import io.github.prluciohermano.domain.entity.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByLogin(String login);
}
>>>>>>> 58da461f51bdd56a6f1915e43a0a551db87e3036
