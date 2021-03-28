package rest;

import ejb.ConceptoUsoPuntoDAO;
import model.ConceptoUsoPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// una api para exponer nuestra entidad

@Path("conceptoUsoPunto")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class ConceptoUsoPuntoRest {
    @Inject
    private ConceptoUsoPuntoDAO conceptoUsoPuntoDAO;


    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearConcepto(ConceptoUsoPunto concepto){
        this.conceptoUsoPuntoDAO.nuevoConceptoUsoPunto(concepto);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarConceptos(){
        return Response.ok(conceptoUsoPuntoDAO.listarConceptosUsoPunto()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idConceptoUsoPunto}")
    public Response listarConceptoById(@PathParam(value="idConceptoUsoPunto") Integer id) {
        try {
            return Response.ok(conceptoUsoPuntoDAO.obtenerConceptoUsoPuntoById(id)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idConceptoUsoPunto}")
    public Response actualizarDatosConceptoUsoPuntoById(@PathParam(value="idConceptoUsoPunto") Integer id, ConceptoUsoPunto c){
        try {
            conceptoUsoPuntoDAO.actualizarConceptoUsoPuntoById(id, c);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idConceptoUsoPunto}")
    public Response borrarConceptoById(@PathParam(value = "idConceptoUsoPunto") Integer id){
        try{
            conceptoUsoPuntoDAO.borrarConceptoUsoPuntoById(id);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
