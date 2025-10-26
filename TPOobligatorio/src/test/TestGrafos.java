package test;

import interfaces.IGrafo;
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

	}
}
