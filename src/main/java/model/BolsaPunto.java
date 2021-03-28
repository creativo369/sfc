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

//    // Dueño de la relación o owner, por tener la referencia al campo de la relación a la tabla Cliente
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente" )
//    /* 2 atributos
//        name : el atributo que esta en la tabla BolsaPunto que referencia a la tabla cliente.
//        referencedColumnName : al atributo o columna de la tabla cliente a la cual hace referencia.
//    * */
//    private Cliente cliente;

    @Column(name = "fecha_asignacion_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaAsignacion;

    @Column(name = "fecha_caducidad_puntaje")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaCaducidad;

    @Column(name = "puntaje_asignado")
    @Basic(optional = false)
    private Integer puntajeAsignado;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    @Column(name = "saldo_puntos")
    @Basic(optional = false)
    private Integer saldo;

    @Column(name = "monto_operacion")
    @Basic(optional = false)
    private Integer monto;

//    @OneToMany(mappedBy = "bolsaPunto") // a que atributo de la clase BolsaPunto hace referencia para mapear
//    private List<DetUsoPunto> listaDetUsoPunto;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "puntos", referencedColumnName = "id_reglaPunto")
//    private ReglaAsignacionPunto puntos;

    public BolsaPunto() {
    }

    public Integer getIdBolsaPunto() {
        return idBolsaPunto;
    }

    public void setIdBolsaPunto(Integer idBolsaPunto) {
        this.idBolsaPunto = idBolsaPunto;
    }

//    public Cliente getCliente() {
//        return cliente;
//    }
//
//    public void setCliente(Cliente cliente) {
//        this.cliente = cliente;
//    }

    public Date getFechaAsignacion() {
        return fechaAsignacion;
    }

    public void setFechaAsignacion(Date fechaAsignacion) {
        this.fechaAsignacion = fechaAsignacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

   /* public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }*/
//     public void setFechaCaducidad() {
//            this.fechaCaducidad = this.puntos.getVencpunto().getFechaFinValidez();
//        }
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

    public Integer getSaldo() {
        return saldo;
    }

    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    /*TODO: hacer los calculos respectivos a monto, saldo, puntaje utilizado*/

}
