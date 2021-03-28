// Administrar la logica de negocio de la actividad de nuestra entidad ConceptoUsoPunto
// ConceptoUsoPuntoDAO ( DAO: Data access Object )
package ejb;

import model.ReglaAsignacionPunto;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ReglaAsignacionPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */

    public void nuevaReglaAsignacionPunto(ReglaAsignacionPunto r){
        //The persist operation can only be called within a transaction
        this.em.persist(r);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<ReglaAsignacionPunto> listarReglasAsignacionPunto(){
        Query q=this.em.createQuery( "select r from ReglaAsignacionPunto r");
        List<ReglaAsignacionPunto> listadoReglasAsignacion = (List<ReglaAsignacionPunto>) q.getResultList();
        return  listadoReglasAsignacion;
    }

    // Obtener una regla de asignacion de puntos por id
    public ReglaAsignacionPunto obtenerReglaAsignacionPuntoById(Integer id) {
        ReglaAsignacionPunto r = this.em.find(ReglaAsignacionPunto.class, id);
        if (r == null) {
            throw new EntityNotFoundException("No se encuentra la regla de asignacion de punto con el ID "
                    + id);
        }
        return r;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public void actualizarReglaAsignacionPunto(Integer id, ReglaAsignacionPunto r){
        // Primero vemos si esta en la base de datos para poder actualizar
        ReglaAsignacionPunto regla = this.em.find(ReglaAsignacionPunto.class, id);
        if (  regla == null) {
            throw new EntityNotFoundException("No se encuentra la regla de asignacion de punto con el ID " + id);
        }else{
            regla.merge(r); // Actualizamos la "regla" existente con nuevos parametros de una regla "r" viniendo de json
        }
    }

    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public void borrarReglaAsignacionPuntoById(Integer id){
            ReglaAsignacionPunto regla = this.em.find(ReglaAsignacionPunto.class, id);
            if (regla == null) {
                throw new EntityNotFoundException("No se encuentra la regla de asignacion de punto con el ID " + id);
            }else{
                this.em.remove(regla);
            }
    }

    public int obtenerLimiteInferiorMenor(){
        Query query = this.em.createQuery( "select r from ReglaAsignacionPunto r");
        List<ReglaAsignacionPunto> listadoReglasAsignacion = (List<ReglaAsignacionPunto>) query.getResultList();
        int menor = 999999999;
        for (ReglaAsignacionPunto r : listadoReglasAsignacion) {
            if (r.getLimiteInferior() < menor){
                menor = r.getLimiteInferior();
            }
        }
        return menor;
    }

    public int obtenerLimiteSuperiorMayor(){
        Query query = this.em.createQuery( "select r from ReglaAsignacionPunto r");
        List<ReglaAsignacionPunto> listadoReglasAsignacion = (List<ReglaAsignacionPunto>) query.getResultList();
        int mayor = 0;
        for (ReglaAsignacionPunto r : listadoReglasAsignacion) {
            if (r.getLimiteInferior() > mayor){
                mayor = r.getLimiteInferior();
            }
        }
        return mayor;
    }

}