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

    @Column(name = "descripcion", length = 250)
    @Basic(optional = false)
    private String descripcionConcepto;

    @Column(name = "puntosRequeridos", length = 15)
    @Basic(optional = false)
    private String puntosRequeridos;

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

    public String getPuntosRequeridos() {
        return puntosRequeridos;
    }

    public void setPuntosRequeridos(String puntosRequeridos) {
        this.puntosRequeridos = puntosRequeridos;
    }
}
