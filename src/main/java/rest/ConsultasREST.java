package rest;

import ejb.ClienteDAO;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

public class ConsultasREST {

    @Inject
    private ClienteDAO clienteDAO;

    @GET
    @Path("/clientes-puntos-a-vencer/{dias}")
    public Response clientesPuntosAVencer(@PathParam(value="dias")Integer dias){
        return Response.ok(clienteDAO.clientesPuntosAVencer(dias)).build();
    }

}
