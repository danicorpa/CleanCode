## Cohesión

Una clase debe ser coherente, es decir, todos sus elementos deben servir a un mismo propósito, 
a esto es lo que llamamos **cohesión**, de esta forma nuestra clase es un bloque o una unidad única y singular.

En el momento en que nuestra clase empieza a abarcar más temas y tareas que se alejan de un propósito en común ahí debemos pensar en clases adicionales, entonces vemos que por ejemplo en **Java** tenemos la clase 
*String* que se encarga de las cadenas inmutables y la clase *StringBuilder* que nos ayuda a crear cadenas mutables, 
ambas clases pudieran ser similares pero sus fines son muy diferentes de ahí que estén separadas y tengan coherencia 
en sus acciones.

## Consistencia

Debemos tener presente las convenciones, esto nos ayuda a entender el código de manera rápida y a integrar equipos 
de trabajo más fácilmente, la convención no es solamente saber si un nombre debe ir en mayúscula o minúscula, 
sino también a que los nombres de los métodos sean descriptivos.

Así pues si por ejemplo tenemos en una clase un método que establece la cantidad de caracteres y lo llamamos 
*longitud()* en una clase diferente que haya un método que realice una acción similar no debería tener 
un nombre diferente, esto es lo que llamamos **consistencia**.

## Claridad

Por último la claridad de nuestra clase es primordial para que nuestro código pase a ser excelente, esto nos implica diseñar nuestras abstracciones para que sean simples y puedan utilizarse en cualquier contexto, 
así pues una *clase Persona* pueda contener atributos y métodos que puedan aplicarse en diferentes contextos sin que 
la clase pierda su esencia.

~~~
Otro aspecto a tener en cuenta es que no podemos colocar atributos que puedan ser derivados de otros, de esta forma podemos evitar confusiones y escribir código innecesario, veamos en el ejemplo siguiente a que nos referimos con ello.


![Imágen](/img/tutorials-2308-0-04393300-1405804343.jpg)
~~~

Vemos que tenemos un atributo **edad**, pero la edad la podemos saber de la fecha de nacimiento, por lo que estamos declarando un atributo que no es necesario y que nos puede generar confusiones, si queremos saber la edad podemos crear un método que lo calcule tomando el atributo **fechaNacimiento**.

Como podemos ver para generar una buena clase en **Java* no solo basta que no existen errores sintácticos ni semánticos, también debemos tener en cuenta la calidad del mismo.