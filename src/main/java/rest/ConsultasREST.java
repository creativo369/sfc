package rest;

import ejb.BolsaPuntoDAO;
import ejb.ClienteDAO;
import ejb.UsoPuntoDAO;
import model.Cliente;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Path("consultas")
@Consumes("application/json")
@Produces("application/json")
public class ConsultasREST {

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private BolsaPuntoDAO bolsaPuntoDAO;

    @Inject
    private UsoPuntoDAO usoPuntoDAO;

    @GET
    @Path("/clientes-puntos-a-vencer/{dias}")
    public Response clientesPuntosAVencer(@PathParam(value="dias")Integer dias){
        return Response.ok(clienteDAO.clientesPuntosAVencer(dias)).build();
    }

    @GET
    @Path("bolsas-de-puntos-por-cliente/{id_cliente}")
    public Response bolsasDePuntosPorCliente(@PathParam(value="id_cliente") Integer id_cliente){
        return Response.ok(bolsaPuntoDAO.listarBolsas(id_cliente)).build();
    }

    @GET
    @Path("listar-bolsas-por-rango/{a}/{b}")
    public Response listarBolsasPorRango(@PathParam(value="a") Integer a,
                                         @PathParam(value="b") Integer b){
        return Response.ok(bolsaPuntoDAO.listarPorRango(a, b)).build();
    }

    @GET
    @Path("uso-de-puntos-por-concepto/{id_concepto}")
    public Response listarUsoDePuntosPorConcepto(@PathParam(value="id_concepto")Integer id_concepto) {
        return Response.ok(usoPuntoDAO.obtenerPorConcepto(id_concepto)).build();
    }

    @GET
    @Path("uso-de-puntos-por-fecha/{dia}/{mes}/{anho}")
    public Response obtenerPorFechaUso(@PathParam(value = "dia")Integer dia,
                                       @PathParam(value = "mes")Integer mes,
                                       @PathParam(value = "anho")Integer anho) throws ParseException {
        System.out.println(dia);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return Response.ok(usoPuntoDAO.obtenerPorFechaUso(sdf.parse(dia + "/" + mes + "/" + anho))).build();
    }

    @GET
    @Path("uso-de-puntos-por-cliente/{id_cliente}")
    public Response obtenerPorCliente(@PathParam(value="id_cliente")Integer id_cliente) {
        return Response.ok(usoPuntoDAO.obtenerPorCliente(id_cliente)).build();
    }

}
