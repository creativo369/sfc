package rest;

import ejb.ReglaPuntoDAO;
import model.ReglaPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// una api para exponer nuestra entidad

@Path("reglas")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class ReglaPuntoRest {
    @Inject
    private ReglaPuntoDAO reglaDAO;


    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearRegla(ReglaPunto regla){
        this.reglaDAO.nuevaRegla(regla);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarRules(){
        return Response.ok(reglaDAO.listarReglas()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idconceptoRegla}")
    public Response listarRegla(@PathParam(value="idconceptoRegla") Integer idconceptoRegla) {
        try {
            return Response.ok(reglaDAO.obtenerReglabyId(idconceptoRegla)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idconceptoRegla}")
    public Response actualizarDatosPromo(@PathParam(value="idconceptoRegla") Integer id, ReglaPunto regla){
        try {
            reglaDAO.actualizarRegla(id, regla);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idconceptoRegla}")
    public Response borrarPromo(@PathParam(value = "idconceptoRegla") Integer idconceptoRegla){
        try{
            reglaDAO.borrarReglaById(idconceptoRegla);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
