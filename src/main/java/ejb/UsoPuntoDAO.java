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
import javax.persistence.Query;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless // No tiene estado, vamos a usar el ejb sin estado, es lo que se acostumbra.
public class UsoPuntoDAO {
    @PersistenceContext(unitName = "sfcPU")
    // The EntityManager.persist() operation is used to insert a new object into the database.
    private EntityManager em;  // Un objeto que nos permite administrar y manipular nuestras entidades y realiza el mapeo correspondiente en la base de datos

    @Inject
    private UsoPuntoDAO usoPuntoDAO;

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
    @SuppressWarnings("unchecked")
    public void Cabecera(Integer id_cliente, ConceptoUsoPunto concepto, Integer puntajeUtilizado, BolsaPunto bolsa){
        UsoPunto usoPunto = new UsoPunto();
        usoPunto.setCliente(clienteDAO.obtenerClienteById(id_cliente));
        usoPunto.setPuntajeUtilizado(puntajeUtilizado);
        usoPunto.setFechaUsoPunto(new Date());
        usoPunto.setConceptoUsoPunto(concepto);
        this.em.persist(usoPunto);
        detalleUsoPuntoDAO.Detalle(usoPunto, puntajeUtilizado, bolsa);
    }
    @SuppressWarnings("unchecked")
    public List<UsoPunto> listarUsoPunto(){
        Query query = this.em.createQuery("SELECT p FROM UsoPunto p");
        return (List<UsoPunto>) query.getResultList();
    }

    public List<UsoPunto> obtenerPorConcepto(Integer id_concepto){
        List<UsoPunto> listaUsoPunto = this.listarUsoPunto();
        List<UsoPunto> answerList = new ArrayList<>();
        for (UsoPunto usoPunto: listaUsoPunto) {
            if (usoPunto.getConceptoUsoPunto().getIdConceptoUsoPunto().equals(id_concepto))
                answerList.add(usoPunto);
        }
        return  answerList;
    }

    public List<UsoPunto> obtenerPorFechaUso(Date fecha){
        List<UsoPunto> listaUsoPunto = this.listarUsoPunto();
        List<UsoPunto> answerList = new ArrayList<>();
        for (UsoPunto usoPunto: listaUsoPunto) {
            if (usoPunto.getFechaUsoPunto().compareTo(fecha) == 0)
                answerList.add(usoPunto);
        }
        return  answerList;
    }

    public List<UsoPunto> obtenerPorCliente(Integer id_cliente){
        List<UsoPunto> listaUsoPunto = this.listarUsoPunto();
        List<UsoPunto> answerList = new ArrayList<>();
        for (UsoPunto usoPunto: listaUsoPunto) {
            if (usoPunto.getCliente().getIdCliente().equals(id_cliente))
                answerList.add(usoPunto);
        }
        return  answerList;
    }
}
