// Administrar la logica de negocio de la actividad de nuestra entidad ConceptoPunto
// ConceptoPuntoDAO ( DAO: Data access Object )
package ejb;

import model.VencimientoPunto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class VencimientoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void nuevoVencimiento(VencimientoPunto vencimiento){
        //The persist operation can only be called within a transaction
        this.em.persist(vencimiento);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<VencimientoPunto> listarVencimientos(){
        Query q=this.em.createQuery( "select v from VencimientoPunto v");
        List<VencimientoPunto> listadoVencimiento = (List<VencimientoPunto>) q.getResultList();
        return  listadoVencimiento;
    }

    /**
     * Busca una entidad conceptoPunto basado en su id.
     *
     * @param id_vencimientoPunto
     * @return ReglaAsignacionPunto.
     * @throws EntityNotFoundException cuando la promo no se existe en nuestra base de datos.
     */
    public VencimientoPunto obtenerVencimientobyId(Integer id_vencimientoPunto) {
        VencimientoPunto vencimiento = this.em.find(VencimientoPunto.class, id_vencimientoPunto);
        if (vencimiento == null) {
            throw new EntityNotFoundException("No se encuentra la regla con el ID "
                    + id_vencimientoPunto);
        }
        return vencimiento;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public void actualizarRegla(Integer id_vencimientoPunto, VencimientoPunto v){
        // Primero vemos si esta en la base de datos para poder actualizar
        VencimientoPunto vencimiento = this.em.find(VencimientoPunto.class, id_vencimientoPunto);
        if (  vencimiento == null) {
            throw new EntityNotFoundException("No se encuentra la regla con el ID " + id_vencimientoPunto);
        }else{
//            this.em.getTransaction().begin();
            vencimiento.merge(v);
//            this.em.getTransaction().commit();
        }
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public void borrarCaducidadById(Integer id_vencimientoPunto){
//        this.em.getTransaction().begin();
            VencimientoPunto vencimiento = this.em.find(VencimientoPunto.class, id_vencimientoPunto);
            if (vencimiento == null) {
                throw new EntityNotFoundException("No se encuentra la regla con el ID " + id_vencimientoPunto);
            }else{
                this.em.remove(vencimiento);
            }
//        this.em.getTransaction().commit();
    }
}