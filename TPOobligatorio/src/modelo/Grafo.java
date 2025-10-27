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
    	//if(nodos.containsKey(origen.obtenerClave()) && nodos.containsKey(destino.obtenerClave())){ //Verifica que ambos nodos existen
        //INodo<T> primero= nodos.get(origen.obtenerClave()); 
        //INodo<T> segundo= nodos.get(destino.obtenerClave()); //Recupera el origen y el destino en 2 variables.
        //primero.agregarVecino(segundo); 
        //segundo.agregarVecino(primero); //Los conecta a ambos ida y vuelta, arista de doble sentido. 
    //}			
    //}
    
    @Override
    public void mostrarMatrizAdyacencia() {
    	System.out.println("Matriz de Adyacencia: ");
        List<Long> claves = new ArrayList<>(nodos.keySet());
        Collections.sort(claves); //Metemos las claves a traves de "nodos", que usa HashMap y las colocamos en una lista. Por ult. ordenamos la lista.

        System.out.print("    ");
        for (Long clave : claves) {
            System.out.print(clave + " ");
        }
        System.out.println();

        // Filas de la matriz
        for (Long claveI : claves) { //Itera por cada clave en claves. Columnas de la  matriz
            System.out.print(claveI + ": ");
            INodo<T> nodoI = nodos.get(claveI); //Atrapa al nodo con la Persona que le corresponde la clave y la guarda en nodoI
            for (Long claveJ : claves) { //Filas
                INodo<T> nodoJ = nodos.get(claveJ); 
                // Imprime 1 si hay conexión, 0 si no
                System.out.print(nodoI.getVecinos().contains(nodoJ) ? "1 " : "0 ");
            }
            System.out.println();
        }
    }


    @Override
    public void mostrarListaAdyacencia() {
    	System.out.println("Lista de Adyacencia:");
        List<Map.Entry<Long, INodo<T>>> entradas = new ArrayList<>(nodos.entrySet());
        Collections.sort(entradas, Comparator.comparing(Map.Entry::getKey));
        
        for (Map.Entry<Long, INodo<T>> entrada : entradas) {
            Long clave = entrada.getKey();
            INodo<T> nodo = entrada.getValue();
            System.out.print(clave + ": ");
            Set<INodo<T>> vecinos = nodo.getVecinos();
            
            for (INodo<T> vecino : vecinos) {
                System.out.print(vecino.getValor() + " "); //Muestra los datos de cada nodo "vecino" que son Personas.
            }
            System.out.println();
        }
    }
//Itera entre Nodos, de cada nodo muestra los datos sus nodos vecinos (Personas)

    
    @Override
    public void bfs(T inicio) { //Búsqueda en Amplitud en anchura
        if (!nodos.containsKey(inicio.obtenerClave())) return; // precondición (Si el nodo de inicio no existe en el grafo, sale inmediatamente)
        ///Lista y cola
        Set<Long> visitados = new HashSet<>(); // Se guardan las claves de los nodos ya visitados para evitar procesarlas de nuevo.

        Queue<INodo<T>> cola = new LinkedList<>(); // Cola FIFO para el recorrido. Se usa LinkedList como implementación de Queue

        INodo<T> nodoInicio = nodos.get(inicio.obtenerClave());
        cola.add(nodoInicio);
        visitados.add(inicio.obtenerClave());

        System.out.println("Recorrido BFS:");
        while (!cola.isEmpty()) {
            INodo<T> actual = cola.poll(); //poll, metodo para eliminar primero de una cola y añadirlo, en este caso a actual.
            System.out.print(actual.getValor() + " ");

            for (INodo<T> vecino : actual.getVecinos()) {          //Obtiene los vecinos del inicio/actual, itera y los almacena en la cola.
                if (!visitados.contains(vecino.getValor().obtenerClave())) {
                    visitados.add(vecino.getValor().obtenerClave());
                    cola.add( vecino);
                }
            }
        }
        System.out.println();
    }


    @Override
    public void dfs(T inicio) { //DFS Busqueda en profundidad (Recorre)
        if (!nodos.containsKey(inicio.obtenerClave())) return;
        //Si no existe un nodo de inicio, se cierra.
        Set<Long> visitados = new HashSet<>();
        System.out.println("Recorrido DFS:");
        INodo<T> nodoInicio = nodos.get(inicio.obtenerClave());
        dfsRec(nodoInicio, visitados);
        System.out.println();
    }

    private void dfsRec(INodo<T> actual, Set<Long> visitados) {
        // marcamos como visitado usando la clave del valor T (En nuestro caso la clave de la persona, dni)
        Long claveActual = actual.getValor().obtenerClave();
        visitados.add(claveActual);

        // imprimimos el valor 
        System.out.print(actual.getValor() + " ");

        // recorremos los vecinos (getVecinos() devuelve Set<INodo<T>>)
        for (INodo<T> vecino : actual.getVecinos()) {
            Long claveVecino = vecino.getValor().obtenerClave();
            if (!visitados.contains(claveVecino)) { //Vecinos contiene las claves, y verificamos si ya visitamos a este vecino.
                dfsRec(vecino, visitados); //En caso de no contener la clave del vecino, significa que no lo recorrimos. 
            } //Al recorrer este nuevo vecino, lo marcamos como visitado 
        }	  //Imprimimos su valor
    }  		  //Y repetimos el proceso con los vecinos de ese nodo.
    
     //public void encontrarCaminoCorto() {
    	
    //}
    
    //public void mostrarGrafoPonderado() {
    	
    //}
    
    //public void kruskal() {
    	
    //}
    
}
