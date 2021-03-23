// Administrar la logica de negocio de la actividad de nuestra entidad ConceptoPunto
// ConceptoPuntoDAO ( DAO: Data access Object )
package ejb;

import model.ConceptoPunto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ConceptoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */
    public void nuevaPromo(ConceptoPunto promo){
        //The persist operation can only be called within a transaction
        this.em.persist(promo);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<ConceptoPunto> listarPromos(){
        Query q=this.em.createQuery( "select c from ConceptoPunto c");
        List<ConceptoPunto> listadoPromos = (List<ConceptoPunto>) q.getResultList();
        return  listadoPromos;
    }

    /**
     * Busca una entidad conceptoPunto basado en su id.
     *
     * @param id_conceptoPunto
     * @return ConceptoPunto.
     * @throws EntityNotFoundException cuando la promo no se existe en nuestra base de datos.
     */
    public ConceptoPunto obtenerPromobyId(Integer id_conceptoPunto) {
        ConceptoPunto promo = this.em.find(ConceptoPunto.class, id_conceptoPunto);
        if (promo == null) {
            throw new EntityNotFoundException("No se encuentra la promo con la ID "
                    + id_conceptoPunto);
        }
        return promo;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public void actualizarPromo(Integer id_conceptoPunto, ConceptoPunto p){
        // Primero vemos si esta en la base de datos para poder actualizar
        ConceptoPunto promo = this.em.find(ConceptoPunto.class, id_conceptoPunto);
        if (  promo == null) {
            throw new EntityNotFoundException("No se encuentra la promo con la ID " + id_conceptoPunto);
        }else{
//            this.em.getTransaction().begin();
            promo.merge(p);
//            this.em.getTransaction().commit();
        }
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public void borrarPromoById(Integer id_conceptoPunto){
//        this.em.getTransaction().begin();
            ConceptoPunto promo = this.em.find(ConceptoPunto.class, id_conceptoPunto);
            if (promo == null) {
                throw new EntityNotFoundException("No se encuentra la promo con la ID " + id_conceptoPunto);
            }else{
                this.em.remove(promo);
            }
//        this.em.getTransaction().commit();
    }
}