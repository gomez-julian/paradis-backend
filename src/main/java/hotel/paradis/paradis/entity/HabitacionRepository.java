/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.paradis.paradis.entity;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Lenovo
 */
@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long>{
    
    @Query("select s from Habitaciones s where s.id_habitacion =?1")
    Optional<Habitacion> findHabitacionById(Long id);

    @Query("select s from Habitacion s where s.nombre =?1")
    Optional<Habitacion> findHabitacionByName(String nombre);
}
