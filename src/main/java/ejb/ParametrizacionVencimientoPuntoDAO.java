// Administrar la logica de negocio de la actividad de nuestra entidad ConceptoUsoPunto
// ConceptoUsoPuntoDAO ( DAO: Data access Object )
package ejb;

import model.ParametrizacionVencimientoPunto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ParametrizacionVencimientoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void nuevoParametrizacionVencimientoPunto(ParametrizacionVencimientoPunto v){
        //The persist operation can only be called within a transaction
        this.em.persist(v);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<ParametrizacionVencimientoPunto> listarParametrizacionVencimientoPunto(){
        Query q=this.em.createQuery( "select v from ParametrizacionVencimientoPunto v");
        List<ParametrizacionVencimientoPunto> listadoParametrizacionVencimientoPunto = (List<ParametrizacionVencimientoPunto>) q.getResultList();
        return  listadoParametrizacionVencimientoPunto;
    }


    public ParametrizacionVencimientoPunto obtenerParametrizacionVencimientoPuntoById(Integer id) {
        ParametrizacionVencimientoPunto vencimiento = this.em.find(ParametrizacionVencimientoPunto.class, id);
        if (vencimiento == null) {
            System.out.println("No se encuentra la Parametrizacion de Vencimiento punto con el ID " + id);
            return null;
        }
        return vencimiento;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public String actualizarParametrizacionVencimientoPuntoById(Integer id, ParametrizacionVencimientoPunto v){
        // Primero vemos si esta en la base de datos para poder actualizar
        ParametrizacionVencimientoPunto vencimiento = this.em.find(ParametrizacionVencimientoPunto.class, id);
        if (  vencimiento == null) {
            System.out.println("No se encuentra la Parametrizacion de Vencimiento punto con el ID " + id);
            return "-1";
        }else{
            vencimiento.merge(v);
        }
        return "1";
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public String borrarParametrizacionVencimientoPuntoById(Integer id){
        ParametrizacionVencimientoPunto vencimiento = this.em.find(ParametrizacionVencimientoPunto.class, id);

        if (vencimiento == null) {
            System.out.println("No se encuentra la Parametrizacion de Vencimiento punto con el ID " + id);
            return "-1";
        } else {
            this.em.remove(vencimiento);
        }
        return "1";
    }
}