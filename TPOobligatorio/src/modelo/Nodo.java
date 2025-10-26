package modelo;

import interfaces.INodo;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Nodo <T> implements INodo <T>{
    private T valor; // Valor almacenado en el nodo; T <T>

    private Set<INodo<T>> vecinos; // Lista de nodos vecinos (adyacentes)
                                   // creo set en vez de lista para que solo acepte valor unicos


    public Nodo(T valor) {
        this.valor = valor;
        this.vecinos=new HashSet<>();
    }

    @Override
    public T getValor() {
        return this.valor;
    }

    @Override
    public void setValor(T valor) {
        this.valor=valor;

    }

    @Override
    public void agregarVecino(INodo<T> vecino) {

        vecinos.add(vecino);
    }

    @Override
    public Set<INodo<T>> getVecinos() {

        return this.vecinos;
    }

}
