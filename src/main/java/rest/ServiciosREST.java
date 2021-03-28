package rest;


import ejb.ClienteDAO;
import ejb.ReglaPuntoDAO;
import model.Cliente;
import model.ReglaPunto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("servicios")
@Consumes("application/json")
@Produces("application/json")

public class ServiciosREST {
    @Inject
    private ReglaPuntoDAO reglasDAO;


    @GET
    @Path("/consultasEquivalente/{monto}")
    public Response consultaPuntosEquivalente(@PathParam(value="monto") Integer monto){
        List<ReglaPunto> reglas = reglasDAO.listarReglas();
        int cantPuntos=0;
        for (ReglaPunto r : reglas) {
            if(monto <= r.getLimite_superior()){
                cantPuntos = (int) Math.ceil( monto / r.getMonto_equivalencia());
                break;
            }
        }
        String respuesta = "El monto "+monto+" es equivalente a "+ cantPuntos+" puntos.";
        return Response.ok(respuesta).build();
    }
}
