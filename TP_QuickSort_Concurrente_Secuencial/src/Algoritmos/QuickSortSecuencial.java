package Algoritmos;

import java.util.Random;

public class QuickSortSecuencial {

    // Método principal que inicia la ejecución del programa
    public static void main(String[] args) {
        int tamaño = 10;  // Definimos el tamaño del arreglo a ordenar
        int[] arreglo = generarArregloAleatorio(tamaño);  // Generar arreglo con números aleatorios

        System.out.println("Arreglo original:");
        imprimirArreglo(arreglo);  // Mostrar arreglo antes de ordenar

        quickSort(arreglo, 0, arreglo.length - 1);  // Ordenar arreglo con QuickSort

        System.out.println("Arreglo ordenado (secuencial):");
        imprimirArreglo(arreglo);  // Mostrar arreglo ordenado
    }

    // Método para generar un arreglo con números aleatorios entre 0 y 99
    public static int[] generarArregloAleatorio(int tamaño) {
        int[] arreglo = new int[tamaño];  // Crear arreglo con el tamaño especificado
        Random rand = new Random();       // Crear objeto Random para generar números aleatorios

        // Llenar arreglo con números aleatorios entre 0 y 99
        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = rand.nextInt(100);
        }
        return arreglo;  // Retornar arreglo generado
    }

    // Método recursivo que implementa el algoritmo QuickSort
    public static void quickSort(int[] arr, int inicio, int fin) {
        if (inicio < fin) {  // Caso base: si el segmento tiene más de un elemento
            int indicePivote = particion(arr, inicio, fin);  // Particionar arreglo y obtener índice del pivote

            quickSort(arr, inicio, indicePivote - 1);  // Ordenar segmento izquierdo
            quickSort(arr, indicePivote + 1, fin);     // Ordenar segmento derecho
        }
    }

    // Método que reorganiza los elementos en torno al pivote y retorna su posición final
    public static int particion(int[] arr, int inicio, int fin) {
        int pivote = arr[fin];  // Elegir último elemento como pivote
        int i = inicio - 1;     // Índice del último elemento menor al pivote

        // Recorrer arreglo desde inicio hasta el elemento antes del pivote
        for (int j = inicio; j < fin; j++) {
            if (arr[j] < pivote) {  // Si el elemento actual es menor que el pivote
                i++;  // Incrementar índice de elementos menores
                // Intercambiar arr[i] con arr[j]
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // Colocar el pivote en su posición correcta (después del último menor)
        int temp = arr[i + 1];
        arr[i + 1] = arr[fin];
        arr[fin] = temp;

        return i + 1;  // Retornar índice del pivote
    }

    // Método para imprimir los elementos del arreglo en consola
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");  // Imprimir cada número con espacio
        }
        System.out.println();  // Salto de línea
    }
}
