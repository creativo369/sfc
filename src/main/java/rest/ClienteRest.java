package rest;

import javax.inject.Inject;
import javax.persistence.EntityNotFoundException;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import ejb.ClienteDAO;
import model.Cliente;

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
        try {
            return Response.ok(clienteDAO.obtenerClienteById(id)).build();
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
            clienteDAO.actualizarClienteById(id, c);
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
    public Response borrarCliente(@PathParam(value = "idCliente") Integer id){
        try{
            clienteDAO.borrarClienteById(id);
            return Response.ok(null).build();
        }catch (EntityNotFoundException e){
            return Response.serverError().build();
        }
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
