package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "vencimientoPunto")

public class VencimientoPunto {

    @Id
    @Column(name = "id_vencimientoPunto")
    @Basic(optional = false) // not null
    @GeneratedValue(generator = "vencimientoPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "vencimientoPuntoSec", sequenceName = "vencimientoPunto_sec", allocationSize = 0)
    private Integer idvencimientoPunto;

    @Column(name = "fechaInicioValidez")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicioValidez;

    @Column(name = "fechaFinValidez")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinValidez;

    @Column(name = "duracionDiasPuntaje")
    @Basic(optional = false)
    private Integer duracionDiasPuntaje;

//    @OneToOne(mappedBy = "Vencpunto")   // a que atributo de la clase ReglaPunto hace referencia para mapear
//    private ReglaPunto reglaPunto;

    public VencimientoPunto() {
    }

    public Integer getIdvencimientoPunto() {
        return idvencimientoPunto;
    }

    public void setIdvencimientoPunto(Integer idvencimientoPunto) {
        idvencimientoPunto = idvencimientoPunto;
    }

    public Date getFechaInicioValidez() {
            return fechaInicioValidez;
    }

    public void setFechaInicioValidez(Date fechaInicioValidez) {
        this.fechaInicioValidez = fechaInicioValidez;
    }

    public Date getFechaFinValidez() {
        return fechaFinValidez;
    }

    public void setFechaFinValidez(Date fechaFinValidez) {
        this.fechaFinValidez = fechaFinValidez;
    }

    public Integer getDuracionDiasPuntaje() {
        return duracionDiasPuntaje;
    }

    public void setDuracionDiasPuntaje(Integer duracionDiasPuntaje) {
        this.duracionDiasPuntaje = duracionDiasPuntaje;
    }

//    public ReglaPunto getReglaPunto() {
//        return reglaPunto;
//    }
//
//    public void setReglaPunto(ReglaPunto reglaPunto) {
//        this.reglaPunto = reglaPunto;
//    }

    public void merge (VencimientoPunto v){
        setFechaInicioValidez(v.getFechaInicioValidez());
        setFechaFinValidez(v.getFechaFinValidez());
        setDuracionDiasPuntaje(v.getDuracionDiasPuntaje());
    }
}
