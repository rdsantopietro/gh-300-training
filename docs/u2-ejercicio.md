# Ejercicio U2 — Del prompt básico al prompt optimizado sobre `existeEmail` en RepositorioUsuarios

## 1. Objetivo

Como vimos en esta unidad, una solicitud eficaz suele ser única, específica, breve y estar rodeada del contexto necesario. Vamos a comparar una petición abierta con otra que aplica esas ideas —junto con ejemplos e iteración— para el mismo cambio, y revisar qué diferencias aparecen en el código y las pruebas.

Referencia: [Unidad 2, Fundamentos de la ingeniería de solicitudes y procedimientos recomendados](https://learn.microsoft.com/es-es/training/modules/introduction-prompt-engineering-with-github-copilot/2-prompt-engineering-foundations-best-practices).

### Contexto verificado antes del ejercicio

### HECHOS OBSERVADOS EN EL REPOSITORIO

| Hecho | Evidencia |
|---|---|
| Proyecto Java 25 con Gradle Wrapper, sin frameworks ni dependencias de producción. | `build.gradle` (toolchain 25, solo JUnit 5 en `testImplementation`), `gradlew.bat` |
| Arquitectura MVC por consola. | `src/main/java/com/ejemplo/usuarios/Main.java` conecta `RepositorioUsuarios`, `VistaConsola` y `UsuarioController` |
| El método `existeEmail` **no existe aún**. | `RepositorioUsuarios.java:18-42` tiene `crear`, `listar`, `buscar`, `actualizar`, `eliminar`, pero no `existeEmail`. |
| El modelo es un `record` inmutable. | `Usuario.java:6`: `public record Usuario(int id, String nombre, String email)` con validación de no vacío en el constructor compacto |
| El repositorio es en memoria. | `RepositorioUsuarios.java:15-16`: `LinkedHashMap<Integer, Usuario>` y `siguienteId` |
| API real del repositorio: `crear`, `listar`, `buscar`, `actualizar`, `eliminar`. | `RepositorioUsuarios.java:18-42` |
| La vista ya aplica `trim()` al leer texto. | `VistaConsola.java:29-32` |
| API real de la vista: `mostrarMenu`, `leerTexto`, `leerEntero`, `mostrarUsuarios`, `mostrarMensaje`. | `VistaConsola.java:19-57` |
| Existen dos tests del ABM y uno de calculadora no versionado. | `AbmUsuarioE2ETest.java`, `model/RepositorioUsuariosTest.java`, `src/main/java/com/ejemplo/calculadora/` sin trackear |
| No existen Spring Boot, JPA, REST, base de datos, AssertJ, Mockito, Lombok ni MapStruct. | `build.gradle` y árbol de `src` |
| El README menciona `docs/prompts.md` y el paquete `pipeline`, pero **no existen**. | `README.md:7` y `README.md:52-65`; `docs/prompts.md` no existe y `src/main/java/com/ejemplo/usuarios/pipeline` tampoco |
| Los tests pasan si se corrige `JAVA_HOME`. | Ejecución observada: con `JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot`, `.\gradlew.bat test` termina en `BUILD SUCCESSFUL`. El `JAVA_HOME` actual apunta a una ruta inexistente. |
| El árbol de trabajo no está limpio. | `git status` muestra archivos modificados y la carpeta `calculadora` sin trackear. |

### SUPOSICIONES

- Hace falta Copilot Chat con `@workspace` y `#file`; se usa el mismo modelo en ambas fases.
- Se puede crear un worktree o clon limpio para no mezclar las dos variantes.
- "Alta de usuario" se interpreta como **incremento sobre el alta existente**: validación de email, normalización y prevención de duplicados. No se parte de cero porque el método `alta()` ya está implementado.
- Las reglas de email del ejercicio son una decisión pedagógica, no un requisito previo del repositorio.
- La cuenta es Copilot Business; lo que dependa de administración se marca `[ADMIN]`. Este ejercicio no requiere cambios de administración.

### RECOMENDACIONES

- Congelar un commit base limpio antes de comparar. No ejecutar el ejercicio sobre el árbol sucio actual.
- No modificar `.github/copilot-instructions.md` entre fases: es contexto automático constante.
- Usar un chat nuevo por fase y no arrastrar la respuesta baseline a la fase optimizada.
- Guardar transcripciones y patches en `.tmp/u2/`, que ya está ignorado por `.gitignore`.
- Si se puede, repetir cada fase 3 veces y comparar medianas: un LLM no es determinístico.

---

## Bloque común anti-alucinación

Este bloque se usa para diseñar y revisar el ejercicio. **No se pega en el prompt baseline**, porque dejaría de ser baseline. La Fase 2 lo incorpora a través de su contexto verificado y sus restricciones.

```text
REGLAS ANTI-ALUCINACIÓN:
- Analizá primero el repositorio con @workspace.
- Antes de proponer cambios, identificá explícitamente qué archivos existen.
- No inventes clases, paquetes, APIs, servicios, dependencias ni frameworks.
- Si falta información del repositorio, indicarlo explícitamente.
- Toda modificación debe referenciar archivos existentes.
- Si proponés crear un archivo nuevo, justificá por qué es necesario.
- No asumir bases de datos, REST APIs, Spring Boot ni persistencia externa salvo que existan en el repositorio.
- Diferenciar claramente:
  - HECHOS OBSERVADOS EN EL REPOSITORIO
  - SUPOSICIONES
  - RECOMENDACIONES
```

---

Hipótesis del ejercicio:

> El prompt optimizado debe producir un cambio más pequeño, referenciado a archivos reales, con menos alucinaciones, con los tests existentes intactos y con menos iteraciones hasta que `.\gradlew.bat test` pase.

El objetivo no es demostrar que "un prompt más largo es mejor". El objetivo es medir si el resultado es **correcto, acotado y verificable**.

---

## 2. Qué conceptos de la Unidad 2 se practican

Como vimos en esta unidad, las 4S ayudan a enfocar y contextualizar una solicitud. En la comparación también aparecerán ejemplos, rol e iteración; fíjate cuáles ayudan a que Copilot respete mejor el código y las pruebas reales del repositorio.

---

## 3. Prompt baseline exacto

**Condiciones de ejecución:**

- Chat nuevo.
- Modelo por defecto, el mismo que se usará en la Fase 2.
- Un solo turno.
- No adjuntar archivos adicionales.
- No agregar el bloque anti-alucinación.
- Si el modelo pide aclaraciones, registrar la pregunta y responder solamente: `Decidí vos.`

**Prompt exacto:**

```text
@workspace Implementá el método `existeEmail(String email)` en la clase `RepositorioUsuarios`. Debe normalizar con `trim()`, comparar sin distinguir mayúsculas y lanzar `IllegalArgumentException` si recibe `null`. No modificar ninguna otra firma existente.
```

Guarda la respuesta completa en `.tmp/u2/baseline-respuesta.md`. Anota debajo, con tus propias palabras, qué archivos o APIs mencionó, si reconoció el alta existente y qué pasó al aplicar su propuesta y ejecutar las pruebas. No hace falta completar una ficha ni producir un resultado concreto.

---

## 4. Prompt mejorado exacto

**Acción previa:** en un chat nuevo, adjuntar con `#file` estos seis archivos:

```text
src/main/java/com/ejemplo/usuarios/controller/UsuarioController.java
src/main/java/com/ejemplo/usuarios/model/RepositorioUsuarios.java
src/main/java/com/ejemplo/usuarios/model/Usuario.java
src/main/java/com/ejemplo/usuarios/view/VistaConsola.java
src/test/java/com/ejemplo/usuarios/AbmUsuarioE2ETest.java
src/test/java/com/ejemplo/usuarios/model/RepositorioUsuariosTest.java
```

**Prompt exacto:**

````text
@workspace

ROL
Actuá como desarrollador Java senior responsable de mantener este repositorio.

TAREA ÚNICA
Implementá el método `existeEmail(String email)` en `RepositorioUsuarios` como un nuevo método, respetando el scope permitido: normalizar con `trim()`, comparar sin distinguir mayúsculas y lanzar `IllegalArgumentException` si recibe `null`. No modificar firmas existentes.

CONTEXTO VERIFICADO
- Java 25, Gradle Wrapper, JUnit 5. Sin frameworks ni dependencias de producción.
- Arquitectura MVC: `model`, `view`, `controller`. `Main` conecta las piezas.
- `Usuario` es un record inmutable `(int id, String nombre, String email)`.
- `RepositorioUsuarios` usa un `LinkedHashMap<Integer, Usuario>` en memoria y ya tiene `crear`, `listar`, `buscar`, `actualizar`, `eliminar`.
- `UsuarioController.alta()` es privado, lee nombre/email, rechaza vacíos, llama a `crear` y muestra "Usuario N creado.".
- `VistaConsola.leerTexto` ya hace `trim()`.
- No existen Spring Boot, JPA, REST, base de datos, AssertJ, Mockito ni Lombok en este repositorio.
- `docs/prompts.md` y el paquete `pipeline` aparecen en el README pero NO existen. No los uses.
- No modifiques `.github/copilot-instructions.md` ni los tests existentes.

ALCANCE PERMITIDO
- Modificar solo:
  - `src/main/java/com/ejemplo/usuarios/controller/UsuarioController.java`
  - `src/main/java/com/ejemplo/usuarios/model/RepositorioUsuarios.java`
- Crear solo:
  - `src/test/java/com/ejemplo/usuarios/controller/AltaUsuarioTest.java`
- Justificación del archivo nuevo: no existe ningún test de controlador y los tests existentes no deben modificarse.
- No crear clases de producción nuevas. Si considerás que hace falta una, detenete y justificalo antes de escribir código.

COMPORTAMIENTO EXIGIDO
1. Normalizar nombre y email con `trim()`; el email además a minúsculas con `Locale.ROOT`.
2. Si nombre o email quedan vacíos: mostrar exactamente "El nombre y el email son obligatorios." y no crear nada.
3. Si el email es inválido: mostrar exactamente "El email no es válido." y no crear nada. Regla: una sola `@`, parte local no vacía, dominio con al menos un punto, sin espacios.
4. Si el email ya existe, comparando sin distinguir mayúsculas ni espacios: mostrar exactamente "Ya existe un usuario con ese email." y no crear nada.
5. Si el alta es válida: crear con `repositorio.crear(...)` y mantener el mensaje "Usuario N creado.".
6. Agregar a `RepositorioUsuarios` exactamente `public boolean existeEmail(String email)`. Debe normalizar con `trim()`, comparar sin distinguir mayúsculas y lanzar `IllegalArgumentException` si recibe `null`. No cambiar las firmas existentes.
7. El controlador sigue sin imprimir ni leer directamente: todo pasa por `VistaConsola`.

EJEMPLO DE ESTILO (few-shot; no lo copies como solución)
Así se ve una prueba existente que debe seguir pasando:

```java
@Test
void creaYListaUsuarios() {
    RepositorioUsuarios repositorio = new RepositorioUsuarios();
    Usuario primero = repositorio.crear("Ana", "ana@example.com");
    assertEquals(1, primero.id());
}
```

El test nuevo debe usar el mismo estilo JUnit 5, sin AssertJ, y simular la consola como `AbmUsuarioE2ETest`.

FORMATO DE SALIDA
1. Archivos que vas a tocar y por qué.
2. Diff o bloques completos de los métodos modificados.
3. Test nuevo completo.
4. Comandos ejecutados y salida real.
5. Tabla requisito 1-7 -> cumple (sí/no) -> evidencia.
No agregues teoría, alternativas ni código que no se pueda ejecutar.

VERIFICACIÓN
Ejecutá `.\gradlew.bat test` y mostrá el resultado. Si no podés ejecutarlo, decilo y no afirmes que pasa.

AUTORREVISIÓN
Antes de entregar, revisá tu propia respuesta contra "ALCANCE PERMITIDO" y "COMPORTAMIENTO EXIGIDO". Si algo no cumple, corregilo y mostrá solo la corrección.
````

**Iteración obligatoria de la Fase 2, turno 2:**

````text
Revisá únicamente tu última respuesta contra el prompt anterior. No reescribas todo.

Devolvé:
1. Tabla requisito 1-7 -> cumple (sí/no) -> evidencia con archivo y línea, o salida de test.
2. Lista de archivos tocados y comparación con los permitidos.
3. Cualquier API, clase, dependencia o archivo inventado. Si existe, eliminarlo.
4. Corrección mínima de lo que falle.
5. Salida real de `.\gradlew.bat test`.

No afirmes que compila o que los tests pasan si no ejecutaste el comando.
````

---

## 5. Cómo hacerlo y qué mirar

### Antes de empezar

1. Verificar el estado del repositorio:

```powershell
git status --short
```

2. Si hay cambios sin commitear, no ejecutar el ejercicio sobre ese árbol. Congelar una base limpia con un commit temporal o un clon. Luego crear dos worktrees desde el mismo commit:

```powershell
git worktree add ..\gh-300-u2-baseline -b u2/baseline
git worktree add ..\gh-300-u2-optimizado -b u2/optimizado
```

3. En cada worktree, verificar Java y tests antes de empezar:

```powershell
$env:JAVA_HOME='C:\Program Files\Eclipse Adoptium\jdk-25.0.5.8-hotspot'
.\gradlew.bat test --console=plain
```

Nota: la ruta de `JAVA_HOME` es la observada en este entorno. Si cambia, ajustarla. Si los tests no pasan antes de empezar, detenerse: no es un fallo del prompt.

4. Crear la carpeta de evidencias:

```powershell
New-Item -ItemType Directory -Force .tmp\u2
```

### Fase 1 — Baseline

5. Abrir un chat nuevo. Confirmar que no hay historial de la Fase 2.

6. Pegar exactamente:

```text
@workspace Implementá el alta de usuario en el ABM.
```

7. No adjuntar archivos, no agregar el bloque anti-alucinación y no hacer preguntas de diseño. Si el modelo pregunta, responder `Decidí vos.` y registrar esa intervención.

8. Guardar la respuesta cruda:

```powershell
# Copiar la respuesta completa en:
.tmp/u2/baseline-respuesta.md
```

9. Aplicar literalmente lo que propone en el worktree `gh-300-u2-baseline`. Si no compila, registrar el error y **no corregirlo**: ese es el resultado baseline.

10. Ejecutar y guardar la salida:

```powershell
.\gradlew.bat test --console=plain 2>&1 | Tee-Object .tmp\u2\baseline-test.txt
git diff --stat | Tee-Object .tmp\u2\baseline-diffstat.txt
git diff > .tmp\u2\baseline.patch
```

11. Completar la plantilla de registro del punto 3.

### Fase 2 — Prompt optimizado

12. Abrir un chat nuevo. No reutilizar el chat baseline.

13. Adjuntar con `#file` los seis archivos listados en el punto 4.

14. Pegar el prompt mejorado exacto del punto 4.

15. Guardar la respuesta cruda en `.tmp/u2/optimizado-respuesta.md`.

16. Aplicar el cambio en el worktree `gh-300-u2-optimizado`.

17. Ejecutar los tests:

```powershell
.\gradlew.bat test --console=plain 2>&1 | Tee-Object .tmp\u2\optimizado-test-1.txt
```

18. Si falla, hay un archivo fuera del alcance o se inventó una API, pegar el prompt de iteración del punto 4. Máximo dos iteraciones. Registrar cada una.

19. Guardar el diff final:

```powershell
git diff --stat | Tee-Object .tmp\u2\optimizado-diffstat.txt
git diff > .tmp\u2\optimizado.patch
```

20. Verificar explícitamente que los tests existentes no fueron modificados:

```powershell
git diff -- src/test/java/com/ejemplo/usuarios/AbmUsuarioE2ETest.java src/test/java/com/ejemplo/usuarios/model/RepositorioUsuariosTest.java
```

La salida debe ser vacía.

### Fase 3 — Comparar

21. Pon los dos diffs uno al lado del otro. Si quieres, pide a otra persona que los lea sin decirle cuál corresponde al prompt optimizado y te cuente cuál entiende mejor.

22. Como vimos en esta unidad, un prompt no se evalúa por ser más largo, sino por cómo orienta la respuesta. Mira los diffs y anota, en unas pocas frases, lo que observas:

- **Requisitos:** ¿cuántos de los 7 cumple cada uno? No alcanza con que "parezca que sí"; tiene que estar en el código o en un test.
- **Archivos:** ¿tocó solo los dos permitidos y creó solo el test nuevo? ¿Se fue de mambo con servicios, DTOs o paquetes?
- **Inventos:** ¿apareció Spring, JPA, una base de datos, `buscarPorEmail`, `leerEmail` o cualquier cosa que el repo no tiene?
- **Tests:** ¿compila? ¿Los tests existentes siguen pasando sin haberlos tocado? ¿El test nuevo cubre los casos importantes?
- **Idas y vueltas:** ¿cuántos turnos hicieron falta para llegar a algo que funcione?
- **Tiempo:** ¿cuánto tardaste desde que escribiste el prompt hasta que quedó verificado? El prompt optimizado puede tardar más en escribirse, pero quizás te ahorra correcciones.
- **Lectura:** ¿el diff se entiende de una pasada o hay que descifrar qué hizo?

23. Escribí una conclusión corta. No hace falta declarar un ganador absoluto. Puedes tener en cuenta:

- ¿Con cuál llegaste antes a algo que funciona?
- ¿Cuál se quedó más cerca del repo real?
- ¿Cuál te obligó a corregir menos cosas a mano?
- ¿El tiempo extra de escribir el prompt optimizado se compensó con menos retrabajo?
- ¿La diferencia se ve clara o fue más bien una cuestión de suerte del modelo?

24. Si tienes tiempo, repite cada fase en chats nuevos. Los LLM no son deterministas; una sola prueba sirve como observación, no como regla universal.

---

## Cierre

No hay puntaje: alcanza con conservar las respuestas, el diff y la salida real de las pruebas, y contar qué notaste. La comparación sirve para conversar sobre las técnicas de la unidad, no para demostrar que un prompt siempre gana.

## Nota final

No se modificó ningún archivo del repositorio. El diseño se apoya en los archivos existentes y en las diferencias observadas entre el árbol real y lo que declara el `README.md`. Si el instructor necesitara una implementación desde cero, habría que preparar una rama descartable sin `UsuarioController.alta()`; eso ya no sería el mismo ejercicio baseline y debería documentarse como variante separada.
