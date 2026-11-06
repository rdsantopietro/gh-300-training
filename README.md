# gh-300-training

Proyecto Java mínimo para practicar prompting: un **ABM de usuarios** por consola
con arquitectura **MVC**, sin frameworks y sin dependencias en producción.
Ideal para probar prompts sobre un código chico y fácil de explicar.
Los prompts listos para copiar están en [`docs/prompts.md`](docs/prompts.md).

## Requisitos

- Java 25 (JDK).
- Gradle Wrapper incluido: no hace falta instalar Gradle.

## Comandos

```bash
./gradlew run      # inicia la aplicación
./gradlew test     # ejecuta los tests
```

En Windows:

```powershell
.\gradlew.bat run
.\gradlew.bat test
```

## Qué hace

Menú de consola con las operaciones clásicas de un ABM:

1. Alta de usuario (nombre y email).
2. Listado de usuarios.
3. Modificación por id.
4. Baja por id.
0. Salir.

Los datos viven en memoria: al cerrar la aplicación se pierden.

## Estructura

```text
src/main/java/com/ejemplo/usuarios/
├── Main.java                     # conecta modelo, vista y controlador
├── model/
│   ├── Usuario.java              # record inmutable
│   └── RepositorioUsuarios.java  # almacenamiento en memoria
├── view/
│   └── VistaConsola.java         # impresión y lectura por teclado
├── controller/
│   └── UsuarioController.java    # orquesta el flujo del menú
└── pipeline/
    ├── PipelineSolicitud.java    # encadena las etapas de la unidad 3
    ├── SanitizarSolicitud.java
    ├── FiltroProxy.java
    ├── FiltroToxicidad.java
    ├── GeneradorLLMStub.java
    ├── ValidadorCalidad.java
    ├── FiltroCodigoPublico.java
    └── EntregaSugerencia.java

src/test/java/com/ejemplo/usuarios/
 ├── model/RepositorioUsuariosTest.java  # pruebas unitarias
 └── AbmUsuarioE2ETest.java             # flujo completo de punta a punta
```

## Tests

- `RepositorioUsuariosTest`: alta, listado, modificación, baja y validaciones.
- `AbmUsuarioE2ETest`: simula la entrada de consola y recorre el ABM completo
  (alta → listado → modificación → baja) verificando el estado final.

## Ejercicios alineados con el módulo Learn

Este repositorio contiene ejercicios de práctica para el módulo **"Introducción a la ingeniería de solicitudes con GitHub Copilot"** (Microsoft Learn). Cada archivo corresponde a una unidad práctica:

- [`docs/u2-ejercicio.md`](docs/u2-ejercicio.md) — Unidad 2: fundamentos y mejores técnicas de prompt engineering (4S)
- [`docs/u3-ejercicio-flujo.md`](docs/u3-ejercicio-flujo.md) — Unidad 3: flujo de procesamiento de solicitud de usuario de GitHub Copilot (contexto vs contexto adjunto)
- [`docs/u4-ejercicio-datos.md`](docs/u4-ejercicio-datos.md) — Unidad 4: datos y privacidad en GitHub Copilot (retención, ventana de contexto, decisión segura)
- [`docs/u5-ejercicio-llm.md`](docs/u5-ejercicio-llm.md) — Unidad 5: modelos de lenguaje grande (LLM), ajuste fino y LoRA

U1 es introductoria; U6 y U7 son evaluación y resumen, sin laboratorios designados.

## Pipeline de la unidad 3

El paquete `com.ejemplo.usuarios.pipeline` simula, sin red y de forma
determinística, el flujo de una solicitud en GitHub Copilot: sanitización,
filtro de proxy, toxicidad, generación, validación de calidad, filtro de código
publico y entrega con traza.

```powershell
.\gradlew.bat test
```

El detalle de qué hace Copilot de verdad, qué es `[ADMIN]` y qué se puede
observar desde la cuenta está en `docs/u3-flujo-real.md`.

## Notas

- `build.gradle` fija el toolchain de Java 25 y usa JUnit 5 solo para tests.
- `gradle.properties` define `-Dfile.encoding=COMPAT` porque la ruta del perfil
  en Windows contiene acentos y los `@argfile` del launcher se leen en la
  codificación nativa. En una ruta sin acentos puede quitarse.