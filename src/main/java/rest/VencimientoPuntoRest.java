package rest;

import ejb.VencimientoPuntoDAO;
import model.VencimientoPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// una api para exponer nuestra entidad

@Path("caducidad")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class VencimientoPuntoRest {
    @Inject
    private VencimientoPuntoDAO caducidadDAO;


    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearVencimiento(VencimientoPunto v){
        this.caducidadDAO.nuevoVencimiento(v);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarVencimientos(){
        return Response.ok(caducidadDAO.listarVencimientos()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idvencimientoPunto}")
    public Response listarCaducidad(@PathParam(value="idvencimientoPunto") Integer idvencimientoPunto) {
        try {
            return Response.ok(caducidadDAO.obtenerVencimientobyId(idvencimientoPunto)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idvencimientoPunto}")
    public Response actualizarDatosVencimiento(@PathParam(value="idvencimientoPunto") Integer id, VencimientoPunto v){
        try {
            caducidadDAO.actualizarRegla(id, v);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idvencimientoPunto}")
    public Response borrarCaducidad(@PathParam(value = "idvencimientoPunto") Integer idvencimientoPunto){
        try{
            caducidadDAO.borrarCaducidadById(idvencimientoPunto);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
