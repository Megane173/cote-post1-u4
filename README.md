# Post-contenido — Unidad 4: Patrones de Comportamiento en ComprasUDES

## Descripción
Repositorio del post-contenido de la Unidad 4 de Patrones de Diseño
de Software. Un único proyecto Spring Boot (compras-comportamiento)
que resuelve cuatro necesidades reales del backend de ComprasUDES,
el sistema interno de solicitudes de compra corporativas: aprobación
por niveles jerárquicos, ejecución reversible de solicitudes
aprobadas, notificaciones ante cambios de estado y reglas de
transición según el estado actual de la solicitud.

## Cómo ejecutar
```
$ mvn clean package
$ mvn spring-boot:run
$ mvn test
```

## Decisiones de diseño

### Necesidad 1 — Aprobación por niveles jerárquicos
Se aplicó Chain of Responsability, se consideró y descarto Command. La necesidad descrita habla de niveles, los cuales pueden procesar o delegar la solicitud según corresponda. Aquello no es un problema de encapsular acciones reversibles, se trata de poder realizar una cadena de procesamiento de forma dinámica. Command convierte una solicitud en un objeto independiente, lo cual permite hacer un historial, deshacer, parametrizar solicitudes, etc... Pero lo que no resuelve por sí mismo, es facilitar la creación dinámica de una cadena de procesamiento.

### Necesidad 2 — Ejecución reversible de solicitudes
Para esta necesidad se aplicó Command. A diferencia de la necesidad 1, las acciones a ejecutar están determinadas por un solo actor, que decide sin cadenas de decisión o delegación la ejecución de las operaciones. En esta necesidad se pide flexibilidad para ejecutar, registrar y deshacer las operaciones mencionadas. Esto es algo que Chain of Responsability no puede hacer por sí misma, ya que esta determina la secuencia y niveles con las que se procesa una solicitud, lo cual no proporciona la flexibilidad deseada sobre las operaciones. De la misma forma que CoR no puede hacer lo que Command naturalmente hace, Command no puede hacer lo que CoR naturalmente hace, lo cual es esencialmente, añadir flexibilidad en los niveles y delegación que participan en el procesamiento de solicitudes, lo cual definió la decisión de la necesidad 1.

### Necesidad 3 — Notificaciones ante cambio de estado
En esta necesidad se aplicó Observer. En esta necesidad se pide que módulos diferentes, es decir, encargados de responsabilidades diferentes al módulo que cambió de estado, actúen en consecuencia al cambio de estado del objeto observado, en este caso una Solicitud. Un State no encajaría para esta necesidad, pues a pesar de responder ante cambios de estado en un objeto, responde redefiniendo el comportamiento base del objeto, cumpliendo State el rol de encapsular el comportamiento singular de cada estado.

[Qué patrón se aplicó, por qué el problema no es el comportamiento
propio de la solicitud sino la reacción de módulos externos ajenos a
ella, y por qué el patrón de la Necesidad 4 no serviría aquí.]

### Necesidad 4 — Reglas de transición según el estado
En esta necesidad se aplicó State. El motivo por el que no se aplicó Strategy radica en el propósito y comportamiento por el que se rigen ambos, pues estructuralmente son bastante parecidos. State cambia el comportamiento basado en el estado del objeto, por como funciona, los estados se pueden llegar a conocer entre sí. Por otro lado, Strategy cambia el comportamiento por decisión del cliente, no tiene ninguna relación de cambio con el estado del objeto, lo cual permite que cada estrategia no tenga que conocerse entre sí. 

Esta solución permite encapsular el comportamiento singular de cada estado en diferentes clase por estado y, permite cambiar el comportamiento de la clase por medio del polimorfismo, delegando comportamiento al estado concreto que se encuentre activo, eliminando la necesidad de extensos y dispersos condicionales en la clase base del objeto.


### Reflexión — otros tres patrones (opcional)
1) Encaja con Iterator, permite iterar una colección sin exponer ni depender de su estructura de almacenamiento
2) Encaja con Template Method, representa un algoritmo en una clase y, permite a sus subclases sobreescribir partes del algoritmo según corresponda
3) El patrón que más se acerca a eso sería Memento, ya que permite guardar y restaurar el estado de un objeto (originator) sin exponer sus detalles internos a quien almacena las instantáneas (caretaker). A diferencia de Command, que encapsula métodos en objetos para ejecutar y deshacer, Memento genera objetos que encapsulan el estado del originator en un momento determinado.
## Herramientas utilizadas
- Java 17, Spring Boot 3.2, Apache Maven, JUnit 5
- VS Code, Git, GitHub

## Conclusiones
Los patrones de comportamiento se pueden aplicar en varias situaciones y, su elección depende en gran medida del propósito que se quiera resolver. Algunos como State y Strategy pueden confundirse por su estructura pero su propósito y comportamiento ayuda a diferenciar claramente el uno del otro, lo cual es importante porque los patrones además de soluciones, son medios de comunicación entre programadores. Finalmente, la implementación de los patrones resultó fructífera para explorar las formas en que pueden aplicarse en el contexto planteado.
