
## Java - Bases para el Diseño de Clases

> By Daniel Martin Corpa

----
Aquí os presento algunas consideraciones que se debería tener en cuenta al desarrollar las clases **Java** para dar más claridad al código, así como un mekor funcionamiento al programa final.

**[Mi github]( https://github.com/danicorpa/CleanCode/)**


## Cohesion

------
Una clase debe ser coherente, es decir, todos sus elementos deben servir a un mismo propósito, 
a esto es lo que llamamos **cohesión**, de esta forma nuestra clase es un bloque o una unidad única y singular.

En el momento en que nuestra clase empieza a abarcar más temas y tareas que se alejan de un propósito en común ahí debemos pensar en clases adicionales, entonces vemos que por ejemplo en **Java** tenemos la clase 
*String* que se encarga de las cadenas inmutables y la clase *StringBuilder* que nos ayuda a crear cadenas mutables, 
ambas clases pudieran ser similares pero sus fines son muy diferentes de ahí que estén separadas y tengan coherencia 
en sus acciones.

## Consistencia

------
Debemos tener presente las convenciones, esto nos ayuda a entender el código de manera rápida y a integrar equipos 
de trabajo más fácilmente, la convención no es solamente saber si un nombre debe ir en mayúscula o minúscula, 
sino también a que los nombres de los métodos sean descriptivos.

Así pues si por ejemplo tenemos en una clase un método que establece la cantidad de caracteres y lo llamamos 
*longitud()* en una clase diferente que haya un método que realice una acción similar no debería tener 
un nombre diferente, esto es lo que llamamos **consistencia**.

## Claridad

-----
Por último la claridad de nuestra clase es primordial para que nuestro código pase a ser excelente, esto nos implica diseñar nuestras abstracciones para que sean simples y puedan utilizarse en cualquier contexto, 
así pues una *clase Persona* pueda contener atributos y métodos que puedan aplicarse en diferentes contextos sin que 
la clase pierda su esencia.


Otro aspecto a tener en cuenta es que no podemos colocar atributos que puedan ser derivados de otros, de esta forma podemos evitar confusiones y escribir código innecesario, veamos en el ejemplo siguiente a que nos referimos con ello.


![](/img/tutorials-2308-0-04393300-1405804343.jpg)


Vemos que tenemos un atributo **edad**, pero la edad la podemos saber de la fecha de nacimiento, por lo que estamos declarando un atributo que no es necesario y que nos puede generar confusiones, si queremos saber la edad podemos crear un método que lo calcule tomando el atributo **fechaNacimiento**.

Como podemos ver para generar una buena clase en **Java* no solo basta que no existen errores sintácticos ni semánticos, también debemos tener en cuenta la calidad del mismo.

## Inmutabilidad

## Los objetos en la OOP

------
En la programación orientada a objetos (OOP), la idea principal es abstraer un problema como objetos del mundo real. Por lo tanto, estos son considerados first-class citizens, con sus atributos (propiedades) y métodos (comportamiento). Pero, como en el mundo real, hay cierta información de los objetos que debe mantenerse oculta (encapsulamiento) y libre de cambios (invariants), y el mundo exterior no debe (o al menos no debería) conocerla. Cuando digo libre de cambios me refiero a que desde su creación y durante su ciclo de vida, el objeto no debe mutar.

## ¿Cómo logramos crear objetos inmutables?

----
Analicemos el siguiente código:
 

    class PersonInfo {

    public String name;

    public LocalDateTime birthday;

    }
Luego, digamos que en algún punto de la aplicación un cliente o caller crea un objeto de tipo PersonInfo de la siguiente manera:

    class PersonService {
    
    ...
    
    void registrationProcess() {
    
    PersonInfo personInfo = new PersonInfo();
    
    personInfo.name = "Bob"
    
    personInfo.birthday = LocalDateTime.of(2000, 1, 1, 0, 0);

    }
    ...
    }

Este parece ser un inocente e inofensivo programa, pero más bien es ingenuo, presenta varias desventajas y al mismo tiempo corre varios peligros en manos de sus callers. Joshua Bloch, en su libro *Effective Java* recomienda: **minimizar el acceso de clases y miembros de clase**, algo que la clase PersonInfo claramente no hace. **“Un componente bien diseñado oculta (encapsula) todos los detalles de su implementación y los componentes se comunican entre sí solo a través de sus APIs”**.

Para demostrar las debilidades y peligros que corren los objetos de la clase PersonInfo vamos a suponer que nuestro recién creado objeto es pasado a otro para procesar, por ejemplo, un registro en una bitácora:

    class PersonService {

    LoggingService loggingService; 

    void registrationProcess() { ...
    
    //una vez registrado, guardamos operación en bitácora

    loggingService.logRegistration(personInfo);
    
        }

    }

    class LoggingService {

    void logRegistration(PersonInfo personInfo) {

    recordPersonInfo(personInfo.name, personInfo.birthday);
    
    //Aprovechamos para poner el sufijo _logged para marcar

    // a la persona como procesada por el servicio de logging

    personInfo.name = personInfo.name + "_logged";

        }

    }

A ver, a ver, un momento, ¿cómo que: aprovechamos para poner el sufijo…? El nombre en un objeto PersonInfo no debería cambiar. Sin embargo aquí ha ocurrido y los invariants del objeto, pues… han variado. Los atributos de los objetos tipo PersonInfo están expuestos de tal manera que cualquier otro objeto puede manipularlos arbitrariamente. ¿Querrías tú que alguien cambiara tu nombre de esta manera? Creo que no.

Podemos erradicar el problema con encapsulamiento y mejor aún implementando inmutabilidad. En este sentido, otra vez Joshua Bloch recomienda: “en clases públicas, utiliza métodos accesors, no uses campos públicos” y “minimiza la mutabilidad”. Si bien Java soporta la inmutabilidad de clases, no nos forza a su utilización, pero nosotros, al escribir nuestros programas, sí podríamos hacerlo, y veremos cómo conseguirlo. Vamos por partes, primero…

## ¿Qué es una clase inmutable?

----
Una clase inmutable es simplemente aquella cuyas instancias no pueden ser modificadas una vez que su información ha sido definida. No habrá ninguna modificación a la misma durante su ciclo de vida. Un ejemplo de clase inmutable en Java es la clase String. Ahora veamos…

## ¿Cómo diseñar una clase inmutable?

---
Para que los objetos de tipo PersonInfo pasen de ser unos ingenuos desprevenidos a unos bien protegidos debemos seguir varios pasos:

Declarar la clase como final
Cambiar el modificador de acceso de public a private. Recuerda, minimiza el acceso.
Declarar cada atributo como final.
No exponer ningún mutator.
Exponer los atributos, si se necesita, solo con accesors.
Inicializar los atributos con ayuda de Constructores.
Poner especial atención a colecciones y parámetros de constructores

## Versión inmutable de PersonInfo

----
    final class PersonInfo {

    private final String name;

    private final LocalDateTime birthday;

    PersonInfo(String name, LocalDateTime birthday) {

    this.name = name;
    this.birthday = birthday;
    }

    public String getName() {

    return name;

    }

    //Solo queremos publicar el día y el mes de nacimiento,
    // en la forma "mm/dd", sin el año.

    public String getBirthday() {

    return birthday.getMonthValue() + "/" + birthday.getDayOfMonth();
    
        }
    }


#### Analicemos los pasos anteriores aplicados a la clase PersonInfo:


Si marcamos la clase como final, evitamos que a través de la herencia, una subclase consiga acceso a los atributos de la clase padre y modifique sus valores.
Cuando se cambia el modificador de acceso de los atributos de public a private, se protege el estado interno de los objetos.

Al marcar como final a los atributos de una clase, se asegura que los atributos del objeto no podrán ser modificados una vez que se han definido. En el caso de la clase PersonInfo, un constructor ayuda a inicializar los valores de sus atributos.

No implementes métodos que modifiquen el estado del objeto. Como se puede ver, ahora la clase PersonInfo no tiene mutators. Con este ajuste, evitamos cambios al estado del objeto.

Si fuera necesario, la clase expone información solo usando accesors, que son considerados como su API.

Si lo notaste, el accesor getBirthday() devuelve un String y no un LocalDateTime. No existe ninguna regla que indique que se deben regresar los atributos de un objeto usando el mismo tipo de dato de dichos atributos. Así que en este caso, encapsulamos el birthday de una persona, exponiendo el dato de mes/año como un String. En realidad el birthday podría ser un LocalDateTime o un timestamp en tipo de dato long. Los clientes no lo sabrían.

## Colecciones y parámetros en constructores

----
Si la clase que estamos definiendo tiene referencias a objetos mutables (Collections, StringBuilders u otros objetos, por ejemplo) que fueron recibidos como parámetros en constructores o que están expuestos a través de accesors, tienes que asegurarte que es el objeto el que tiene **acceso exclusivo** a esto atributos. Esto es, el cliente que construye la instancia o pide por el atributo no debe ser capaz de modificar dicho objeto. ¿Cómo lograrlo? **No inicialices un campo con referencias a objetos provistas por los clientes o no regreses un campo mutable desde un *accesor***. Una técnica para asegurar el acceso exclusivo es generar copias defensivas tanto de parámetros mutables recibidos en constructores, como de atributos mutables en accesors y en métodos *readObject* (en caso de serialización).

Para ver esta situación en acción, supongamos que implementamos la clase Student. Un estudiante tiene un nombre y una lista de cursos. El siguiente sería nuestro intento de hacer de Student una clase inmutable:

    final class Student {

    private final String name;

    private final List courses;

    //Constructor initialization

    Student(String name, List courses) {

    this.name = name;

    this.courses = courses;
    
    }

    public String getName() {

    return name;
    
    }

    public List getCourses() {
    
    return courses;

        }

    }

Pero, ¿Es realmente inmutable la clase Student? Sin analizar con detenimiento, podrías asegurar que sí, la clase es inmutable; como courses es final, como se inicializó en el construtor y además como no hay ningún mutator definido, la lista de cursos a los que un estudiante está inscrito no podrá modificarse.

Sin embargo, y aunque lo parezca, la clase Student no es completamente inmutable. La palabra reservada final en un atributo de clase (técnicamente en una referencia) **garantiza que una referencia**, en este caso courses, nunca apunte a otro objeto o tenga otro valor una vez que ha sido definida (por ejemplo, courses = new ArrayList<>() dispararía un error de compilación). Si bien la referencia se ha protegido, lo que ha quedado desprotegido aquí es el objeto al que esta referencia apunta, la lista de cursos como tal, **perdiendo así la exclusividad** en el acceso a dicha lista. Su vulnerabilidad se puede ver en el siguiente código de un malicioso caller:

    class Teacher {

    ...
    
    /**
    * Como profesor, tengo que completar el número de alumnos para
    * mi no muy apreciado curso y así recibir mi bono. Fácil, agregaré
    * mi curso al primer inocente de mi lista de estudiantes.
    **/
  
    public void addACourseToANaiveStudent() {
  
    allMyStudents().get(0).getCourses()
  
    .add(new Course("Project Management"));
  
    log.info("Venga mi bono");
  
    }
    ...
  
    }
  
Si fueras tú el estudiante en cuestión y al final del semestre recibieras una nota reprobatoria de un curso que ni siquiera registraste, o peor aún, que algún caller decida eliminar tus cursos con un student.getCourses().clear() y con eso ya no aparecieras en las listas de los profesores, ¿qué dirías? ¿crees que tus internals estaban suficientemente protegidos? Como comenté, la técnica de hacer copias defensivas en constructores o accesors puede librarte de estos peligros. En la clase Student, el accesor a courses puede quedar de la siguiente manera:

    ...
    public List getCourses() {

    return Collections.unmodifiableList(courses);

    }
    ...
## Paralelización

---
Otro beneficio de los objetos inmutables es su uso en la paralelización. Desde la versión 8 de Java ya contamos con un estilo funcional de programación (FP) out-of-the-box a través del API de Stream y las expresiones Lambda (repito, es un estilo funcional porque Java no es y creo que nunca será un lenguaje funcional puro) y de mucho más atrás tenemos programacion multi-thread. Entonces, ¿por qué los objetos inmutables encajan perfecto aquí?, porque aseguran thread-safety, es decir, no importa cuántos clientes (threads) accedan de forma simultánea a nuestros objetos inmutables, sus atributos expuestos son de solo lectura y no cambian.

## ¿Desventajas?

-----
Si se considera como tal, la única desventaja real de utilizar objetos inmutables sería que si después de haber sido concebidos como inmutables, ahora sea necesario mutar el estado de estos objetos (sus atributos). Habrá entonces que crear nuevos objetos por cada nuevo valor y/o hacer una refactorización del código que los utiliza. Crear objetos puede resultar costoso (aunque esto no siempre cierto para objetos inmutables debido a las optimizaciones de la JVM y GC), especialmente para aquellos que son bastante grandes y de los cuales solo mutaría una pequeña cantidad de sus atributos. La concatenación de Strings (inmutables), por ejemplo, en un proceso de miles o millones de cadenas sería ineficiente. Usar un StringBuilder (mutable), es más adecuado en este escenario.

Para el caso de la creación de objetos de clases que tienen muchos atributos, inicializarlos a través de un constructor puede caer en el antipattern Telescoping Constructor o constructor telescópico, pero esto más que una desventaja, es un problema de legibilidad y mantenimiento del código, además de no ser exclusivo de objetos inmutables. Para solucionarlo, el patrón “Builder” es la solución de facto.

La clave radica en evaluar la naturaleza del dominio de negocio de los objetos; validar si estos deben o no mutar. Por ejemplo, para una aplicación de control de peso, el atributo peso de un objeto Persona no debe ser inmutable, no aplica, puesto que una persona sube o baja de peso. Así que, hay que usar la inmutabilidad donde sea pertinente y no en todos lados. Pero entre menos mutable sea nuestro modelo más ventajas obtendremos.

## Conclusión

---
Implementar código endeble y vulnerable que no considera buenas prácticas de OOP, puede traer consecuencias al momento de ser expuesto a clientes potencialmente maliciosos. Este tipo de código da libertad a que agentes externos manipulen el estado de nuestros objetos o que al depurarlo, para encontrar en qué punto alguien le agregó o quitó algo, resulte en un verdadero dolor de cabeza. Sin embargo, el código que tiene presente buenas prácticas, en este caso la inmutabilidad, trae muchos beneficios, como los siguientes:

* Los objetos son los mismos durante todo su ciclo de vida.
* Se mantiene oculto el detalle de la implementación.
* Solo expone lo que es necesario.
* Es fácil de diseñar e implementar.
* Es menos propenso a errores.
* Es más seguro.
* Fácil de probar y depurar.
* Tiene bajo acoplamiento.
* Es thread-safe.
* Puede mejorar el rendimiento, limitando el número de copias del objeto, como en el caso de los Strings.
* Previene side-effects.
* Perfecto para paralelizar.
* Así que te invito a que, en la medida de lo posible, y donde aplique la técnica de la inmutabilidad, la implementes. Happy coding!




#### Bibliografía
* [https://www.solvetic.com/tutoriales/article/973-java-bases-para-el-diseno-de-clases/](https://www.solvetic.com/tutoriales/article/973-java-bases-para-el-diseno-de-clases/)
* [https://www.bbvanexttechnologies.com/blogs/inmutabilidad-la-clave-para-crear-un-buen-diseno-en-java/](https://www.bbvanexttechnologies.com/blogs/inmutabilidad-la-clave-para-crear-un-buen-diseno-en-java/)
* EL libro que se comenta es Effective Java y lo puedes ver [aqui](https://github.com/GunterMueller/Books-3/blob/master/Effective%20Java%20(3rd%20Edition).pdf) en inglés.  