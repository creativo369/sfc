package model;

import javax.persistence.*;


@Entity
@Table(name ="conceptoPunto")
public class ConceptoPunto {
    @Id
    @Column(name ="id_conceptoPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "conceptoPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conceptoPuntoSec", sequenceName = "conceptoPunto_sec", allocationSize = 0)
    private Integer idConceptoPunto;

    @Column(name = "descripcion_concepto", length = 200)
    @Basic(optional = false)
    private String descripcionConcepto;

    @Column(name = "puntos_requeridos")
    @Basic(optional = false)
    private Integer puntosRequeridos;

    public ConceptoPunto() {
    }

    public Integer getIdConceptoPunto() {
        return idConceptoPunto;
    }

    public void setIdConceptoPunto(Integer idConceptoPunto) {
        this.idConceptoPunto = idConceptoPunto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }

    public Integer getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(Integer puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
