package Algoritmos;

import java.util.Random;

public class QuickSortConcurrente {

    // Clase interna que extiende Thread para ordenar una parte del arreglo
    static class QuickSortThread extends Thread {
        private int[] arr;     // Arreglo a ordenar
        private int inicio;    // Índice inicial del segmento a ordenar
        private int fin;       // Índice final del segmento a ordenar

        // Constructor para inicializar el arreglo y los límites
        public QuickSortThread(int[] arr, int inicio, int fin) {
            this.arr = arr;
            this.inicio = inicio;
            this.fin = fin;
        }

        // Método que se ejecuta al iniciar el hilo
        @Override
        public void run() {
            quickSort(arr, inicio, fin);  // Llama al método quickSort sobre el segmento asignado
        }

        // Implementación recursiva de QuickSort con hilos para concurrencia
        private void quickSort(int[] arr, int inicio, int fin) {
            if (inicio < fin) {  // Caso base: si el segmento tiene más de un elemento
                int indicePivote = particion(arr, inicio, fin);  // Particionar y obtener índice del pivote

                // Crear hilo para ordenar la parte izquierda del pivote
                QuickSortThread izquierda = new QuickSortThread(arr, inicio, indicePivote - 1);
                // Crear hilo para ordenar la parte derecha del pivote
                QuickSortThread derecha = new QuickSortThread(arr, indicePivote + 1, fin);

                izquierda.start();  // Iniciar el hilo izquierdo
                derecha.start();    // Iniciar el hilo derecho

                try {
                    izquierda.join();  // Esperar a que termine el hilo izquierdo antes de continuar
                    derecha.join();    // Esperar a que termine el hilo derecho antes de continuar
                } catch (InterruptedException e) {
                    e.printStackTrace();  // Manejo de excepción en caso de interrupción del hilo
                }
            }
        }

        // Método que organiza el arreglo en base al pivote, devuelve la posición final del pivote
        private int particion(int[] arr, int inicio, int fin) {
            int pivote = arr[fin];  // Elegir el último elemento como pivote
            int i = inicio - 1;     // Índice del elemento más pequeño encontrado

            // Recorrer el arreglo desde inicio hasta antes del pivote
            for (int j = inicio; j < fin; j++) {
                if (arr[j] < pivote) {  // Si el elemento actual es menor que el pivote
                    i++;  // Incrementar índice de elementos menores
                    // Intercambiar arr[i] con arr[j] para poner el menor antes del pivote
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
    }

    // Método principal que inicia la ejecución del programa
    public static void main(String[] args) {
        int tamaño = 10;  // Definimos el tamaño del arreglo a ordenar
        int[] arreglo = generarArregloAleatorio(tamaño);  // Generamos arreglo con números aleatorios

        System.out.println("Arreglo original:");
        imprimirArreglo(arreglo);  // Mostrar el arreglo antes de ordenar

        QuickSortThread hiloPrincipal = new QuickSortThread(arreglo, 0, arreglo.length - 1);
        hiloPrincipal.start();  // Iniciar hilo principal para ordenar todo el arreglo

        try {
            hiloPrincipal.join();  // Esperar a que termine el hilo principal antes de continuar
        } catch (InterruptedException e) {
            e.printStackTrace();  // Manejo de excepción en caso de interrupción del hilo
        }

        System.out.println("Arreglo ordenado (concurrente):");
        imprimirArreglo(arreglo);  // Mostrar el arreglo ordenado
    }

    // Método para generar un arreglo de números aleatorios entre 0 y 99
    public static int[] generarArregloAleatorio(int tamaño) {
        int[] arreglo = new int[tamaño];  // Crear arreglo del tamaño especificado
        Random rand = new Random();       // Crear objeto Random para generar números aleatorios

        for (int i = 0; i < tamaño; i++) {
            arreglo[i] = rand.nextInt(100);  // Asignar un número aleatorio entre 0 y 99
        }
        return arreglo;  // Retornar el arreglo generado
    }

    // Método para imprimir todos los elementos del arreglo en consola
    public static void imprimirArreglo(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");  // Imprimir número seguido de un espacio
        }
        System.out.println();  // Salto de línea al finalizar
    }
}

