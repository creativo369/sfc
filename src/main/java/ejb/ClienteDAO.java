// Administrar la logica de negocio de la actividad de nuestra entidad Cliente
// ClienteDAO ( DAO: Data access Object )
package ejb;

import model.Cliente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ClienteDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    /*
           --- Create ---
    */
    public void nuevoCliente(Cliente c){
        //The persist operation can only be called within a transaction
        this.em.persist(c);
    }
    /*
          --- Read ---
   */
    @SuppressWarnings("unchecked")
    public List<Cliente> listarClientes(){
        Query q=this.em.createQuery( "select c from Cliente c");
        List<Cliente> listadoClientes = (List<Cliente>) q.getResultList();
        return  listadoClientes;
    }

    /**
     * Busca una entidad cliente basado en su id.
     *
     * @param id_cliente
     * @return Cliente.
     * @throws EntityNotFoundException cuando el cliente no se encuentra.
     */
    public Cliente obtenerClientebyId(Integer id_cliente) {
        Cliente cliente = this.em.find(Cliente.class, id_cliente);
        if (cliente == null) {
            throw new EntityNotFoundException("No se puede encontrar al cliente con el ID "
                    + id_cliente);
        }
        return cliente;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public void actualizarCliente(Integer id_cliente, Cliente c){
        // Primero vemos si esta en la base de datos para poder actualizar
        Cliente cl = this.em.find(Cliente.class, id_cliente);
        if (  cl == null) {
            throw new EntityNotFoundException("No se puede encontrar al cliente con el ID " + id_cliente);
        }else{
//            this.em.getTransaction().begin();
            cl.merge(c);
//            this.em.getTransaction().commit();
        }
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public void borrarClienteById(Integer id_cliente){
//        this.em.getTransaction().begin();
            Cliente c = this.em.find(Cliente.class, id_cliente);
            if (c == null) {
                throw new EntityNotFoundException("No se puede encontrar al cliente con el ID " + id_cliente);
            }else{
                this.em.remove(c);
            }
//        this.em.getTransaction().commit();
    }
}