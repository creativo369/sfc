package ejb;


import model.Cliente;
import model.UsoPunto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class UsoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    public void crear(UsoPunto u) {
        //The persist operation can only be called within a transaction
        this.em.persist(u);
    }

    public UsoPunto obtenerUsoPunto(Integer id) {
        UsoPunto usoPunto = this.em.find(UsoPunto.class, id);
        if (usoPunto == null) {
            throw new EntityNotFoundException("No se puede encontrar al cliente con el ID " + id);
        }
        return usoPunto;
    }

//    public List<UsoPunto> listaPorConcepto(Integer id){
//       UsoPunto u = this.obtenerUsoPunto(id);
//
//
//
//        return listaUsoPunto;
//    }

}
