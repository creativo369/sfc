package model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id_cliente")
    @Basic(optional = false) // not null
    @GeneratedValue(generator = "clienteSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "clienteSec", sequenceName = "cliente_sec", allocationSize = 0)
    private Integer idCliente;

    @Column(name = "nombre", length = 50)
    @Basic(optional = false)
    private String nombre;

    @Column(name = "apellido", length = 50)
    @Basic(optional = false)
    private String apellido;

    @Column(name = "numero_documento", length = 15, unique = true)
    @Basic(optional = false)
    private String numeroDocumento;

    @Column(name = "tipo_documento", length = 80)
    @Basic(optional = false)
    private String tipoDocumento;

    @Column(name = "nacionalidad", length = 15)
    @Basic(optional = false)
    private String nacionalidad;

    @Column(name = "email", length = 60)
    @Basic(optional = false)
    private String email;

    @Column(name = "telefono", length = 20)
    @Basic(optional = false)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;

//     Hay veces que queremos ir de este lado para la otra tabla entonces ponemos esta relación:
//    @OneToMany(mappedBy = "cliente") // a que atributo de la clase BolsaPunto hace referencia para mapear
//    private List<BolsaPunto> listaBolsas;

//    @OneToMany(mappedBy = "cliente") // a que atributo de la clase BolsaPunto hace referencia para mapear
//    private List<UsoPunto> listaUsoPuntos;

    public Cliente() {

    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

//    public List<BolsaPunto> getListaBolsas() {
//        return listaBolsas;
//    }
//
//    public void setListaBolsas(List<BolsaPunto> listaBolsas) {
//        this.listaBolsas = listaBolsas;
//    }

//    public List<UsoPunto> getListaUsoPuntos() {
//        return listaUsoPuntos;
//    }
//
//    public void setListaUsoPuntos(List<UsoPunto> listaUsoPuntos) {
//        this.listaUsoPuntos = listaUsoPuntos;
//    }

    public void merge(Cliente c) {
        setNumeroDocumento(c.getNumeroDocumento());
        setTipoDocumento(c.getTipoDocumento());
        setNacionalidad(c.getNacionalidad());
        setEmail(c.getEmail());
        setTelefono(c.getTelefono());
    }
}
