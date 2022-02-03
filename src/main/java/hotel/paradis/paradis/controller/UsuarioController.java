package hotel.paradis.paradis.controller;

import hotel.paradis.paradis.entity.Usuario;
import hotel.paradis.paradis.repository.UsuarioRepository;
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

    @GetMapping("/sign-up")
    public String agregarUsuario(Model model){

        model.addAttribute("usuario", new Usuario());
        return "sign-up";
    }

    @PostMapping("/sign-up")
    public String registerNewUsuario(@ModelAttribute Usuario usuario, RedirectAttributes redirectAttrs){
        System.out.println(usuario);
        usuarioService.addNewUsuario(usuario);
        redirectAttrs
                .addFlashAttribute("mensaje","Agregado correctamente")
                .addFlashAttribute("clase","success");
        return "redirect:/usuario/login";
    }

    @GetMapping(path="/eliminar/{usuarioId}")
    public String deleteUsuario(@PathVariable("usuarioId") Long usuarioId){

        usuarioService.deleteUsuario(usuarioId);
        return "redirect:/usuario/login";
    }

    //Método para desplegar formulario de edición
    @GetMapping(path="/editarusuario/{usuarioId}")
    public String editarUsuario(@PathVariable("usuarioId") Long usuarioId, Model model){

        model.addAttribute("usuario", usuarioService.getUsuario(usuarioId));
        return "profile";
    }

    @PostMapping(path="/editarusuario")
    public String updateUsuario(@ModelAttribute Usuario usuario){

        usuarioService.updateUsuario(usuario.getIdUsuario(), usuario.getNombre(),
                usuario.getApellidoPaterno(),usuario.getApellidoMaterno(),
                usuario.getEmail(), usuario.getTelefono(), usuario.getPassword());
        return "redirect:/usuario/profile/" + usuario.getIdUsuario();
    }

    @GetMapping(path="/login")
    public String logUsuario(){
        return "login";
    }

    @PostMapping(path="/login")
    public String loginUsuario(@ModelAttribute Usuario usuario){
        Usuario us = usuarioService.getUsuario(usuario.getEmail());
        if(us==null)
            return "redirect:/usuario/sign-up";
        if(usuario.getPassword().equals(us.getPassword())){
            return "redirect:/usuario/profile/"+ us.getIdUsuario();
        }else{
            return "redirect:/usuario/sign-up";
        }
    }

    @GetMapping(path="/profile/{usuarioId}")
    public String profile(@PathVariable("usuarioId") Long usuarioId, Model model){

        model.addAttribute("usuario", usuarioService.getUsuario(usuarioId));
        return "profile";
    }

}
