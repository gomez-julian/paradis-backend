package hotel.paradis.paradis.controller;

import hotel.paradis.paradis.entity.Usuario;
import hotel.paradis.paradis.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author maste
 */
@Controller
@RequestMapping(path="/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService){

        this.usuarioService = usuarioService;
    }

    @GetMapping("/index")
    public String inicio(){

        return "index";
    }

    @GetMapping(value="/mostrar")
    public String getUsuarios(Model model){

        model.addAttribute("UsuarioList",usuarioService.getUsuarios());
        return "Usuario-list";
    }
/*
    @GetMapping(value="/Usuario-rentar")
    public String venderProductos(Model model){
        // No s� qu� hacer aqu� xd
        model.addAttribute("habitacionList",usuarioService.getHabitaciones());
        return "habitacion-rentar";
    }
*/
    @GetMapping("/agregar-usuario")
    public String agregarUsuario(Model model){

        model.addAttribute("usuario", new Usuario());
        return "agregar-usuario";
    }


    @PostMapping("/agregar")
    public String registerNewHabitacion(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttrs){
        System.out.println(usuario);
        usuarioService.addNewUsuario(usuario);
        redirectAttrs
                .addFlashAttribute("mensaje","Agregado correctamente")
                .addFlashAttribute("clase","success");
        return "redirect:/usuario/mostrar";
    }

    @GetMapping(path="/eliminar/{usuarioId}")
    public String deleteUsuario(@PathVariable("usuarioId") Long usuarioId){

        usuarioService.deleteUsuario(usuarioId);
        return "redirect:/usuario/mostrar";
    }

    //Método para desplegar formulario de edición
    @GetMapping(path="/editarusuario/{usuarioId}")
    public String editarUsuario(@PathVariable("usuarioId") Long usuarioId, Model model){

        model.addAttribute("usuario", usuarioService.getUsuario(usuarioId));
        return "editar-usuario";
    }

    @PostMapping(path="/editarusuario")
    public String updateUsuario(@ModelAttribute Usuario usuario){

        usuarioService.updateHabitacion(usuario.getIdUsuario(), usuario.getNombre(),
                usuario.getApellidoPaterno(),usuario.getApellidoPaterno(),
                usuario.getEmail(), usuario.getTelefono(), usuario.getPassword());
        return "redirect:/usuario/mostrar";
    }
}
