/**
 * @author Daniel Martin Corpa
 * 13/3/23
 * 16:58
 * @version version of class
 * @return value to return
 * @throws Errors that throws
 * @since since version
 * @deprecated Method deprecated
 */

/**
 * @param
 * @see ""
 *
 */


public class EjemploStringBuilder {

    public static void main(String[] args) {
        System.out.println("Ejemplo" + " de "+"Concatencaión: ");
        // Según las reglas de buenas prácticas
        // en vez de +, crear un StringBuilder


        // Creamos un objeto StringBuilder
        StringBuilder sb = new StringBuilder("Ejemplo");
        // si ahora quuiero concatenar algo debo usar el método append()
        sb.append(" de ");
        sb.append("concatenación.");
        // ahora quiero imprimir el resultado del StringBuilder
        System.out.println(sb);

    }
}
