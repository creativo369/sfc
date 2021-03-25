package model;

import javax.persistence.*;
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
    private Integer id_cliente;

    @Column(name = "nombre", length = 50)
    @Basic(optional = false)
    private String nombre;

    @Column(name = "apellido", length = 50)
    @Basic(optional = false)
    private String apellido;

    @Column(name = "numero_documento", length = 15, unique = true)
    @Basic(optional = false)
    private String numero_documento;

    @Column(name = "tipo_documento", length = 80)
    @Basic(optional = false)
    private String tipo_documento;

    @Column(name = "nacionalidad", length = 15)
    @Basic(optional = false)
    private String nacionalidad;

    @Column(name = "email", length = 60 )
    @Basic(optional = false)
    private String email;

    @Column(name = "telefono", length = 20)
    @Basic(optional = false)
    private String telefono;

    @Column(name = "fecha_nacimiento")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha_nacimiento;

   /* // Hay veces que queremos ir de este lado para la otra tabla entonces ponemos esta relación:
    @OneToMany(mappedBy = "bolsaPunto") // a que atributo de la clase BolsaPunto hace referencia para mapear
    private List<BolsaPunto> listaBolsa;*/

    public Cliente(){

    }

    public Integer getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(Integer id_cliente) {
        this.id_cliente = id_cliente;
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

    public String getNumero_documento() {
        return numero_documento;
    }

    public void setNumero_documento(String numero_documento) {
        this.numero_documento = numero_documento;
    }

    public String getTipo_documento() {
        return tipo_documento;
    }

    public void setTipo_documento(String tipo_documento) {
        this.tipo_documento = tipo_documento;
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


    public Date getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(Date fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    /*    public List<BolsaPunto> getListaBolsa() {
        return listaBolsa;
    }

    public void setListaBolsa(List<BolsaPunto> listaBolsa) {
        this.listaBolsa = listaBolsa;
    }*/
    public void merge (Cliente c){
        setNumero_documento(c.getNumero_documento());
        setTipo_documento(c.getTipo_documento());
        setNacionalidad(c.getNacionalidad());
        setEmail(c.getEmail());
        setTelefono(c.getTelefono());
    }


}
