package hotel.paradis.paradis.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import java.time.LocalDate;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    private String nombre;

    @Column(name="apellido_paterno")
    private String apellidoPaterno;

    @Column(name="apellido_materno")
    private String apellidoMaterno;

    private String email;

    private String telefono;

    private String password;

    @Column(name = "fecha_nacimiento")
    @DateTimeFormat(pattern="dd/MM/yyyy")
    private LocalDate fechaNacimiento;

    @Column(name="paypal_email")
    private String paypalEmail;

    @Column(name="paypal_password")
    private String paypalPassword;

    @OneToMany(mappedBy = "usuario")
    private List<Reservacion> reservaciones = new ArrayList<>();

    public List<Reservacion> getReservacions() {
        return reservaciones;
    }

    public void setReservacions(List<Reservacion> reservacions) {
        this.reservaciones = reservacions;
    }

    public Usuario() {
    }

    public Usuario(String nombre, String apellidoPaterno, String apellidoMaterno, String email, String telefono, String password, LocalDate fechaNacimiento) {
        this.nombre = nombre;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.email = email;
        this.telefono = telefono;
        this.password = password;
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getPaypalEmail() {
        return paypalEmail;
    }

    public void setPaypalEmail(String paypalEmail) {
        this.paypalEmail = paypalEmail;
    }

    public String getPaypalPassword() {
        return paypalPassword;
    }

    public void setPaypalPassword(String paypalPassword) {
        this.paypalPassword = paypalPassword;
    }

    public List<Reservacion> getReservaciones() {
        return reservaciones;
    }

    public void setReservaciones(List<Reservacion> reservaciones) {
        this.reservaciones = reservaciones;
    }
}
