package hotel.paradis.paradis.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "habitaciones")
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_habitacion")
    private Integer idHabitacion;

    private String nombre;

    private Integer precio;

    private String descripcion;

    private String cama;

    private Integer categoria;

    @JoinTable(
            name="habitaciones_servicios",
            joinColumns = @JoinColumn(name="id_habitacion", nullable = false),
            inverseJoinColumns = @JoinColumn(name="id_servicio", nullable = false)
    )
    @ManyToMany(cascade = CascadeType.ALL)
    private List<Servicio> servicios;

    @OneToMany(mappedBy = "habitacion")
    private List<Reservacion> reservaciones = new ArrayList<>();

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }

    public void addServicio(Servicio servicio){
        if(this.servicios == null)
            this.servicios = new ArrayList<>();
        this.servicios.add(servicio);
    }

    public Habitacion() {
    }

    public Habitacion(String nombre, Integer precio, String descripcion, String cama, Integer categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cama = cama;
        this.categoria = categoria;
    }

    public Habitacion(String nombre, Integer precio, String descripcion, String cama, Integer categoria, List<Servicio> servicios) {
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.cama = cama;
        this.categoria = categoria;
        this.servicios = servicios;
    }

    public Integer getIdHabitacion() {
        return idHabitacion;
    }

    public void setIdHabitacion(Integer idHabitacion) {
        this.idHabitacion = idHabitacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public List<Servicio> getServicios() {
        return servicios;
    }

    public void setServicios(List<Servicio> servicios) {
        this.servicios = servicios;
    }
}
