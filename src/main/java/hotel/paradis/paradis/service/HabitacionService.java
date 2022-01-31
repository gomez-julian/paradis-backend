/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.paradis.paradis.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.transaction.Transactional;

import hotel.paradis.paradis.entity.Habitacion;
import hotel.paradis.paradis.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author maste
 */
@Service
public class HabitacionService {
    private final HabitacionRepository habitacionRepository;
    
    @Autowired
    public HabitacionService(HabitacionRepository habitacionRepository) {
        this.habitacionRepository = habitacionRepository;
    }
    
    public List<Habitacion> getHabitaciones(){
    
        return habitacionRepository.findAll();

    }
    
    public Habitacion getHabitacion(Long id){
        System.out.println(habitacionRepository.findById(id).orElse(null).toString());
        return habitacionRepository.findById(id).orElse(null);
    }

    public void addNewHabitacion(Habitacion habitacion) {
        Optional<Habitacion> habitacionOpcional = habitacionRepository.findHabitacionById(habitacion.getIdHabitacion());
        //System.out.println(habitacion);
        
        if(habitacionOpcional.isPresent()){
        
            throw new IllegalStateException("ID registrado anteriormente");
        }
        habitacionRepository.save(habitacion);
    }
    
    public void deleteHabitacion(Long habitacionId){
    
        boolean exists = habitacionRepository.existsById(habitacionId);
        if(!exists){
        
            throw new IllegalStateException(
            
                    "El habitacion con el id "+ habitacionId + " no existe"
            );
        }
        habitacionRepository.deleteById(habitacionId);
    }
/*
    @Transactional
    void rentarHabitacion(
            Long habitacionId,
            Long unidades
            ) {
        Habitacion habitacion = habitacionRepository.findHabitacionById(habitacionId)
                .orElseThrow(() -> new IllegalStateException(""
                + "la habitacion con el id "+ habitacionId + " no existe"));

        if(unidades != null && unidades >= 0
                && !Objects.equals(habitacion.getDisponibilidad(), disponibilidad)){
        
            habitacion.setDisponibilidad(disponibilidad);
        }
        
        habitacionRepository.save(habitacion);
    }
*/
    @Transactional
    public void updateHabitacion(
            Long habitacionId, 
            String nombre, 
            Integer precio, 
            String descripcion,
            String cama,
            Integer categoria) {
        Habitacion habitacion = habitacionRepository.findById(habitacionId)
                .orElseThrow(() -> new IllegalStateException(""
                + "la habitacion con el id "+ habitacionId + " no existe"));
        if (nombre != null && nombre.length() > 0 
                && !Objects.equals(habitacion.getNombre(), nombre)){
        
            habitacion.setNombre(nombre);
        }
        
        if(precio != null && precio > 0
                && !Objects.equals(habitacion.getPrecio(), precio)){
        
            habitacion.setPrecio(precio);
        }

        if(descripcion != null && descripcion.length() >= 0
                && !Objects.equals(habitacion.getDescripcion(), descripcion)){
        
            habitacion.setDescripcion(descripcion);
        }
        
        if(cama != null && cama.length() > 0
                && !Objects.equals(habitacion.getCama(), cama)){

            habitacion.setCama(cama);
            //habitacionRepository.save(habitacion);
        }

        if(categoria != null && categoria >= 0
                && !Objects.equals(habitacion.getCategoria(), categoria)){

            habitacion.setCategoria(categoria);
            //habitacionRepository.save(habitacion);
        }
        habitacionRepository.save(habitacion);
    }   

}
