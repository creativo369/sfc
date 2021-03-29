package ejb;

import model.Cliente;
import model.DetUsoPunto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class DetUsoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    public void crear(DetUsoPunto d){
        //The persist operation can only be called within a transaction
        this.em.persist(d);
    }
}
