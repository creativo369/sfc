package model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "usoPunto")
public class UsoPunto {
    @Id
    @Column(name = "id_usoPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "usoPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usoPuntoSec", sequenceName = "usoPunto_sec", allocationSize = 0)
    private Integer idusoPunto;

    // Dueño de la relación o owner, por tener la referencia al campo de la relación a la tabla Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente" )
    /* 2 atributos
        name : el atributo que esta en la tabla UsoPunto que referencia a la tabla cliente.
        referencedColumnName : al atributo o columna de la tabla cliente a la cual hace referencia.
    * */
    private Cliente cliente;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    @Column(name = "fecha_usoPunto")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @Column(name = "concepto_uso",length = 200)
    @Basic(optional = false)
    private String concepto_uso;

    public UsoPunto() {
    }

    public Integer getIdusoPunto() {
        return idusoPunto;
    }

    public void setIdusoPunto(Integer idusoPunto) {
        this.idusoPunto = idusoPunto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getConcepto_uso() {
        return concepto_uso;
    }

    public void setConcepto_uso(String concepto_uso) {
        this.concepto_uso = concepto_uso;
    }
}
