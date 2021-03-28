package model;

import javax.persistence.*;

@Entity
@Table(name ="conceptoUsoPunto")
public class ConceptoUsoPunto {
    @Id
    @Column(name ="id_conceptoUsoPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "conceptoUsoPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "conceptoUsoPuntoSec", sequenceName = "conceptoUsoPunto_sec", allocationSize = 0)
    private Integer idConceptoUsoPunto;

    @Column(name = "descripcion_concepto", length = 200)
    @Basic(optional = false)
    private String descripcionConcepto;

    @Column(name = "punto_requerido")
    @Basic(optional = false)
    private Integer puntoRequerido;

    @OneToOne(mappedBy = "conceptoUsoPunto") // a que atributo de la clase BolsaPunto hace referencia para mapear
    private UsoPunto UsoPunto;

    public ConceptoUsoPunto() {
    }

    public Integer getIdConceptoUsoPunto() {
        return idConceptoUsoPunto;
    }

    public void setIdConceptoUsoPunto(Integer idConceptoUsoPunto) {
        this.idConceptoUsoPunto = idConceptoUsoPunto;
    }

    public String getDescripcionConcepto() {
        return descripcionConcepto;
    }

    public void setDescripcionConcepto(String descripcionConcepto) {
        this.descripcionConcepto = descripcionConcepto;
    }

    public Integer getPuntoRequerido() {
        return puntoRequerido;
    }

    public void setPuntoRequerido(Integer puntoRequerido) {
        this.puntoRequerido = puntoRequerido;
    }

    public model.UsoPunto getUsoPunto() {
        return UsoPunto;
    }

    public void setUsoPunto(model.UsoPunto usoPunto) {
        UsoPunto = usoPunto;
    }

    public void merge (ConceptoUsoPunto c){
        setDescripcionConcepto(c.getDescripcionConcepto());
        setPuntoRequerido(c.getPuntoRequerido());
    }
}
