package rest;


import ejb.ReglaAsignacionPuntoDAO;
import model.ReglaAsignacionPunto;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Path("servicios")
@Consumes("application/json")
@Produces("application/json")

public class ServiciosREST {
    @Inject
    private ReglaAsignacionPuntoDAO reglaDAO;



    @GET
    @Path("/EquivalenciaPuntoMonto/{m}")
    public Response equivalenciaPuntoMonto(@PathParam(value="m") Integer monto) {
        List<ReglaAsignacionPunto> reglas = reglaDAO.listarReglasAsignacionPunto();
        Map<String, String> respuesta = new HashMap<>();
        Response.ResponseBuilder builder = null;
        int cantPuntos=0;

        if (monto < reglaDAO.obtenerLimiteInferiorMenor() || monto > reglaDAO.obtenerLimiteSuperiorMayor()){
            respuesta.put("error", "No existe regla de asignacion de punto para este monto");
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }else{
            for (ReglaAsignacionPunto r : reglas) {
                if(monto <= r.getLimiteSuperior()){
                    cantPuntos = (int) Math.ceil( monto / r.getMontoEquivalencia());
                    respuesta.put("puntos", monto + " es equivalente a " + cantPuntos);
                    builder = Response.status(Response.Status.OK).entity(respuesta);
                    break;
                }
            }
        }

        return builder.build();
    }
}
