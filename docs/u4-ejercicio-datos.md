# Ejercicio U4 — Qué datos compartimos al usar Copilot

## 1. Objetivo

Como vimos en esta unidad, el tratamiento de datos depende de la función de Copilot, el producto y la configuración de la cuenta. Vamos a distinguir el contexto que aportamos para obtener una respuesta de las reglas de retención, y a practicar cómo reducir los datos compartidos.

## 2. Antes de empezar

Lee la [unidad 4: Datos de GitHub Copilot](https://learn.microsoft.com/es-es/training/modules/introduction-prompt-engineering-with-github-copilot/4-github-copilot-data). Las políticas pueden cambiar y variar entre planes y productos: para una decisión real, consulta la información vigente de GitHub Copilot y la configuración de tu organización.

No uses datos de personas reales, tokens, contraseñas, claves, archivos de configuración sensibles ni código confidencial. En esta actividad alcanza con los archivos didácticos del repositorio.

## 3. Actividad: compartir solo el contexto necesario

Abre dos chats nuevos. En el primero, escribe:

```text
En Java, ¿qué suele hacer una clase repositorio? Explica la idea en dos frases.
```

Guarda la respuesta. En el segundo chat, adjunta con `#file` solo `RepositorioUsuarios.java` y pregunta:

```text
Resume las operaciones que aparecen en este archivo y menciona un método por su nombre exacto.
Limítate a lo que muestra el archivo; no supongas qué política de retención aplica.
```

Compara las respuestas en tus notas: ¿qué información agregaste deliberadamente en el segundo chat?, ¿qué dato del archivo aparece en la respuesta?, ¿la respuesta afirma algo que no se puede comprobar mirando el archivo? Esta comparación muestra una diferencia de contexto visible, pero no permite saber por sí sola todo lo que el servicio procesa o conserva.

## 4. Actividad: decidir qué no compartir

Lee cada situación y explica en voz alta o por escrito qué harías y cómo adaptarías el ejemplo para practicar de forma segura:

1. Quieres pedir ayuda con un error, pero el registro contiene el correo de un cliente.
2. El archivo que pensabas adjuntar incluye una clave de acceso.
3. Necesitas mostrar un ejemplo de usuario para probar una validación.
4. Quieres adjuntar todo el proyecto, aunque la pregunta trata de un único método.

Como vimos en esta unidad, aportar contexto puede ayudar a la respuesta, pero no significa que convenga enviar todo. Por ejemplo, puedes reemplazar una identidad real por `ana@example.com`, quitar credenciales y compartir solo el fragmento relevante. Nunca pegues un secreto para “probar” si Copilot lo detecta.

## 5. Actividad: consultar retención y ventana de contexto

Vuelve a la unidad y localiza lo que dice sobre sugerencias de código, Copilot Chat, retención y límites de la ventana de contexto. Después contrasta cualquier afirmación que necesites usar con la información vigente para tu producto o plan. Anota en tus propias palabras:

- qué diferencias de tratamiento menciona Learn entre las experiencias descritas;
- por qué no debemos asumir que todos los productos retienen los datos de la misma forma;
- qué recomienda la unidad cuando una solicitud es demasiado extensa;
- qué fuente consultaste y cuándo, si verificaste una política actual.

Si no puedes confirmar una regla vigente para tu cuenta, dilo claramente; no completes el dato por intuición. La actividad no permite comprobar la retención real del servicio ni inspeccionar el tráfico de red.

## 6. Para cerrar

Guarda las dos respuestas y tus notas. Termina con una recomendación breve para alguien que va a adjuntar contexto a Copilot: qué incluiría, qué excluiría y qué verificaría antes de usarlo en un entorno de trabajo.

Lo importante es justificar tus decisiones con la unidad y las fuentes consultadas.
