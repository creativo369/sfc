package ejb;


import model.BolsaPunto;
import model.Cliente;
import model.ConceptoUsoPunto;
import model.UsoPunto;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import java.util.Date;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class UsoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    @Inject
    private ConceptoUsoPuntoDAO conceptoDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private BolsaPuntoDAO bolsaDAO;

    @Inject
    private DetUsoPuntoDAO detalleUsoPuntoDAO;

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

    public void Cabecera(Integer id_cliente, Integer id_concepto, Integer puntajeUtilizado, BolsaPunto bolsa){
        UsoPunto usoPunto = new UsoPunto();
        usoPunto.setCliente(clienteDAO.obtenerClienteById(id_cliente));
        usoPunto.setPuntajeUtilizado(puntajeUtilizado);
        usoPunto.setFechaUsoPunto(new Date());
        usoPunto.setConceptoUsoPunto(conceptoDAO.obtenerConceptoUsoPuntoById(id_concepto));
        this.em.persist(usoPunto);
        detalleUsoPuntoDAO.Detalle(usoPunto, puntajeUtilizado, bolsa);
    }

}
