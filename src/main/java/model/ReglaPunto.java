package model;
package model.VencimientoPunto;


import javax.persistence.*;

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


    // Monto de equivalencia de 1 punto
    @OneToMany(fetch = FetchType.LAZY)
    @Basic(optional = false)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente" )
    /* 2 atributos
        name : el atributo que esta en la tabla BolsaPunto que referencia a la tabla cliente.
        referencedColumnName : al atributo o columna de la tabla cliente a la cual hace referencia.
    * */
    private VencimientoPunto idVencimiento;


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

    public Integer getIdVencimiento() {
        return idVencimiento;
    }

    public void setIdVencimiento(Integer idVencimiento) {
        this.idVencimiento = idVencimiento;
    }
    public void merge (ReglaPunto r){
        setLimite_inferior(r.limite_inferior);
        setLimite_superior(r.limite_superior);
        setIdVencimiento(r.idVencimiento);
        setMonto_equivalencia(r.monto_equivalencia);
    }

}
