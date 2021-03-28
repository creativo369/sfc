package model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "parametrizacionVencimientoPunto")

public class ParametrizacionVencimientoPunto {

    @Id
    @Column(name = "id_parametrizacionVencimientoPunto")
    @Basic(optional = false) // not null
    @GeneratedValue(generator = "parametrizacionVencimientoPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "parametrizacionVencimientoPuntoSec", sequenceName = "parametrizacionVencimientoPunto_sec", allocationSize = 0)
    private Integer idparametrizacionVencimientoPunto;

    @Column(name = "fecha_inicio_validez")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaInicioValidez;

    @Column(name = "fecha_fin_Validez")
    @Basic(optional = false)
    @Temporal(TemporalType.DATE)
    private Date fechaFinValidez;

    @Column(name = "duracion_diasPuntaje")
    @Basic(optional = false)
    private Integer duracionDiasPuntaje;

    public ParametrizacionVencimientoPunto() {
    }

    public Integer getIdparametrizacionVencimientoPunto() {
        return idparametrizacionVencimientoPunto;
    }

    public void setIdparametrizacionVencimientoPunto(Integer idparametrizacionVencimientoPunto) {
        this.idparametrizacionVencimientoPunto = idparametrizacionVencimientoPunto;
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

    public void merge (ParametrizacionVencimientoPunto v){
        setFechaInicioValidez(v.getFechaInicioValidez());
        setFechaFinValidez(v.getFechaFinValidez());
        setDuracionDiasPuntaje(v.getDuracionDiasPuntaje());
    }
}
