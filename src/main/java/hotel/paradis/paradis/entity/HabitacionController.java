/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.paradis.paradis.entity;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author maste
 */
@Controller
@RequestMapping(path="/producto")
public class HabitacionController {
    
    private final HabitacionService habitacionService;
    
    @Autowired
    public HabitacionController(HabitacionService habitacionService){
    
        this.habitacionService = habitacionService;
    }
   
    @GetMapping("/index")
    public String inicio(){
    
        return "index";
    }
    
    @GetMapping(value="/mostrar")
    public String getHabitaciones(Model model){
    
        model.addAttribute("HabitacionList",habitacionService.getHabitaciones());
        return "Habitacion-list";
    }

    @GetMapping(value="/Habitacion-rentar")
    public String venderProductos(Model model){
    // No sé qué hacer aquí xd
        model.addAttribute("habitacionList",habitacionService.getHabitaciones());
        return "habitacion-rentar";
    }
    
    @GetMapping("/agregar-habitacion")
    public String agregarHabitacion(Model model){
    
        model.addAttribute("habitacion", new Habitacion());
        return "agregar-habitacion";
    }

    
    @PostMapping("/agregar")
    public String registerNewHabitacion(@ModelAttribute Habitacion habitacion, RedirectAttributes redirectAttrs){
        System.out.println(habitacion);
        habitacionService.addNewHabitacion(habitacion);
        redirectAttrs
                .addFlashAttribute("mensaje","Agregado correctamente")
                .addFlashAttribute("clase","success");
        return "redirect:/habitacion/mostrar";
    }
    
    @GetMapping(path="/eliminar/{habitacionId}")
    public String deleteHabitacion(@PathVariable("habitacionId") Long habitacionId){
    
        habitacionService.deleteHabitacion(habitacionId);
        return "redirect:/habitacion/mostrar";
    }
    
    //MÃ©todo para desplegar formulario de ediciÃ³n
    @GetMapping(path="/editarhabitacion/{habitacionId}")
    public String editarHabitacion(@PathVariable("habitacionId") Long habitacionId, Model model){
    
        model.addAttribute("habitacion", habitacionService.getHabitacion(habitacionId));
        return "editar-habitacion";
    }
    
    @PostMapping(path="/editarhabitacion")
    public String updateProducto(@ModelAttribute Habitacion habitacion){
    
        habitacionService.updateHabitacion(habitacion.getIdHabitacion(), habitacion.getNombre(), 
                habitacion.getPrecio(),habitacion.getDescripcion(), habitacion.getCama(), habitacion.getCategoria());
        return "redirect:/habitacion/mostrar";
    }
}
