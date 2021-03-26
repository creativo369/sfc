package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name ="reglaPunto")

public class ReglaPunto {
    @Id
    @Column(name ="id_reglaPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "reglaPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reglaPuntoSec", sequenceName = "reglaPunto_sec", allocationSize = 0)
    private Integer idReglaPunto;

    @Column(name = "limite_inferior")
    @Basic(optional = false)
    private Integer limite_inferior;

    @Column(name = "limite_superior")
    @Basic(optional = false)
    private Integer limite_superior;

    // Monto de equivalencia de 1 punto
    @Column(name = "monto_equivalencia")
    @Basic(optional = false)
    private Integer monto_equivalencia;


    // Unidirectional @OneToMany with @JoinColumn
    /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "id_vencimientoPunto")*/
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vencimiento", referencedColumnName = "id_vencimientoPunto")
//    private List<VencimientoPunto> listaVencimientoPunto;
    private VencimientoPunto Vencpunto;



    public ReglaPunto() {
    }

    public Integer getIdReglaPunto() {
        return idReglaPunto;
    }

    public void setIdReglaPunto(Integer idReglaPunto) {
        this.idReglaPunto = idReglaPunto;
    }

    public Integer getLimite_inferior() {
        return limite_inferior;
    }

    public void setLimite_inferior(Integer limite_inferior) {
        this.limite_inferior = limite_inferior;
    }

    public Integer getLimite_superior() {
        return limite_superior;
    }

    public void setLimite_superior(Integer limite_superior) {
        this.limite_superior = limite_superior;
    }

    public Integer getMonto_equivalencia() {
        return monto_equivalencia;
    }

    public void setMonto_equivalencia(Integer monto_equivalencia) {
        this.monto_equivalencia = monto_equivalencia;
    }

    public VencimientoPunto getVencpunto() {
        return Vencpunto;
    }

    public void setVencpunto(VencimientoPunto vencpunto) {
        Vencpunto = vencpunto;
    }

    public void merge (ReglaPunto r){
        setLimite_inferior(r.getLimite_inferior());
        setLimite_superior(r.getLimite_superior());
        setMonto_equivalencia(r.getMonto_equivalencia());
        VencimientoPunto v = r.getVencpunto(); // Obtengo la nueva fechas para actualizar
        VencimientoPunto v2 = getVencpunto(); // las fechas antiguas por el cual cambiar
        v2.setFechaInicioValidez(v.getFechaInicioValidez());
        v2.setFechaFinValidez(v.getFechaFinValidez());
        v2.setDuracionDiasPuntaje(v.getDuracionDiasPuntaje());
        //setVencpunto(r.getVencpunto());
    }

}
