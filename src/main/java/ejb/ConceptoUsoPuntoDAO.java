// Administrar la logica de negocio de la actividad de nuestra entidad ConceptoUsoPunto
// ConceptoUsoPuntoDAO ( DAO: Data access Object )
package ejb;

import model.ConceptoUsoPunto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ConceptoUsoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */
    public void nuevoConceptoUsoPunto(ConceptoUsoPunto c){
        //The persist operation can only be called within a transaction
        this.em.persist(c);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<ConceptoUsoPunto> listarConceptosUsoPunto(){
        Query q=this.em.createQuery( "select c from ConceptoUsoPunto c");
        List<ConceptoUsoPunto> listadoConceptoUsoPunto = (List<ConceptoUsoPunto>) q.getResultList();
        return  listadoConceptoUsoPunto;
    }

    public ConceptoUsoPunto obtenerConceptoUsoPuntoById(Integer id) {
        ConceptoUsoPunto c = this.em.find(ConceptoUsoPunto.class, id);
        if (c == null) {
            System.out.println("No se encuentra el concepto uso punto con la ID "
                    + id);
            return null;
        }
        return c;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public String actualizarConceptoUsoPuntoById(Integer id, ConceptoUsoPunto c){
        // Primero vemos si esta en la base de datos para poder actualizar
        ConceptoUsoPunto concepto = this.em.find(ConceptoUsoPunto.class, id);
        if (  concepto == null) {
            System.out.println("No se encuentra el concepto uso punto con la ID " + id);
            return "-1";
        }else{
//            this.em.getTransaction().begin();
            concepto.merge(c);
//            this.em.getTransaction().commit();
        }
        return "1";
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public String borrarConceptoUsoPuntoById(Integer id){
            ConceptoUsoPunto c = this.em.find(ConceptoUsoPunto.class, id);
            if (c == null) {
                System.out.println("No se encuentra el concepto uso punto con la ID " + id);
                return "-1";
            }else{
                this.em.remove(c);
            }
        return "1";
    }
}