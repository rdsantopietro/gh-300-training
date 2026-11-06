# gh-300-training

Proyecto Java mínimo para practicar prompting: un **ABM de usuarios** por consola
con arquitectura **MVC**, sin frameworks y sin dependencias en producción.
Ideal para probar prompts sobre un código chico y fácil de explicar.

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
└── controller/
    └── UsuarioController.java    # orquesta el flujo del menú

src/test/java/com/ejemplo/usuarios/
├── model/RepositorioUsuariosTest.java  # pruebas unitarias
└── AbmUsuarioE2ETest.java             # flujo completo de punta a punta
```

## Tests

- `RepositorioUsuariosTest`: alta, listado, modificación, baja y validaciones.
- `AbmUsuarioE2ETest`: simula la entrada de consola y recorre el ABM completo
  (alta → listado → modificación → baja) verificando el estado final.

## Notas

- `build.gradle` fija el toolchain de Java 25 y usa JUnit 5 solo para tests.
- `gradle.properties` define `-Dfile.encoding=COMPAT` porque la ruta del perfil
  en Windows contiene acentos y los `@argfile` del launcher se leen en la
  codificación nativa. En una ruta sin acentos puede quitarse.
