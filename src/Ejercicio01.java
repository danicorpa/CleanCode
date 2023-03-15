/**
 * @author Daniel Martin
 * Se generan números aleatorios y se guardan en un Array, luego se genera otro Array donde se
 * indica si el número del Array de números es par (p) o impar (i).
 *
 **/
public class Ejercicio01 {
    /**
     *  Tamaño de la matriz
     */
    private static final int TAMANO_MATRIZ = 5; // Tamaño de la matriz
    /**
     *  Tamaño del rango de numeros aleatorios
     */
    private static final int RANGO_NUMEROS_ALEATORIOS = 10; // Rango de números aleatorios para inicializar la matriz

    public static void main(String[] args) {

        int[][] enteros = inicializarMatrizAleatoria(TAMANO_MATRIZ, RANGO_NUMEROS_ALEATORIOS); // Inicializa la matriz de enteros aleatoriamente
        char[][] caracteres = convertirNumerosEnCaracteres(enteros); // Convierte los números de la matriz de enteros a caracteres

        imprimirMatriz(enteros); // Imprime la matriz de enteros
        System.out.println();
        imprimirMatriz(caracteres); // Imprime la matriz de caracteres
    }
    /**
     * Inicializa una matriz de enteros con valores aleatorios entre 0 y rangoAleatorio-1.
     *
     * @param tamanoMatriz El tamaño de la matriz cuadrada.
     * @param rangoAleatorio El rangoAleatorio de números aleatorios.
     * @return La matriz de enteros inicializada aleatoriamente.
     */
    private static int[][] inicializarMatrizAleatoria(int tamanoMatriz, int rangoAleatorio) {
        int[][] matriz = new int[tamanoMatriz][tamanoMatriz];
        for (int[] fila : matriz) {
            for (int i = 0; i < fila.length; i++) {
                fila[i] = (int) (Math.random() * rangoAleatorio);
            }
        }
        return matriz;
    }

    /**
     * Convierte una matriz de enteros en una matriz de caracteres donde cada número par se convierte en 'p' y cada número impar se convierte en 'i'.
     *
     * @param enteros La matriz de enteros a convertir.
     * @return La matriz de caracteres convertida.
     */
    private static char[][] convertirNumerosEnCaracteres(int[][] enteros) {
        char[][] caracteres = new char[TAMANO_MATRIZ][TAMANO_MATRIZ];
        int filaIndex = 0;
        for (int[] fila : enteros) {
            int columnaIndex = 0;
            for (int numero : fila) {
                caracteres[filaIndex][columnaIndex] = (numero % 2 == 0) ? 'p' : 'i';
                columnaIndex++;
            }
            filaIndex++;
        }
        return caracteres;
    }

    /**
     * Imprime una matriz de enteros en la consola.
     *
     * @param matriz La matriz de enteros a imprimir.
     */
    private static void imprimirMatriz(int[][] matriz) {
        for (int[] fila : matriz) {
            for (int elemento : fila) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }

    /**
     * Imprime una matriz de caracteres en la consola.
     *
     * @param matriz La matriz de caracteres a imprimir.
     */
    private static void imprimirMatriz(char[][] matriz) {
        for (char[] fila : matriz) {
            for (char elemento : fila) {
                System.out.print(elemento + "\t");
            }
            System.out.println();
        }
    }
}

