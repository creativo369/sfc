package model;

import javax.persistence.*;

@Entity
@Table(name = "detUsoPunto")
public class DetUsoPunto {

    @Id
    @Column(name = "id_detUsoPunto")
    @Basic(optional = false) // not null
    @GeneratedValue(generator = "detUsopuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "detUsopuntoSec", sequenceName = "detUsopunto_sec", allocationSize = 0)
    private Integer idDetUsoPunto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_usoPunto", referencedColumnName = "id_usoPunto")
    private UsoPunto usoPunto;

    @Column(name = "puntaje_utilizado")
    @Basic(optional = false)
    private Integer puntajeUtilizado;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_bolsa_puntos", referencedColumnName = "id_bolsaPunto")
    private BolsaPunto bolsaPunto;

    public DetUsoPunto() {
    }

    public Integer getIdDetUsoPunto() {
        return idDetUsoPunto;
    }

    public void setIdDetUsoPunto(Integer idDetUsoPunto) {
        this.idDetUsoPunto = idDetUsoPunto;
    }

    public UsoPunto getUsoPunto() {
        return usoPunto;
    }

    public void setUsoPunto(UsoPunto usoPunto) {
        this.usoPunto = usoPunto;
    }

    public Integer getPuntajeUtilizado() {
        return puntajeUtilizado;
    }

    public void setPuntajeUtilizado(Integer puntajeUtilizado) {
        this.puntajeUtilizado = puntajeUtilizado;
    }

    public BolsaPunto getBolsaPunto() {
        return bolsaPunto;
    }

    public void setBolsaPunto(BolsaPunto bolsaPunto) {
        this.bolsaPunto = bolsaPunto;
    }

}
