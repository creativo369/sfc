// Administrar la logica de negocio de la actividad de nuestra entidad Cliente
// ClienteDAO ( DAO: Data access Object )
package ejb;

import model.BolsaPunto;
import model.Cliente;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.DELETE;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class ClienteDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

//    @Inject
    // Por defecto el contenedor hace que esto sea transaccional: que si existiese un error no se comitee a la base de datos y se revierta la escritura

    @Inject
    private BolsaPuntoDAO bolsaPuntoDAO;



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

    public Cliente obtenerClienteById(Integer id) {
        Cliente c = this.em.find(Cliente.class, id);
        if (c == null) {
            return null;
        }
        return c;
    }

     /*
           --- Update ---
    */
    // Nose si hice lo correcto en actualizar pero es una idea interesante, simple y sencilla.
    public String actualizarClienteById(Integer id, Cliente cliente){
        // Primero vemos si esta en la base de datos para poder actualizar
        Cliente c = this.em.find(Cliente.class, id);
        if (  c == null) {
            return "-1";
        }else{
            c.merge(cliente);
        }
        return "1";
    }
    /*
           --- Delete ---
    */
    // Por lo visto el delete ya hace que sea transacional y que deje consiste la base de datos y commite los nuevos cambios
    public String borrarClienteById(Integer id){
            Cliente c = this.em.find(Cliente.class, id);
            if (c == null) {
                return "-1";
            }else{
                this.em.remove(c);
            }
        return "1";
    }

    /* Consulta de Clientes por parametro */

    public Object obtenerClientesPorParametro(String nombre, String apellido, String fechaNacimiento) {
        List<Cliente> clientes = null;
        Query q = null;
        if(nombre != null && !nombre.equals("")) {
            q = this.em.createQuery("select p from Cliente p where p.nombre like :param");
            q.setParameter("param", "%"+nombre+"%");
        } else if(apellido != null && !apellido.equals("")) {
            q = this.em.createQuery("select p from Cliente p where p.apellido like :param");
            q.setParameter("param", "%"+apellido+"%");
        } else if(fechaNacimiento != null && !fechaNacimiento.equals("")) {
            q = this.em.createQuery("select p from Cliente p where to_char(p.fechaNacimiento, 'MM-dd') like :param");
            q.setParameter("param", fechaNacimiento);
        } else {
            q = this.em.createQuery("select p from Cliente p");
        }
        clientes = (List<Cliente>) q.getResultList();
        return clientes;
    }

    public List<Cliente> clientesPuntosAVencer(Integer dias) {
        List<Cliente> listaClientes = new ArrayList<>();
        List<BolsaPunto> listaBolsas = bolsaPuntoDAO.listarTodoBP();
        Date hoy = new Date();
        for (BolsaPunto bolsa: listaBolsas) {
            if (getDifferenceDays(bolsa.getFechaCaducidadPuntaje(), hoy) <= dias){
                if (!listaClientes.contains(bolsa.getCliente())) {
                    listaClientes.add(bolsa.getCliente());
                }
            }
        }
        return listaClientes;
    }

    public static long getDifferenceDays(Date d1, Date d2) {
        long diff = d2.getTime() - d1.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }
}