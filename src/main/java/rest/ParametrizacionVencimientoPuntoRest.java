package rest;

import ejb.ParametrizacionVencimientoPuntoDAO;
import model.ParametrizacionVencimientoPunto;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

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
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        ParametrizacionVencimientoPunto ans = vencimientoDAO.obtenerParametrizacionVencimientoPuntoById(id);

        if (ans == null){
            respuesta.put("error", "No se existe el Parametro de Vencimiento con el id "+id);
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
    @Path("/{idParametrizacionVencimientoPunto}")
    public Response actualizarDatosVencimiento(@PathParam(value="idParametrizacionVencimientoPunto") Integer id, ParametrizacionVencimientoPunto v){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = vencimientoDAO.actualizarParametrizacionVencimientoPuntoById(id, v);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe la Parametrizacion de Vencimiento con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Actualización exitosa", "Se han actualizados los datos del parametro" +
                    "ID:" +vencimientoDAO.obtenerParametrizacionVencimientoPuntoById(id).getIdparametrizacionVencimientoPunto()+
                    " Fecha Inicio Validez:"+vencimientoDAO.obtenerParametrizacionVencimientoPuntoById(id).getFechaInicioValidez()+
                    " Fecha Fin Validez:"+vencimientoDAO.obtenerParametrizacionVencimientoPuntoById(id).getFechaFinValidez()+
                    " Días de duración:"+vencimientoDAO.obtenerParametrizacionVencimientoPuntoById(id).getDuracionDiasPuntaje());
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idParametrizacionVencimientoPunto}")
    public Response borrarParametrizacionVencimientoPunto(@PathParam(value = "idParametrizacionVencimientoPunto") Integer id){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = vencimientoDAO.borrarParametrizacionVencimientoPuntoById(id);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe la Parametrizaciòn de Vencimiento con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Borrado exitoso", "Se borrado el Parametro de Vencimiento de la base de datos con id "+id);
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }

}
