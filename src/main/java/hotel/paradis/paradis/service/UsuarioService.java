package hotel.paradis.paradis.service;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;
import hotel.paradis.paradis.entity.Usuario;
import hotel.paradis.paradis.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jester
 */
@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> getUsuarios(){

        return usuarioRepository.findAll();

    }

    public Usuario getUsuario(Long id){
        System.out.println(usuarioRepository.findById(id).orElse(null).toString());
        return usuarioRepository.findById(id).orElse(null);
    }

    public void addNewUsuario(Usuario usuario) {
        /*Optional<Usuario> usuarioOpcional = usuarioRepository.findUsuarioById(usuario.getIdUsuario());
        System.out.println(usuario);

        if(usuarioOpcional.isPresent()){

            throw new IllegalStateException("ID registrado anteriormente");
        }

         */
        usuarioRepository.save(usuario);
    }

    public void deleteUsuario(Long usuarioId){

        boolean exists = usuarioRepository.existsById(usuarioId);
        if(!exists){

            throw new IllegalStateException(

                    "El usuario con el id "+ usuarioId + " no existe"
            );
        }
        usuarioRepository.deleteById(usuarioId);
    }

    @Transactional
    public void updateUsuario(
            Long usuarioId,
            String nombre,
            String apellidoPaterno,
            String apellidoMaterno,
            String email,
            String telefono,
            String password) {

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new IllegalStateException(""
                        + "El usuario con el id "+ usuarioId + " no existe"));

        if (nombre != null && nombre.length() > 0
                && !Objects.equals(usuario.getNombre(), nombre)){

            usuario.setNombre(nombre);
        }

        if(apellidoPaterno != null && apellidoPaterno.length() > 0
                && !Objects.equals(usuario.getApellidoPaterno(), apellidoPaterno)){

            usuario.setApellidoPaterno(apellidoPaterno);
        }

        if(apellidoMaterno != null && apellidoMaterno.length() > 0
                && !Objects.equals(usuario.getApellidoMaterno(), apellidoMaterno)){

            usuario.setApellidoMaterno(apellidoMaterno);
        }

        if(email != null && email.length() > 0
                && !Objects.equals(usuario.getEmail(), email)){

            /*Optional<Usuario> usuarioOptional = usuarioRepository
                    .findUsuarioByEmail(email);
            if(usuarioOptional.isPresent()){

                throw new IllegalStateException("El correo ya se encuentra registrado");
            }*/
            usuario.setEmail(email);
        }

        if(telefono != null && telefono.length() > 0
                && !Objects.equals(usuario.getTelefono(), telefono)){

            usuario.setTelefono(telefono);
        }

        if(password != null && password.length() > 0
                && !Objects.equals(usuario.getPassword(), password)){

            usuario.setPassword(password);
        }

        usuarioRepository.save(usuario);
    }
}
