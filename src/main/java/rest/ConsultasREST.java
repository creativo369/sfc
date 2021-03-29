package rest;

import ejb.ClienteDAO;
import model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("consultas")
@Consumes("application/json")
@Produces("application/json")

public class ConsultasREST {

    @Inject
    private ClienteDAO clienteDAO;

    @GET
    @Path("/clientes-puntos-a-vencer/{dias}")
    public Response clientesPuntosAVencer(@PathParam(value="dias")Integer dias){
        return Response.ok(clienteDAO.clientesPuntosAVencer(dias)).build();
    }
}
