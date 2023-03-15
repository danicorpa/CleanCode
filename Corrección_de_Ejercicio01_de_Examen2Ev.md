## Corrección de Ejercicio01 de Evaluación 2

> by Daniel Martin Corpa

He cogido este ejemplo de clase java para mejorarla para que cumpla, en lo posible, los criterios de clean code.

Para ello lo he compuesto de esta manera:

* Generados métodos para hacer cada acción y los llamo desde el método main, para cumplir con métodos cortos y que hagan una sola cosa.
* También, los métodos y los argumentos  cumplen con nombres descriptivos.
* Creado un par de constantes donde se guardan el tamaño del Array y el número máximo para el random, de ese modo se puede modificar su valor desde un sitio.
* Los bucles son for-each, así mejora la legibilidad.
* Argumentos y métodos privados para asegurar inmutabilidad.
* Añadidos comentarios para generar javadoc.
* Indentación y espacio entre sentencias regulados, para mejorar legibilidad.

---

## Código Mejorado
____
    /**
    @author Daniel Martin
    **/
    public class Ejercicio01 {

      private static final int TAMANO_MATRIZ = 5; // Tamaño de la matriz
      private static final int RANGO_NUMEROS_ALEATORIOS = 10; // Rango de números aleatorios para inicializar la matriz

      public static void main(String[] args) {
          int[][] enteros = inicializarMatrizAleatoria(TAMANO_MATRIZ, RANGO_NUMEROS_ALEATORIOS); // Inicializa la matriz de enteros aleatoriamente
          char[][] caracteres = convertirNumerosEnCaracteres(enteros); // Convierte los números de la matriz de enteros a caracteres

          imprimirMatriz(enteros); // Imprime la matriz de enteros
          System.out.println();
          imprimirMatriz(caracteres); // Imprime la matriz de caracteres
      }

      /**
       * Inicializa una matriz de enteros con valores aleatorios entre 0 y rango-1.
       *
       * @param tamano El tamaño de la matriz cuadrada.
       * @param rango El rango de números aleatorios.
       * @return La matriz de enteros inicializada aleatoriamente.
       */
      private static int[][] inicializarMatrizAleatoria(int tamano, int rango) {
          int[][] matriz = new int[tamano][tamano];
          for (int[] fila : matriz) {
              for (int i = 0; i < fila.length; i++) {
                  fila[i] = (int) (Math.random() * rango);
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
  
## Código Original

---
    public class Ejercicio01 {
    public static void main(String[] args) {
    int[][] enteros = new int[5][5];
    for (int i = 0; i < enteros.length; i++) {
    for (int j = 0; j < enteros[i].length; j++) {
    enteros[i][j] = (int) (Math.random() * 10);
        }
    }

        for (int i = 0; i < enteros.length; i++) {
            for (int j = 0; j < enteros[i].length; j++) {
                System.out.print(enteros[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
        char[][] caracteres = new char[5][5];
        for (int i = 0; i < enteros.length; i++) {
            for (int j = 0; j < enteros[i].length; j++) {
                if (enteros[i][j] % 2 == 0) {
                    caracteres[i][j] = 'p';
                } else {
                    caracteres[i][j] = 'i';
                }
            }
        }

        for (int i = 0; i < caracteres.length; i++) {
            for (int j = 0; j < caracteres[i].length; j++) {
                System.out.print(caracteres[i][j] + "\t");
            }
            System.out.println();
            }
    
        }



    }
