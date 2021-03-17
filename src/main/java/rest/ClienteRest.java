package rest;

import javax.inject.Inject;
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

    // generar primero para obtener todas los clientes, consumiendo el metodo en ClienteDAO ( listarClientes<Cliente>)
    @GET
    @Path("/")
    public Response listarClientes(){
        return Response.ok(clienteDAO.listar()).build();
    }

    @POST
    @Path("/")
    public Response crearCliente(Cliente c){
        this.clienteDAO.agregar(c);
        return Response.ok().build();
    }
}
