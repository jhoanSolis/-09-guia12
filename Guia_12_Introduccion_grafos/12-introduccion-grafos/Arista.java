public class Arista {
    String origen;
    String destino;
    double peso;       // tiempo de viaje en minutos
    boolean dirigida;

    Arista(String o, String d, double p, boolean dir) {
        origen = o; destino = d; peso = p; dirigida = dir;
    }
}
