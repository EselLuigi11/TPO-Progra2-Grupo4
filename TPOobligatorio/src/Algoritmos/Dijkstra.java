package Algoritmos;

import interfaces.INodo;
import modelo.Grafo;

//Calcula las distancias mínimas desde un nodo origen hacia todos los demás.

public class Dijkstra<T extends interfaces.IIdentificable> {

// Se ejecuta el algoritmo sobre un grafo ponderado.
//grafo El grafo sobre el cual se ejecuta el algoritmo.
//origen El nodo de inicio.
//pesos Un mapa que contiene los pesos de las aristas (clave: par de nodos, valor: peso).

    public void ejecutar(Grafo<T> grafo, T origen, Map<String, Integer> pesos) {


        Map<Long, INodo<T>> nodos = obtenerMapaNodos(grafo);

        Map<Long, Integer> distancias = new HashMap<>();

        // 🔹 Conjunto de nodos ya visitados
        Set<Long> visitados = new HashSet<>();

        // 🔹 Cola de prioridad (elige siempre el nodo con menor distancia)
        PriorityQueue<NodoDistancia> cola = new PriorityQueue<>();

        // Inicializar todas las distancias como "infinito"
        for (Long id : nodos.keySet()) {
            distancias.put(id, Integer.MAX_VALUE);
        }

        // Distancia del origen = 0
        distancias.put(origen.obtenerClave(), 0);
        cola.add(new NodoDistancia(origen.obtenerClave(), 0));


        while (!cola.isEmpty()) {
            NodoDistancia actual = cola.poll();

            // Si ya fue visitado, lo salteamos
            if (visitados.contains(actual.id)) continue;
            visitados.add(actual.id);

            INodo<T> nodoActual = nodos.get(actual.id);

            // Iterar sobre sus vecinos
            for (INodo<T> vecino : nodoActual.getVecinos()) {
                Long idVecino = vecino.getValor().obtenerClave();

                // Obtenemos el peso (si no está en el mapa, asumimos peso 1)
                String clavePeso = generarClaveArista(actual.id, idVecino);
                int peso = pesos.getOrDefault(clavePeso, 1);

                int nuevaDist = distancias.get(actual.id) + peso;

                if (nuevaDist < distancias.get(idVecino)) {
                    distancias.put(idVecino, nuevaDist);
                    cola.add(new NodoDistancia(idVecino, nuevaDist));
                }
            }
        }

        System.out.println("\n📍 Distancias mínimas desde " + origen + ":");
        for (Map.Entry<Long, Integer> entry : distancias.entrySet()) {
            System.out.println("→ " + entry.getKey() + " = " +
                    (entry.getValue() == Integer.MAX_VALUE ? "∞" : entry.getValue()));
        }
    }

//Convierte el campo privado "nodos" de Grafo en un mapa accesible.

    private Map<Long, INodo<T>> obtenerMapaNodos(Grafo<T> grafo) {
        try {
            var field = Grafo.class.getDeclaredField("nodos");
            field.setAccessible(true);
            return (Map<Long, INodo<T>>) field.get(grafo);
        } catch (Exception e) {
            throw new RuntimeException("No se pudo acceder a los nodos del grafo", e);
        }
    }

//Genera una clave única para una arista (ej: "1-2" o "2-1" si es no dirigido).

    private String generarClaveArista(Long a, Long b) {
        return a < b ? a + "-" + b : b + "-" + a;
    }


    private static class NodoDistancia implements Comparable<NodoDistancia> {
        long id;
        int distancia;

        NodoDistancia(long id, int distancia) {
            this.id = id;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(NodoDistancia otro) {
            return Integer.compare(this.distancia, otro.distancia);
        }
    }
}
