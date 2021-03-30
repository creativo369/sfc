package rest;


import ejb.*;
import model.*;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.*;

@Path("servicios")
@Consumes("application/json")
@Produces("application/json")

public class ServiciosREST {
    @Inject
    private ReglaAsignacionPuntoDAO reglaDAO;

    @Inject
    private ClienteDAO clienteDAO;

    @Inject
    private ParametrizacionVencimientoPuntoDAO paramVencDAO;

    @Inject
    private BolsaPuntoDAO bolsaDAO;

    @Inject
    private ConceptoUsoPuntoDAO conceptoUsoDao;

    @Inject
    private UsoPuntoDAO usoPuntoDAO;

    @Inject
    private DetUsoPuntoDAO detUsoPuntoDAO;

    @POST
    @Path("/carga-de-puntos/{id_cliente}/{monto}")
    public Response cargaPuntos(@PathParam("id_cliente") int id_cliente,@PathParam("monto") int monto ){
        Response.ResponseBuilder builder = null;
        BolsaPunto bolsa = new BolsaPunto();
        Map<String, String> respuesta = new HashMap<>();
        Date hoy = new Date();
        int puntos = this.equivalenciaPunto(monto);

        if (puntos != -1){
            bolsa.setCliente(clienteDAO.obtenerClienteById(id_cliente));
            bolsa.setFechaAsignacionPuntaje(hoy);
            bolsa.setPuntajeUtilizado(0);
            bolsa.setMontoOperacion(monto);
            bolsa.setPuntajeAsignado(puntos);
            bolsa.setSaldoPuntos(puntos);

            List<ParametrizacionVencimientoPunto> paramVencList = paramVencDAO.listarParametrizacionVencimientoPunto();

            for (ParametrizacionVencimientoPunto param: paramVencList) {
                if (hoy.compareTo(param.getFechaInicioValidez()) >= 0 && hoy.compareTo(param.getFechaFinValidez()) <= 0){
                    Calendar c = Calendar.getInstance();
                    c.setTime(hoy);
                    c.add(Calendar.DATE,  param.getDuracionDiasPuntaje());
                    bolsa.setFechaCaducidadPuntaje(c.getTime());
                }
            }

            this.bolsaDAO.crearBolsa(bolsa);

            respuesta.put("exito", "+" + puntos + " puntos cargados exitosamente");
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }else {
            respuesta.put("error", "No existe regla de asignacion de punto para este monto");
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        }

        return builder.build();
    }

    @POST
    @Path("/utilizacion-puntos/{id_cliente}/{id_concepto_uso}")
    public Response utilizacionPuntos(@PathParam("id_cliente")Integer id_cliente,
                                      @PathParam("id_concepto_uso")Integer id_concepto_uso){
        Response.ResponseBuilder builder = null;
        Map<String, String> respuesta = new HashMap<>();

        String ans = bolsaDAO.utilizacionPuntos(id_cliente, id_concepto_uso);
        if (ans.equals("-1")){
            respuesta.put("error", "No posee los suficientes puntos");
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("exito", ans);
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }

        return builder.build();
    }


    @GET
    @Path("/EquivalenciaPuntoMonto/{m}")
    public Response equivalenciaPuntoMonto(@PathParam(value="m") Integer monto) {
        Map<String, String> respuesta = new HashMap<>();
        Response.ResponseBuilder builder = null;
        int puntos = this.equivalenciaPunto(monto);

        if (puntos == -1) {
            respuesta.put("error", "No existe regla de asignacion de punto para este monto");
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
        } else {
            respuesta.put("puntos", monto + " es equivalente a " + puntos);
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }

        return builder.build();
    }

    public int equivalenciaPunto(Integer monto){
        List<ReglaAsignacionPunto> reglas = reglaDAO.listarReglasAsignacionPunto();
        int respuesta =0;
        if (monto < reglaDAO.obtenerLimiteInferiorMenor() || monto > reglaDAO.obtenerLimiteSuperiorMayor()){
            respuesta = -1;
        }else{
            for (ReglaAsignacionPunto r : reglas) {
                if(monto <= r.getLimiteSuperior()){
                    respuesta = (int) Math.ceil( monto / r.getMontoEquivalencia());
                    break;
                }
            }
        }
        return respuesta;
    }
}
