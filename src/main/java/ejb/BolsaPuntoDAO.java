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
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class BolsaPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    private ClienteDAO clienteDAO;


    public void crearBolsa(BolsaPunto bolsa){
        //The persist operation can only be called within a transaction
        this.em.persist(bolsa);
    }

    public List<BolsaPunto> listarBolsas(int id_cliente){
        Cliente cliente = clienteDAO.obtenerClienteById(id_cliente);
        Query query = this.em.createQuery( "select b from BolsaPunto b");
        List<BolsaPunto> listaBolsas = (List<BolsaPunto>) query.getResultList();
        List<BolsaPunto> newLista = new ArrayList<>();
        for (BolsaPunto bolsa: listaBolsas) {
            if (bolsa.getCliente().equals(cliente)){
                newLista.add(bolsa);
            }
        }
        return newLista;
    }

    public List<BolsaPunto> listarTodoBP(){
        Query query = this.em.createQuery( "select b from BolsaPunto b");
        List<BolsaPunto> listaBolsas = (List<BolsaPunto>) query.getResultList();
        return listaBolsas;
    }

    public void eliminarBolsa(Integer id){
        BolsaPunto bolsa = this.em.find(BolsaPunto.class, id);
        if (bolsa == null) {
            throw new EntityNotFoundException("No se puede encontrar la bolsa con el ID " + id);
        }else{
            this.em.remove(bolsa);
        }
    }

//    public void actualizarBolsa(BolsaPunto bolsa){
//        this.merge(bolsa);
//    }
}
