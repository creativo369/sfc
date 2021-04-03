package rest;

import ejb.ReglaAsignacionPuntoDAO;
import model.ReglaAsignacionPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

// una api para exponer nuestra entidad

@Path("reglaAsignacionPunto")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class ReglaAsignacionPuntoRest {
    @Inject
    private ReglaAsignacionPuntoDAO reglaDAO;

    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearRegla(ReglaAsignacionPunto r){
        this.reglaDAO.nuevaReglaAsignacionPunto(r);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarReglas(){
        return Response.ok(reglaDAO.listarReglasAsignacionPunto()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idReglaAsignacionPunto}")
    public Response listarRegla(@PathParam(value="idReglaAsignacionPunto") Integer id) {
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        ReglaAsignacionPunto ans = reglaDAO.obtenerReglaAsignacionPuntoById(id);
        if (ans == null){
            respuesta.put("error", "No existe la regla de asignacion de punto con el ID " + id);
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
    @Path("/{idReglaAsignacionPunto}")
    public Response actualizarReglaAsignacionPunto(@PathParam(value="idReglaAsignacionPunto") Integer id, ReglaAsignacionPunto r){ // regla es el objeto nuevo que viene para actualizar
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans =  reglaDAO.actualizarReglaAsignacionPunto(id, r);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe la regla de asignacion con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Actualización exitosa", "Se han actualizados los datos de la regla de asignacion con id "+id+" " +
                    "Limite inferior:" +reglaDAO.obtenerReglaAsignacionPuntoById(id).getLimiteInferior()+
                    " Limite superior:"+reglaDAO.obtenerReglaAsignacionPuntoById(id).getLimiteSuperior()+
                    " Monto equivalencia:"+reglaDAO.obtenerReglaAsignacionPuntoById(id).getMontoEquivalencia());
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idReglaAsignacionPunto}")
    public Response borrarPromo(@PathParam(value = "idReglaAsignacionPunto") Integer id){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = reglaDAO.borrarReglaAsignacionPuntoById(id);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe la regla de asignacion de punto con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Borrado exitoso", "Se borrado  la regla de asignacion de punto de la base de datos con id "+id);
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }

}
