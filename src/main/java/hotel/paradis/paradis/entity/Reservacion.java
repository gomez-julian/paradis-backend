package hotel.paradis.paradis.entity;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "reservaciones")
public class Reservacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_reservacion")
    private Integer idReservacion;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "id_habitacion")
    private Habitacion habitacion;

    @Column(name = "check_in")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate checkIn;

    @Column(name = "check_out")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate checkOut;

    private Boolean checked;


    public Habitacion getHabitacion() {
        return habitacion;
    }

    public void setHabitacion(Habitacion habitacion) {
        this.habitacion = habitacion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Reservacion() {
    }

    public Reservacion(Usuario usuario, Habitacion habitacion, LocalDate checkIn, LocalDate checkOut) {
        this.usuario = usuario;
        this.habitacion = habitacion;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getIdReservacion() {
        return idReservacion;
    }

    public void setIdReservacion(Integer idReservacion) {
        this.idReservacion = idReservacion;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
