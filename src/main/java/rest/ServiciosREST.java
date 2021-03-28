package rest;


import ejb.ReglaAsignacionPuntoDAO;
import model.ReglaAsignacionPunto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("servicios")
@Consumes("application/json")
@Produces("application/json")

public class ServiciosREST {
    @Inject
    private ReglaAsignacionPuntoDAO reglaDAO;


    @GET
    @Path("/EquivalenciaPuntoMonto/{m}")
    public Response equivalenciaPuntoMonto(@PathParam(value="m") Integer monto){
        List<ReglaAsignacionPunto> reglas = reglaDAO.listarReglasAsignacionPunto();
        int cantPuntos=0;
        for (ReglaAsignacionPunto r : reglas) {
            if(monto <= r.getLimiteSuperior()){
                cantPuntos = (int) Math.ceil( monto / r.getMontoEquivalencia());
                break;
            }
        }
        String respuesta = "El monto "+monto+" es equivalente a "+ cantPuntos+" puntos.";
        return Response.ok(respuesta).build();
    }
}
