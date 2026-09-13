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

