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
    public Response utilizacionPuntos(@PathParam(value="id_cliente") Integer id_cliente, @PathParam(value="id_concepto_uso")Integer id_concepto_uso){
        Map<String, String> respuesta = new HashMap<>();
        Response.ResponseBuilder builder = null;

        UsoPunto usoPunto = new UsoPunto();
        DetUsoPunto detUsoPunto = new DetUsoPunto();
        ConceptoUsoPunto concepto = this.conceptoUsoDao.obtenerConceptoUsoPuntoById(id_concepto_uso);
        Cliente cliente = clienteDAO.obtenerClienteById(id_cliente);
        List<BolsaPunto> listaBolsa = bolsaDAO.listarBolsas(id_cliente);

        int puntos_requeridos = concepto.getPuntoRequerido();
        int total_puntos_cliente = 0;

        for (BolsaPunto bolsa: listaBolsa) {
            total_puntos_cliente += bolsa.getPuntajeAsignado();
        }

        if (total_puntos_cliente - puntos_requeridos >= 0) {

            for (BolsaPunto bolsa: listaBolsa) {
                if (puntos_requeridos - bolsa.getSaldoPuntos()>= 0){
                    bolsa.setPuntajeUtilizado(bolsa.getPuntajeAsignado());
                    puntos_requeridos = puntos_requeridos - bolsa.getSaldoPuntos();
                    bolsa.setSaldoPuntos(0);
                    //CABECERA
                    usoPunto.setConceptoUsoPunto(concepto);
                    usoPunto.setCliente(cliente);
                    usoPunto.setPuntajeUtilizado(concepto.getPuntoRequerido());
                    usoPunto.setFechaUsoPunto(new Date());
                    //DETALLE
                    detUsoPunto.setUsoPunto(usoPunto);
                    detUsoPunto.setPuntajeUtilizado(concepto.getPuntoRequerido());
                    detUsoPunto.setBolsaPunto(bolsa);
                    this.detUsoPuntoDAO.crear(detUsoPunto);
                    this.usoPuntoDAO.crear(usoPunto);
                    this.bolsaDAO.actualizarBolsa(bolsa);
                    if (puntos_requeridos == 0){
                        break;
                    }
                }else { // aca se entra si necesitamos puntos restantes de otras bolsas
                    bolsa.setPuntajeUtilizado(bolsa.getPuntajeUtilizado() + puntos_requeridos);
                    bolsa.setSaldoPuntos(bolsa.getSaldoPuntos() - puntos_requeridos);
                    //CABECERA
                    usoPunto.setConceptoUsoPunto(concepto);
                    usoPunto.setCliente(cliente);
                    usoPunto.setPuntajeUtilizado(concepto.getPuntoRequerido());
                    usoPunto.setFechaUsoPunto(new Date());
                    //DETALLE
                    detUsoPunto.setUsoPunto(usoPunto);
                    detUsoPunto.setPuntajeUtilizado(concepto.getPuntoRequerido());
                    detUsoPunto.setBolsaPunto(bolsa);
                    puntos_requeridos = puntos_requeridos - bolsa.getSaldoPuntos();
                    this.detUsoPuntoDAO.crear(detUsoPunto);
                    this.usoPuntoDAO.crear(usoPunto);
                    this.bolsaDAO.actualizarBolsa(bolsa);
                    break;
                }
            }
            respuesta.put("exito", "Se activo " + concepto.getDescripcionConcepto() + " por " + concepto.getPuntoRequerido() + " puntos");
            builder = Response.status(Response.Status.OK).entity(respuesta);
        }else{
            respuesta.put("error", "No posee los suficientes puntos para canjear");
            builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(respuesta);
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
