package vista;

import java.util.*;

public class Dijkstra {

    String[] vectorLetras;  // Letras de identificación de nodo
    int[][] grafo;  // Matriz de distancias entre vectorLetras
    List<Nodo> listos = null;                        // vectorLetras revisados Dijkstra

    Dijkstra(String[] serieLetras) {
        System.out.println(Arrays.toString(serieLetras));
        vectorLetras = serieLetras;

        grafo = new int[vectorLetras.length][vectorLetras.length];

    }

    // asigna el tamaño de la arista entre dos vectorLetras
    public void agregarRuta(String origen, String destino, int distancia) {
        int n1 = posicionLetra(origen);
        int n2 = posicionLetra(destino);
        grafo[n1][n2] = distancia;
        grafo[n2][n1] = distancia;
    }
    
    public void imprimirGrafoMatriz(){
        System.out.println("MATRIZ GRAFO");
        for (int i = 0; i < grafo.length; i++) {
            for (int j = 0; j < grafo[i].length; j++) {
                System.out.print(grafo[i][j] + " ");
            }
            System.out.println("");
        }
    }

    // retorna la posición en el arreglo de un nodo específico
    private int posicionLetra(String letra) {
        int l = 0;
        for (int i = 0; i < vectorLetras.length; i++) {
            if (vectorLetras[i].equals(letra)) {
                return i;
            } else {
                l = i;
            }
        }
        return l;
    }

    // encuentra la ruta más corta desde un nodo origen a un nodo destino
    public String encontrarRutaMinimaDijkstra(String inicio, String fin) {
        // calcula la ruta más corta del inicio a los demás
        encontrarRutaMinimaDijkstra(inicio);
        // recupera el nodo final de la lista de terminados
        Nodo tmp = new Nodo(fin);
        if (!listos.contains(tmp)) {
            return "Error, Camino no alcanzable";
        }
        tmp = listos.get(listos.indexOf(tmp));
        int distancia = tmp.distancia;

        // crea una pila para almacenar la ruta desde el nodo final al origen
        Stack<Nodo> pila = new Stack<Nodo>();
        while (tmp != null) {
            pila.add(tmp);
            tmp = tmp.procedencia;
        }
        String ruta = " ";
        // recorre la pila para armar la ruta en el orden correcto
        while (!pila.isEmpty()) {
            ruta += (pila.pop().id + " ");
        }
        return "La distancia es: " + distancia + " Km y el camino a seguir es por " + ruta;
    }
    
    // encuentra la ruta más corta desde un nodo origen a un nodo destino
    private void encontrarRutaMinimaDijkstra(String inicio) {
        Queue<Nodo> cola = new PriorityQueue<Nodo>(); // cola de prioridad
        Nodo ni = new Nodo(inicio);          // nodo inicial

        listos = new LinkedList<Nodo>();// lista de vectorLetras ya revisados
        cola.add(ni);                   // Agregar nodo inicial a la cola de prioridad
        while (!cola.isEmpty()) {        // mientras que la cola no esta vacia
            Nodo tmp = cola.poll();     // saca el primer elemento
            listos.add(tmp);            // lo manda a la lista de terminados
            int p = posicionLetra(tmp.id);
            for (int j = 0; j < grafo[p].length; j++) {  // revisa los vectorLetras hijos del nodo tmp
                if (grafo[p][j] == 0) {
                    continue;        // si no hay conexión no lo evalua
                }
                if (estaTerminado(j)) {
                    continue;      // si ya fue agregado a la lista de terminados
                }
                Nodo nod = new Nodo(vectorLetras[j], tmp.distancia + grafo[p][j], tmp);
                // si no está en la cola de prioridad, lo agrega
                if (!cola.contains(nod)) {
                    cola.add(nod);
                    continue;
                }
                // si ya está en la cola de prioridad actualiza la distancia menor
                for (Nodo x : cola) {
                    // si la distancia en la cola es mayor que la distancia calculada
                    if (x.id == nod.id && x.distancia > nod.distancia) {
                        cola.remove(x); // remueve el nodo de la cola
                        cola.add(nod);  // agrega el nodo con la nueva distancia
                        break;          // no sigue revisando
                    }
                }
                
            }
        }
    }

    // verifica si un nodo ya está en lista de terminados
    private boolean estaTerminado(int j) {
        Nodo tmp = new Nodo(vectorLetras[j]);
        return listos.contains(tmp);
    }
}
