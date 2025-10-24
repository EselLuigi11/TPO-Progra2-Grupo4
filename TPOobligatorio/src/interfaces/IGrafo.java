package interfaces;


public interface IGrafo <T extends IIdentificable>{
    void agregarNodo(T valor); // pre: el valor no debe estar ya en el grafo
    void agregarArista(T origen, T destino); // pre: nodos deben existir

    void mostrarMatrizAdyacencia(); // Muestra la matriz de adyacencia
    void mostrarListaAdyacencia(); // Muestra la lista de adyacencia

    void bfs(T inicio); // pre: el nodo inicio debe existir
    void dfs(T inicio); // pre: el nodo inicio debe existir

}
