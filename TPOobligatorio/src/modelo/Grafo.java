package modelo;

import interfaces.IGrafo;
import interfaces.IIdentificable;
import interfaces.INodo;

import java.util.*;

public class Grafo<T extends IIdentificable> implements IGrafo<T> {
    private Map<Long, INodo<T>> nodos  = new HashMap<>(); //Map que asocia una clave Long (probablemente la clave devuelta por IIdentificable.obtenerClave()) a un INodo<T>
    													  //Se inicializa con un HashMap. Almacena y recupera datos de manera eficiente utilizando pares de clave-valor.
    
    @Override
    public void agregarNodo(T valor) {
        if(! nodos.containsKey(valor.obtenerClave())){        //Comprueba si la clave del objeto valor (obtenida con obtenerClave()) no está ya en el mapa nodos
            nodos.put(valor.obtenerClave(),new Nodo<>(valor)); //Si no existe, crea un nodo con el valor/datos y lo inserta con clave Long.
        }else{
            System.out.println("Ya existe en el grafo");
        }

    }

    @Override
    public void agregarArista(T origen, T destino) {  //Agregar arista entre inicio y destino, entre 2 nodos.
        if(nodos.containsKey(origen.obtenerClave()) && nodos.containsKey(destino.obtenerClave())){ //Verifica que ambos nodos existen
            INodo<T> primero= nodos.get(origen.obtenerClave()); 
            INodo<T> segundo= nodos.get(destino.obtenerClave()); //Recupera el origen y el destino en 2 variables.
            primero.agregarVecino(segundo); 
            segundo.agregarVecino(primero); //Los conecta a ambos ida y vuelta, arista de doble sentido. 
        }									// Seria una arista bidireccional o sin sentido.
    }
    //public void agregarAristaUniDireccional(T origen, Tdestino) {
    	
    //}
    
    @Override
    public void mostrarMatrizAdyacencia() {
    	
    }

    @Override
    public void mostrarListaAdyacencia() {
    	
    }

    @Override
    public void bfs(T inicio) { //(breadth-first search)
        if (!nodos.containsKey(inicio.obtenerClave())) return; // precondición (Si el nodo de inicio no existe en el grafo, sale inmediatamente)
        ///Lista y cola
        Set<Long> visitados = new HashSet<>(); // Se guardan las claves de los nodos ya visitados para evitar procesarlas de nuevo.

        Queue<INodo<T>> cola = new LinkedList<>(); // Cola FIFO para el recorrido. Se usa LinkedList como implementación de Queue

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
