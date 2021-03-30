package rest;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import ejb.ClienteDAO;
import model.Cliente;

import java.util.HashMap;
import java.util.Map;

// una api para exponer nuestra entidad

@Path("clientes")
@Consumes("application/json")
@Produces("application/json")

// Tenemos que exponer nuestra capa de servicios que proveemos de nuestra entidad cliente que seria el DAO del ejb de Cliente
public class ClienteRest {
    @Inject
    private ClienteDAO clienteDAO;


    /*
           --- Create ---
    */
    @POST
    @Path("/")
    public Response crearCliente(Cliente c){
        this.clienteDAO.nuevoCliente(c);
        return Response.ok().build();
    }
    /*
          --- Read ---
   */
    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarClientes(){
        return Response.ok(clienteDAO.listarClientes()).build();
    }

    // No me cierra todavia si esta funcionando como tenia expectativa si no hay un cliente en la bd
    @GET
    @Path("/{idCliente}")
    public Response listarCliente(@PathParam(value="idCliente") Integer id) {
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        Cliente ans = clienteDAO.obtenerClienteById(id);
        if (ans == null){
            respuesta.put("error", "No se existe el cliente con el id "+id);
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
    @Path("/{idCliente}")
    public Response actualizarDatosCliente(@PathParam(value="idCliente") Integer id, Cliente c){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = clienteDAO.actualizarClienteById(id, c);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe el cliente con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Actualización exitosa", "Se han actualizados los datos del cliente con id "+id+" " +
                    "Nombre:" +clienteDAO.obtenerClienteById(id).getNombre()+
                    " Apellido:"+clienteDAO.obtenerClienteById(id).getApellido()+
                    " CI:"+clienteDAO.obtenerClienteById(id).getNumeroDocumento());
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idCliente}")
    public Response borrarCliente(@PathParam(value = "idCliente") Integer id){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();
        String ans = clienteDAO.borrarClienteById(id);
        if (ans.equals("-1")){
            respuesta.put("error", "No se existe el cliente con el id "+id);
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("Borrado exitoso", "Se borrado al cliente de la base de datos con id "+id);
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }
        return builder.build();
    }

    /* 7) Consultas (GET) :
           Este módulo contempla la consulta para el desarrollo de reportes.
           consulta de clientes por: nombre (aproximación), apellido (aproximación),
            cumpleaños
     */
    @GET
    @Path("/consulta")
    public Response consultaCliente(@QueryParam("nombre") String nombre,
                                    @QueryParam("apellido") String apellido,
                                    @QueryParam("fechaNacimiento") String fechaNacimiento) {
            return Response.ok(clienteDAO.obtenerClientesPorParametro(nombre, apellido, fechaNacimiento)).build();
    }
}
