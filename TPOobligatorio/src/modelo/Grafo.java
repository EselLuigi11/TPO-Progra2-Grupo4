package modelo;

import interfaces.IGrafo;
import interfaces.IIdentificable;
import interfaces.INodo;

import java.util.*;

public class Grafo<T extends IIdentificable> implements IGrafo<T> {
    private Map<Long, INodo<T>> nodos  = new HashMap<>();;
    @Override
    public void agregarNodo(T valor) {
        if(! nodos.containsKey(valor.obtenerClave())){
            nodos.put(valor.obtenerClave(),new Nodo<>(valor));
        }else{
            System.out.println("Ya existe en el grafo");
        }

    }

    @Override
    public void agregarArista(T origen, T destino) {
        if(nodos.containsKey(origen.obtenerClave()) && nodos.containsKey(destino.obtenerClave())){
            INodo<T> primero= nodos.get(origen.obtenerClave());
            INodo<T> segundo= nodos.get(destino.obtenerClave());
            primero.agregarVecino(segundo);
            segundo.agregarVecino(primero);
        }

    }

    @Override
    public void mostrarMatrizAdyacencia() {

    }

    @Override
    public void mostrarListaAdyacencia() {

    }

    @Override
    public void bfs(T inicio) {
        if (!nodos.containsKey(inicio.obtenerClave())) return; // precondición
        ///Lista y cola
        Set<Long> visitados = new HashSet<>(); // Conjunto de nodos visitados

        Queue<INodo<T>> cola = new LinkedList<>(); // Cola para el recorrido

        INodo<T> nodoInicio = nodos.get(inicio.obtenerClave());
        cola.add(nodoInicio);
        visitados.add(inicio.obtenerClave());

        System.out.println("Recorrido BFS:");
        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll();
            System.out.print(actual.getValor() + " ");

            for (INodo<T> vecino : actual.getVecinos()) {
                if (!visitados.contains(vecino.getValor().obtenerClave())) {
                    visitados.add(vecino.getValor().obtenerClave());
                    cola.add( vecino);
                }
            }
        }
        System.out.println();
    }


        @Override
        public void dfs(T inicio) {

        }
}
