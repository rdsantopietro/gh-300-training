# Instrucciones para GitHub Copilot en este repositorio

Estas instrucciones son contexto permanente para Copilot Chat: son un ejemplo
real del principio **Surround**. Al agregar o cambiar reglas aquí, se observa
cómo cambian las sugerencias sin tocar el prompt de cada ejercicio.

## Cómo responder: concreto, sin divagar

La regla de oro es la del ejemplo de la calculadora: **si no se puede probar,
todavía no está bien pedido ni bien respondido.**

- Ir directo al problema. Nada de introducciones, teoría general ni alternativas
  que nadie pidió.
- Ante un pedido vago ("haz un método que haga cuentas"), no adivinar ni
  explayarse: proponer el contrato mínimo (nombre, parámetros, tipo de retorno,
  restricciones) y una prueba que lo verifique.
- Toda respuesta de código sigue este orden: **qué se pide en una frase →
  firma/método → prueba → comando y resultado esperado**.
- Mostrar el caso concreto. Para "sumar dos números":

  ```java
  // Pedido vago: "haz un método que haga cuentas".
  // Pedido concreto: recibe dos int y devuelve la suma, sin imprimir.
  public int sumar(int a, int b) {
      return a + b;
  }
  ```

  ```java
  @Test
  void sumaDosNumeros() {
      assertEquals(5, new Calculadora().sumar(2, 3));
  }
  ```

- Si el resultado no coincide con lo esperado, mostrar **esperado vs. obtenido**,
  corregir y volver a ejecutar. No reemplazar la explicación por una lista de
  posibilidades.
- Ubicar el ejercicio en el curso solo cuando aporte, en una línea: "Unidad 4:
  tipos de prompt", "como vimos en la unidad 3, el pipeline filtra antes de
  generar". Una referencia, no un resumen de la unidad.
- No agregar funcionalidad, archivos ni refactors que no se pidieron. Si algo
  falta para poder probar, señalarlo en una frase y seguir.
- Responder en español neutro, sin emojis y sin relleno.
- Guardar los prompts reutilizables en `docs/prompts.md`, no dentro del código.

## Estilo de código

- Java 25 (JDK), compilado con el toolchain de Gradle.
- Sin frameworks ni dependencias externas en el código de producción.
- Usar `record` para modelos y DTOs inmutables.
- Nombres de clases, métodos y variables en español, con la convención Java
  (`PascalCase` para tipos, `camelCase` para métodos y variables).
- Métodos cortos y con una única responsabilidad.
- Sin `System.out.println` ni código de depuración en las clases de modelo y
  controlador; la salida por consola pertenece a la vista.
- Codificación UTF-8 en todos los archivos fuente.

## Arquitectura

- Mantener la arquitectura **MVC** con los paquetes `model`, `view` y
  `controller`.
- El modelo no debe depender de la vista ni del controlador.
- La vista se encarga de la entrada y salida por consola.
- El controlador orquesta el flujo entre modelo y vista.
- Inyección por constructor; nunca inyección por campos.

## Manejo de errores

- Validar los argumentos al inicio de cada método público.
- Lanzar `IllegalArgumentException` con un mensaje claro cuando un argumento no
  sea válido.
- No silenciar excepciones con `catch (Exception e) {}`.
- Propagar o traducir los errores en el controlador para mostrarlos en la vista.

## Seguridad

- Nunca almacenar contraseñas en texto plano.
- No inventar primitivas criptográficas: usar las APIs de la biblioteca
  estándar de Java.
- Validar y normalizar toda entrada que venga del usuario.

## Pruebas

- Cada clase o método nuevo debe poder probarse con **JUnit 5**.
- Usar **AssertJ** solo si el proyecto lo agrega como dependencia; en su defecto,
  usar las aserciones de JUnit.
- Preferir pruebas parametrizadas (`@ParameterizedTest`) para los casos borde.
- No modificar las pruebas para hacer que pasen: si fallan, se corrige la
  implementación.
- Ejecutar las pruebas con `./gradlew test` (Windows: `.\gradlew.bat test`).

## Documentación

- Actualizar el `README.md` cuando cambien los comandos, la estructura o el
  comportamiento de la aplicación.
- Documentar en español, con lenguaje técnico claro y sin emojis.
- Mantener el laboratorio de privacidad y tratamiento de datos en `docs/u4-ejercicio-datos.md`.

## Privacidad y datos

- No incluir datos personales reales en código, pruebas, comentarios ni
  prompts. Usar nombres ficticios y correos del dominio reservado
  `example.com`.
- No pegar credenciales, tokens ni claves en Copilot Chat, ni siquiera para
  probar el filtrado. Las pruebas usan valores claramente falsos, como
  `ghp_0000000000000000000000000000000000`, viven en `.tmp/` y se borran al
  terminar.
- No adjuntar con `#file` archivos de entorno, claves, certificados ni volcados
  de datos. Si hace falta contexto, describir la estructura sin incluir
  valores.
- Preferir `#file` sobre `@workspace` cuando alcance un archivo, y `#selection`
  sobre `#file` cuando alcance un fragmento. Cuanto menor el contexto, menor la
  exposición.
- Iniciar un chat nuevo por tema para no arrastrar datos de conversaciones
  anteriores.
- Tratar los correos, identificadores y rutas del equipo como datos personales:
  no versionarlos ni mostrarlos en capturas.
- Los controles de content exclusion, registros de auditoría, telemetría y
  retención dependen de la organización. No intentar configurarlos desde el
  repositorio: documentarlos y marcarlos como `[ADMIN]`.
- Ante una duda de privacidad, detener la tarea y consultar al administrador
  antes de enviar contexto a Copilot.

-Si se cree q se tiene q leer todo el repositorio preguntar primero
