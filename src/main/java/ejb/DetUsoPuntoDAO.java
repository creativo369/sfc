package ejb;

import model.BolsaPunto;
import model.Cliente;
import model.DetUsoPunto;
import model.UsoPunto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class DetUsoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    @Inject
    private UsoPuntoDAO usoPuntoDAO;

    public void crear(DetUsoPunto d){
        //The persist operation can only be called within a transaction
        this.em.persist(d);
    }

    public void Detalle(UsoPunto usoPunto, Integer puntajeUtilizado, BolsaPunto bolsa){
        DetUsoPunto detalle = new DetUsoPunto();
        detalle.setUsoPunto(usoPunto);
        detalle.setPuntajeUtilizado(puntajeUtilizado);
        detalle.setBolsaPunto(bolsa);
        this.em.persist(detalle);
    }

}
