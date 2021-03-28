package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "bolsaPunto")

public class BolsaPunto {

    @Id
    @Column(name ="id_bolsaPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "bolsaPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bolsaPuntoSec", sequenceName = "bolsaPunto_sec", allocationSize = 0)
    private Integer idBolsaPunto;

    // Dueño de la relación o owner, por tener la referencia al campo de la relación a la tabla Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente" )
    /* 2 atributos
        name : el atributo que esta en la tabla BolsaPunto que referencia a la tabla cliente.
        referencedColumnName : al atributo o columna de la tabla cliente a la cual hace referencia.
    * */
    private Cliente cliente;

    @Column(name = "fecha_asignacion_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacionPuntaje;

    @Column(name = "fecha_caducidad_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidadPuntaje;

    @Column(name = "puntaje_asignado")
    @Basic(optional = false)
    private Integer puntajeAsignado;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    @Column(name = "saldo_puntos")
    @Basic(optional = false)
    private Integer saldoPuntos;

    @Column(name = "monto_operacion")
    @Basic(optional = false)
    private Integer montoOperacion;

    @OneToMany(mappedBy = "bolsaPunto") // a que atributo de la clase BolsaPunto hace referencia para mapear
    private List<DetUsoPunto> listaDetUsoPunto;

    public BolsaPunto() {
    }

    public Integer getIdBolsaPunto() {
        return idBolsaPunto;
    }

    public void setIdBolsaPunto(Integer idBolsaPunto) {
        this.idBolsaPunto = idBolsaPunto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Date getFechaAsignacionPuntaje() {
        return fechaAsignacionPuntaje;
    }

    public void setFechaAsignacionPuntaje(Date fechaAsignacionPuntaje) {
        this.fechaAsignacionPuntaje = fechaAsignacionPuntaje;
    }

    public Date getFechaCaducidadPuntaje() {
        return fechaCaducidadPuntaje;
    }

    public void setFechaCaducidadPuntaje(Date fechaCaducidadPuntaje) {
        this.fechaCaducidadPuntaje = fechaCaducidadPuntaje;
    }

    public Integer getPuntajeAsignado() {
        return puntajeAsignado;
    }

    public void setPuntajeAsignado(Integer puntajeAsignado) {
        this.puntajeAsignado = puntajeAsignado;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Integer getSaldoPuntos() {
        return saldoPuntos;
    }

    public void setSaldoPuntos(Integer saldoPuntos) {
        this.saldoPuntos = saldoPuntos;
    }

    public Integer getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(Integer montoOperacion) {
        this.montoOperacion = montoOperacion;
    }

    public List<DetUsoPunto> getListaDetUsoPunto() {
        return listaDetUsoPunto;
    }

    public void setListaDetUsoPunto(List<DetUsoPunto> listaDetUsoPunto) {
        this.listaDetUsoPunto = listaDetUsoPunto;
    }
}
