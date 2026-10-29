public class Main {
    public static void main(String[] args) {
        Grafo g = new Grafo();

        String[] estaciones = {
            "Portal Norte", "Calle 100", "Calle 72", "Calle 45", "Centro",
            "Museo", "Universidad", "Aeropuerto", "Portal Sur", "Portal Occidente"
        };
        for (String e : estaciones) g.agregarVertice(e);

        // origen, destino, minutos, dirigida (false = se puede viajar en ambos sentidos)
        g.agregarArista("Portal Norte", "Calle 100", 8, false);
        g.agregarArista("Calle 100", "Calle 72", 6, false);
        g.agregarArista("Calle 72", "Calle 45", 5, false);
        g.agregarArista("Calle 45", "Centro", 7, false);
        g.agregarArista("Centro", "Portal Sur", 12, false);
        g.agregarArista("Centro", "Museo", 4, false);
        g.agregarArista("Museo", "Universidad", 5, false);
        g.agregarArista("Universidad", "Calle 45", 6, false);
        g.agregarArista("Calle 72", "Universidad", 9, false);
        g.agregarArista("Calle 100", "Aeropuerto", 14, false);
        g.agregarArista("Aeropuerto", "Portal Occidente", 10, false);
        g.agregarArista("Portal Occidente", "Centro", 16, false);
        g.agregarArista("Portal Occidente", "Portal Sur", 13, false);
        g.agregarArista("Aeropuerto", "Calle 72", 11, false);
        g.agregarArista("Museo", "Portal Sur", 10, false);
        g.agregarArista("Portal Norte", "Aeropuerto", 18, false);

        System.out.println("=== TABLA DE VERTICES ===");
        System.out.printf("%-18s %s%n", "Vertice", "Grado");
        for (Vertice v : g.vertices) {
            System.out.printf("%-18s %d%n", v.nombre, g.grado(v.nombre));
        }

        System.out.println("\n=== TABLA DE ARISTAS ===");
        System.out.printf("%-3s %-18s %-18s %-8s %-9s%n", "#", "Origen", "Destino", "Peso", "Dirigida");
        int i = 1;
        for (Arista a : g.aristas) {
            System.out.printf("%-3d %-18s %-18s %-8.0f %-9s%n", i++, a.origen, a.destino, a.peso, a.dirigida ? "Si" : "No");
        }

        System.out.println("\n=== CLASIFICACION ===");
        System.out.println("Vertices |V| = " + g.vertices.size());
        System.out.println("Aristas  |A| = " + g.aristas.size());
        System.out.println("Conexo: " + g.esConexo());
        System.out.printf("Densidad: %.3f (%d de %d aristas posibles)%n", g.densidad(), g.aristas.size(), 10 * 9 / 2);
        System.out.println("Ciclo encontrado: " + g.buscarCiclo());

        System.out.println("\n=== EJERCICIO: VERSION DIRIGIDA ===");
        Grafo d = g.aDirigido();
        System.out.println("Desde Portal Norte se llega a " + d.alcanzables("Portal Norte") + " de 10 estaciones");
        System.out.println("Desde Portal Sur se llega a " + d.alcanzables("Portal Sur") + " de 10 estaciones");
        System.out.println("Desde Portal Occidente se llega a " + d.alcanzables("Portal Occidente") + " de 10 estaciones");
    }
}
