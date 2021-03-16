package model;

import javax.persistence.*;

id autogenerado, identificador del cliente, fecha de asignación de puntaje, fecha
        de caducidad de puntaje, puntaje asignado, puntaje utilizado, saldo de puntos, monto de la
        operación


@Entity
@Table(name = "bolsaPunto")

public class BolsaPunto {

    @Id
    @Column(name ="id_bolsaPunto")
    @Basic(optional = false)
    @GeneratedValue(generator = "bolsaPuntoSec", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "bolsaPuntoSec", sequenceName = "bolsaPunto_sec", allocationSize = 0)
    private Integer idBolsaPunto;

    // Dueño de la relación o owner, por tener la referencia al campo de la relación a la tabla Cliente
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_cliente", referencedColumnName = "id_cliente" )
    /* 2 atributos
        name : el atributo que esta en la tabla BolsaPunto que referencia a la tabla cliente.
        referencedColumnName : al atributo o columna de la tabla cliente a la cual hace referencia.
    * */
    private Cliente cliente;


}
