//package ejb;
//
//
//import model.BolsaPunto;
//
//import javax.ejb.Schedule;
//import javax.ejb.Singleton;
//import javax.inject.Inject;
//import java.util.Date;
//import java.util.List;
//
//@Singleton
//public class ProcesoProgramadoDAO {
//
//    @Inject
//    private BolsaPuntoDAO bolsaPuntoDAO;
//
//    //cada 10 segundos a modo de prueba
////    @Schedule(hour = "*", minute = "*", second = "*/10", persistent = false)
//    //cada 2 horas
//    @Schedule(hour = "*/24", minute = "*", second = "*", persistent = false)
//    public void verificarEstadoPuntos() {
//        System.out.println("---Corriendo actualizacion de Bolsas de puntos --- ");
//        List<BolsaPunto> listaBolsas = bolsaPuntoDAO.listarTodoBP();
//        Date hoy = new Date();
//        for (BolsaPunto bolsa: listaBolsas) {
//            if (bolsa.getFechaCaducidadPuntaje().equals(hoy)){
//                bolsa.setSaldoPuntos(0);
//                bolsaPuntoDAO.actualizarBolsa(bolsa);
//            }
//        }
//    }
//}
