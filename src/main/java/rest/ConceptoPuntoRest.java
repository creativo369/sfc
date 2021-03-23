package rest;

import ejb.ConceptoPuntoDAO;
import model.ConceptoPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

// una api para exponer nuestra entidad

@Path("promo")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class ConceptoPuntoRest {
    @Inject
    private ConceptoPuntoDAO promoDAO;


    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearPromo(ConceptoPunto promo){
        this.promoDAO.nuevaPromo(promo);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarPromos(){
        return Response.ok(promoDAO.listarPromos()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idconceptoPuntos}")
    public Response listarPromo(@PathParam(value="idconceptoPuntos") Integer idconceptoPuntos) {
        try {
            return Response.ok(promoDAO.obtenerPromobyId(idconceptoPuntos)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idconceptoPuntos}")
    public Response actualizarDatosPromo(@PathParam(value="idconceptoPuntos") Integer id, ConceptoPunto promo){
        try {
            promoDAO.actualizarPromo(id, promo);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idconceptoPuntos}")
    public Response borrarPromo(@PathParam(value = "idconceptoPuntos") Integer idconceptoPuntos){
        try{
            promoDAO.borrarPromoById(idconceptoPuntos);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
