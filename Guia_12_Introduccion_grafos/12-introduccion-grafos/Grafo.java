import java.util.*;

public class Grafo {
    List<Vertice> vertices = new ArrayList<>();
    List<Arista> aristas = new ArrayList<>();

    void agregarVertice(String nombre) {
        vertices.add(new Vertice(nombre));
    }

    void agregarArista(String o, String d, double peso, boolean dirigida) {
        aristas.add(new Arista(o, d, peso, dirigida));
    }

    // Vecinos alcanzables desde un vertice (respeta la direccion de cada arista)
    List<String> vecinos(String v) {
        List<String> res = new ArrayList<>();
        for (Arista a : aristas) {
            if (a.origen.equals(v)) res.add(a.destino);
            else if (!a.dirigida && a.destino.equals(v)) res.add(a.origen);
        }
        return res;
    }

    // Grado de un vertice (solo para grafos no dirigidos)
    int grado(String v) {
        int g = 0;
        for (Arista a : aristas) {
            if (a.origen.equals(v) || a.destino.equals(v)) g++;
        }
        return g;
    }

    // Cantidad de vertices alcanzables desde 'inicio' (recorrido en anchura)
    int alcanzables(String inicio) {
        Set<String> visitados = new HashSet<>();
        Deque<String> cola = new ArrayDeque<>();
        cola.add(inicio);
        visitados.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.poll();
            for (String vecino : vecinos(actual)) {
                if (visitados.add(vecino)) cola.add(vecino);
            }
        }
        return visitados.size();
    }

    // Conexo: desde un vertice se llega a todos (en un grafo no dirigido)
    boolean esConexo() {
        return alcanzables(vertices.get(0).nombre) == vertices.size();
    }

    // Densidad = aristas / maximo de aristas posibles (no dirigido: n(n-1)/2)
    double densidad() {
        int n = vertices.size();
        return (double) aristas.size() / (n * (n - 1) / 2.0);
    }

    // Busca un ciclo simple con DFS (grafo no dirigido); devuelve el camino o null
    List<String> buscarCiclo() {
        Set<String> visitados = new HashSet<>();
        for (Vertice v : vertices) {
            if (!visitados.contains(v.nombre)) {
                List<String> camino = new ArrayList<>();
                List<String> ciclo = dfs(v.nombre, null, visitados, camino);
                if (ciclo != null) return ciclo;
            }
        }
        return null;
    }

    private List<String> dfs(String actual, String padre, Set<String> visitados, List<String> camino) {
        visitados.add(actual);
        camino.add(actual);
        for (String vecino : vecinos(actual)) {
            if (vecino.equals(padre)) continue;
            if (camino.contains(vecino)) {
                List<String> ciclo = new ArrayList<>(camino.subList(camino.indexOf(vecino), camino.size()));
                ciclo.add(vecino);
                return ciclo;
            }
            if (!visitados.contains(vecino)) {
                List<String> r = dfs(vecino, actual, visitados, camino);
                if (r != null) return r;
            }
        }
        camino.remove(camino.size() - 1);
        return null;
    }

    // Version dirigida: cada arista queda de origen -> destino (una sola direccion)
    Grafo aDirigido() {
        Grafo g = new Grafo();
        g.vertices.addAll(vertices);
        for (Arista a : aristas) {
            g.agregarArista(a.origen, a.destino, a.peso, true);
        }
        return g;
    }
}
