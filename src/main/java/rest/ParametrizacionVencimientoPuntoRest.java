package rest;

import ejb.ParametrizacionVencimientoPuntoDAO;
import model.ParametrizacionVencimientoPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// una api para exponer nuestra entidad

@Path("parametrizacionVencimientoPunto")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class ParametrizacionVencimientoPuntoRest {
    @Inject
    private ParametrizacionVencimientoPuntoDAO vencimientoDAO;


    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearParametrizacionVencimientoPunto(ParametrizacionVencimientoPunto v){
        this.vencimientoDAO.nuevoParametrizacionVencimientoPunto(v);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarParametrizacionVencimientosPuntos(){
        return Response.ok(vencimientoDAO.listarParametrizacionVencimientoPunto()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idParametrizacionVencimientoPunto}")
    public Response listarCaducidad(@PathParam(value="idParametrizacionVencimientoPunto") Integer id) {
        try {
            return Response.ok(vencimientoDAO.obtenerParametrizacionVencimientoPuntoById(id)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idParametrizacionVencimientoPunto}")
    public Response actualizarDatosVencimiento(@PathParam(value="idParametrizacionVencimientoPunto") Integer id, ParametrizacionVencimientoPunto v){
        try {
            vencimientoDAO.actualizarParametrizacionVencimientoPuntoById(id, v);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idParametrizacionVencimientoPunto}")
    public Response borrarParametrizacionVencimientoPunto(@PathParam(value = "idParametrizacionVencimientoPunto") Integer id){
        try{
            vencimientoDAO.borrarParametrizacionVencimientoPuntoById(id);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
