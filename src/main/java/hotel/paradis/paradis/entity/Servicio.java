package hotel.paradis.paradis.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "servicios")
public class Servicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_servicio")
    private Integer idServicio;

    private String nombre;

    private String descripcion;

    private String icon;

    @ManyToMany(mappedBy = "servicios")
    private List<Habitacion> habitaciones;

    public Servicio() {
    }

    public Servicio(String nombre, String descripcion, String icon) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.icon = icon;
    }

    public Integer getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(Integer idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public List<Habitacion> getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(List<Habitacion> habitaciones) {
        this.habitaciones = habitaciones;
    }
}
