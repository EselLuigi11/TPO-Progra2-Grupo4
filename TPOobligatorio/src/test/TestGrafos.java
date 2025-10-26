package test;

import interfaces.IGrafo;
import interfaces.INodo;
import modelo.Grafo;
import modelo.Persona;

public class TestGrafos {
	private static String MASCULINO = "M";
	private static String FEMENINO = "F";

	public static void main(String[] args) {
		IGrafo<Persona> grafo = new Grafo<>();

		Persona alejandro = new Persona("Alejandro", 1L, MASCULINO);
		Persona daniela = new Persona("Daniela", 2L, FEMENINO);
		Persona valentina = new Persona("Valentina", 3L, FEMENINO);
		Persona sebastian = new Persona("Sebastian", 4L, MASCULINO);
		Persona pedro = new Persona("Pedro", 5L, MASCULINO);

		grafo.agregarNodo(alejandro);
		grafo.agregarNodo(daniela);
		grafo.agregarNodo(pedro);
		grafo.agregarNodo(valentina);
		grafo.agregarNodo(sebastian);

		//cuando decimos que ale va con dani y ale con valen es bidireccional ,al sacar la ulyima linea del codigo de agregar aristas va a apuntar directamnete y puede que cambie el recorrido

		grafo.agregarArista(alejandro,daniela);
		grafo.agregarArista(alejandro,valentina);
		grafo.agregarArista(daniela,valentina);
		grafo.agregarArista(daniela,sebastian);
		grafo.agregarArista(valentina,pedro);
		grafo.agregarArista(sebastian,pedro);

		// Visualización y recorridos
		grafo.mostrarMatrizAdyacencia();
		grafo.mostrarListaAdyacencia();
		grafo.bfs(alejandro);
		grafo.dfs(alejandro);

//Respuesta a pregunta:
//	que haria para que el grafo sea dirigido, deberías cambiar para lograrlo y que le pasa a los recorridos?:
// en el metodo del grafo :public void agregarArista(T origen, T destino)
// debemos sacar esta linea :  segundo.agregarVecino(primero)  deja de ser bidireccional y su recorrido puede cambiar dependiendo de como agregamos las aristas despues
//Para ser dirigido debemos solo agregar la arista entre origen y destino y no alreves

//		public void agregarArista(T origen, T destino) {  //Agregar arista entre inicio y destino, entre 2 nodos.
//			if(nodos.containsKey(origen.obtenerClave()) && nodos.containsKey(destino.obtenerClave())){ //Verifica que ambos nodos existen
//				INodo<T> primero= nodos.get(origen.obtenerClave());
//				INodo<T> segundo= nodos.get(destino.obtenerClave()); //Recupera el origen y el destino en 2 variables.
//				primero.agregarVecino(segundo);
//				segundo.agregarVecino(primero);
//
//
//			}
//		}

	}
}
