// Administrar la logica de negocio de la actividad de nuestra entidad ConceptoPunto
// ConceptoPuntoDAO ( DAO: Data access Object )
package ejb;

import model.ReglaPunto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ReglaPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */
    public void nuevaRegla(ReglaPunto reglaPunto){
        //The persist operation can only be called within a transaction
        this.em.persist(reglaPunto);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<ReglaPunto> listarReglas(){
        Query q=this.em.createQuery( "select r from ReglaPunto r");
        List<ReglaPunto> listadoReglas = (List<ReglaPunto>) q.getResultList();
        return  listadoReglas;
    }

    /**
     * Busca una entidad conceptoPunto basado en su id.
     *
     * @param id_reglaPunto
     * @return ReglaPunto.
     * @throws EntityNotFoundException cuando la promo no se existe en nuestra base de datos.
     */
    public ReglaPunto obtenerReglabyId(Integer id_reglaPunto) {
        ReglaPunto regla = this.em.find(ReglaPunto.class, id_reglaPunto);
        if (regla == null) {
            throw new EntityNotFoundException("No se encuentra la regla con el ID "
                    + id_reglaPunto);
        }
        return regla;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public void actualizarRegla(Integer id_reglaPunto, ReglaPunto r){
        // Primero vemos si esta en la base de datos para poder actualizar
        ReglaPunto regla = this.em.find(ReglaPunto.class, id_reglaPunto);
        if (  regla == null) {
            throw new EntityNotFoundException("No se encuentra la regla con el ID " + id_reglaPunto);
        }else{
//            this.em.getTransaction().begin();
            regla.merge(r);
//            this.em.getTransaction().commit();
        }
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public void borrarReglaById(Integer id_reglaPunto){
//        this.em.getTransaction().begin();
            ReglaPunto regla = this.em.find(ReglaPunto.class, id_reglaPunto);
            if (regla == null) {
                throw new EntityNotFoundException("No se encuentra la regla con el ID " + id_reglaPunto);
            }else{
                this.em.remove(regla);
            }
//        this.em.getTransaction().commit();
    }
}