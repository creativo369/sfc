package model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "usoPunto")
public class UsoPunto {
    @Id
    @Column(name = "id_usoPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "usoPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "usoPuntoSec", sequenceName = "usoPunto_sec", allocationSize = 0)
    private Integer idUsoPunto;

    // Dueño de la relación o owner, por tener la referencia al campo de la relación a la tabla Cliente
    @ManyToOne(fetch = FetchType.EAGER)
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
    private Date fechaUsoPunto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="concepto_usoPunto", referencedColumnName = "id_conceptoUsoPunto")
    private ConceptoUsoPunto conceptoUsoPunto;

    @OneToOne(mappedBy = "usoPunto")
    private DetUsoPunto detUsoPunto;

    public UsoPunto() {
    }

    public Integer getIdUsoPunto() {
        return idUsoPunto;
    }

    public void setIdUsoPunto(Integer idUsoPunto) {
        this.idUsoPunto = idUsoPunto;
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

    public Date getFechaUsoPunto() {
        return fechaUsoPunto;
    }

    public void setFechaUsoPunto(Date fechaUsoPunto) {
        this.fechaUsoPunto = fechaUsoPunto;
    }

    public ConceptoUsoPunto getConceptoUsoPunto() {
        return conceptoUsoPunto;
    }

    public void setConceptoUsoPunto(ConceptoUsoPunto conceptoUsoPunto) {
        this.conceptoUsoPunto = conceptoUsoPunto;
    }

    public DetUsoPunto getDetUsoPunto() {
        return detUsoPunto;
    }

    public void setDetUsoPunto(DetUsoPunto detUsoPunto) {
        this.detUsoPunto = detUsoPunto;
    }

}
