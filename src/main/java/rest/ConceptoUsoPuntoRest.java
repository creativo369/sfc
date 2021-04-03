package rest;

import ejb.ConceptoUsoPuntoDAO;
import model.ConceptoUsoPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

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
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        ConceptoUsoPunto ans = conceptoUsoPuntoDAO.obtenerConceptoUsoPuntoById(id);
        if (ans == null){
            respuesta.put("error", "No se existe el concepto de uso punto con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            builder = Response.status(Response.Status.OK).entity(ans);
        }
        return builder.build();
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idConceptoUsoPunto}")
    public Response actualizarDatosConceptoUsoPuntoById(@PathParam(value="idConceptoUsoPunto") Integer id, ConceptoUsoPunto c){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = conceptoUsoPuntoDAO.actualizarConceptoUsoPuntoById(id, c);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe el cliente con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Actualización exitosa", "Se han actualizados los datos del concepto uso de punto con id "+id+
                    " Descripciòn:" +conceptoUsoPuntoDAO.obtenerConceptoUsoPuntoById(id).getDescripcionConcepto()+
                    " Puntos requeridos:"+conceptoUsoPuntoDAO.obtenerConceptoUsoPuntoById(id).getPuntoRequerido());
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idConceptoUsoPunto}")
    public Response borrarConceptoById(@PathParam(value = "idConceptoUsoPunto") Integer id){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = conceptoUsoPuntoDAO.borrarConceptoUsoPuntoById(id);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe el concepto de uso punto con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Borrado exitoso", "Se borrado el concepto de uso punto de la base de datos con id "+id);
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }

}
