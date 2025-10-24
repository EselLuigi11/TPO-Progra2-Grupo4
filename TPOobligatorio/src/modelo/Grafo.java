package modelo;

import interfaces.IGrafo;
import interfaces.IIdentificable;
import interfaces.INodo;

import java.util.HashMap;
import java.util.Map;

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


    }

    @Override
    public void mostrarMatrizAdyacencia() {

    }

    @Override
    public void mostrarListaAdyacencia() {

    }

    @Override
    public void bfs(T inicio) {

    }

    @Override
    public void dfs(T inicio) {

    }
}
