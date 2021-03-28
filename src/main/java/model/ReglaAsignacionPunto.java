package model;

import javax.persistence.*;

@Entity
@Table(name ="reglaAsignacionPunto")

public class ReglaAsignacionPunto {
    @Id
    @Column(name ="id_reglaAsignacionPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "reglaAsignacionPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "reglaAsignacionPuntoSec", sequenceName = "reglaAsignacionPunto_sec", allocationSize = 0)
    private Integer idReglaAsignacionPunto;

    @Column(name = "limite_inferior")
    @Basic(optional = false)
    private Integer limiteInferior;

    @Column(name = "limite_superior")
    @Basic(optional = false)
    private Integer limiteSuperior;

    // Monto de equivalencia de 1 punto
    @Column(name = "monto_equivalencia")
    @Basic(optional = false)
    private Integer montoEquivalencia;


   // Unidirectional @OneToMany with @JoinColumn
   /*@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
   @JoinColumn(name = "id_vencimientoPunto")
   @OneToOne(cascade = CascadeType.ALL)
   @JoinColumn(name = "id_vencimiento", referencedColumnName = "id_vencimientoPunto")
   private List<VencimientoPunto> listaVencimientoPunto;
   private VencimientoPunto Vencpunto;*/



    public ReglaAsignacionPunto() {
    }

    public Integer getIdReglaAsignacionPunto() {
        return idReglaAsignacionPunto;
    }

    public void setIdReglaAsignacionPunto(Integer idReglaAsignacionPunto) {
        this.idReglaAsignacionPunto = idReglaAsignacionPunto;
    }

    public Integer getLimiteInferior() {
        return limiteInferior;
    }

    public void setLimiteInferior(Integer limiteInferior) {
        this.limiteInferior = limiteInferior;
    }

    public Integer getLimiteSuperior() {
        return limiteSuperior;
    }

    public void setLimiteSuperior(Integer limiteSuperior) {
        this.limiteSuperior = limiteSuperior;
    }

    public Integer getMontoEquivalencia() {
        return montoEquivalencia;
    }

    public void setMontoEquivalencia(Integer montoEquivalencia) {
        this.montoEquivalencia = montoEquivalencia;
    }

    public void merge (ReglaAsignacionPunto r){
        setLimiteInferior(r.getLimiteInferior());
        setLimiteSuperior(r.getLimiteSuperior());
        setMontoEquivalencia(r.getMontoEquivalencia());
    }

}
