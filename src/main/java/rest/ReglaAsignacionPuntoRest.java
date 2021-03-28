package rest;

import ejb.ReglaAsignacionPuntoDAO;
import model.ReglaAsignacionPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

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
        try {
            return Response.ok(reglaDAO.obtenerReglaAsignacionPuntoById(id)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idReglaAsignacionPunto}")
    public Response actualizarReglaAsignacionPunto(@PathParam(value="idReglaAsignacionPunto") Integer id, ReglaAsignacionPunto r){ // regla es el objeto nuevo que viene para actualizar
        try {                                                               // id es la el objeto que queremos actualizar
            reglaDAO.actualizarReglaAsignacionPunto(id, r);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idReglaAsignacionPunto}")
    public Response borrarPromo(@PathParam(value = "idReglaAsignacionPunto") Integer id){
        try{
            reglaDAO.borrarReglaAsignacionPuntoById(id);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
