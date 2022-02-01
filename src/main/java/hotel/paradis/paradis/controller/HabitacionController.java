/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.paradis.paradis.controller;

import hotel.paradis.paradis.entity.Habitacion;
import hotel.paradis.paradis.service.HabitacionService;
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
@RequestMapping(path="/habitacion")
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
    
    @GetMapping(value="/crud-hotel")
    public String getHabitaciones(Model model){
    
        model.addAttribute("HabitacionList",habitacionService.getHabitaciones());
        return "crud-hotel";
    }

    @GetMapping(value="/Habitacion-rentar")
    public String venderProductos(Model model){
    // No s� qu� hacer aqu� xd
        model.addAttribute("habitacionList",habitacionService.getHabitaciones());
        return "habitacion-rentar";
    }
    
    @GetMapping("/add-hotel")
    public String agregarHabitacion(Model model){
    
        model.addAttribute("habitacion", new Habitacion());
        return "add-hotel";
    }

    @PostMapping("/add-hotel")
    public String registerNewHabitacion(@ModelAttribute Habitacion habitacion, RedirectAttributes redirectAttrs){
        System.out.println(habitacion);
        habitacionService.addNewHabitacion(habitacion);
        redirectAttrs
                .addFlashAttribute("mensaje","Agregado correctamente")
                .addFlashAttribute("clase","success");
        return "redirect:/habitacion/crud-hotel";
    }
    
    @GetMapping(path="/eliminar/{habitacionId}")
    public String deleteHabitacion(@PathVariable("habitacionId") Long habitacionId){
    
        habitacionService.deleteHabitacion(habitacionId);
        return "redirect:/habitacion/crud-hotel";
    }
    
    //Método para desplegar formulario de edición
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
