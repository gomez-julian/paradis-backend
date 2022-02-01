package hotel.paradis.paradis.repository;
import java.util.Optional;

import hotel.paradis.paradis.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Jester
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    @Query("select s from Usuario s where s.idUsuario =?1")
    Optional<Usuario> findUsuarioById(Long id);

    @Query("select s from Usuario s where s.nombre =?1")
    Optional<Usuario> findUsuarioByName(String nombre);

    @Query("select s from Usuario s where s.email =?1")
    Optional<Usuario> findUsuarioByEmail(String email);
}
