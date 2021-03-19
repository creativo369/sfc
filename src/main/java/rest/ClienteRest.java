package rest;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import ejb.ClienteDAO;
import model.Cliente;

// una api para exponer nuestra entidad

@Path("cliente")
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
    public Response crearC(Cliente c){
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
    public Response listarCliente(@PathParam(value="idCliente") Integer idCliente) {
        try {
            return Response.ok(clienteDAO.obtenerClientebyId(idCliente)).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
     /*
           --- Update ---
    */
    @PUT
    @Path("/{idCliente}")
    public Response actualizarDatosCliente(@PathParam(value="idCliente") Integer id, Cliente c){
        try {
            clienteDAO.actualizarCliente(id, c);
            return Response.ok().build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }
    /*
           --- Delete ---
    */
    @DELETE
    @Path("/{idCliente}")
    public Response borrarCliente(@PathParam(value = "idCliente") Integer idCliente){
        try{
            clienteDAO.borrarClienteById(idCliente);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
    }

}
