# Ejercicio U3 — Seguir una solicitud por el flujo de Copilot

## 1. Objetivo

Como vimos en esta unidad, una solicitud pasa por varias etapas: se recopila contexto, se procesa y filtra, un LLM genera una respuesta y luego esta se procesa y entrega. En esta actividad vamos a observar qué cambia al aportar contexto y a separar esa observación de las etapas internas que solo conocemos por la documentación.

## 2. Antes de empezar

Lee la [unidad 3 del módulo](https://learn.microsoft.com/es-es/training/modules/introduction-prompt-engineering-with-github-copilot/3-github-copilot-user-prompt-process-flow). Ten a mano `src/main/java/com/ejemplo/usuarios/model/RepositorioUsuarios.java`. No hace falta modificar código ni usar `@workspace`.

## 3. Actividad: una misma pregunta, con y sin contexto

La variable que queremos observar es si Copilot recibe el archivo. Por eso, usaremos exactamente la misma pregunta en dos chats nuevos.

**Chat A — sin adjuntar archivos:** pega esta solicitud:

```text
En este proyecto Java, ¿cómo se asigna el identificador cuando se crea un usuario?
Explica qué clase y método revisarías. Si no tienes información suficiente, dilo y no inventes nombres de métodos.
```

Guarda la respuesta. Luego abre un chat nuevo.

**Chat B — con contexto:** adjunta solamente `RepositorioUsuarios.java` con `#file` y pega exactamente la misma solicitud. Guarda también esta respuesta.

Ahora compara las respuestas en tus notas:

- ¿Cuál nombró una clase o método que pudiste verificar en el archivo?
- ¿Qué parte de la respuesta del segundo chat se apoya claramente en el archivo adjunto?
- ¿Copilot hizo alguna suposición incorrecta o dijo que no tenía información suficiente?
- ¿Qué diferencia concreta notas entre las respuestas? Si no hay una diferencia clara, anótalo también.

No hace falta que la respuesta del Chat A sea incorrecta ni que la del Chat B sea perfecta. Registra lo que pasó, no lo que esperabas que pasara.

## 4. Actividad: reconstruir el flujo

Como vimos en la unidad, Microsoft describe etapas que no son todas visibles desde el chat. Dibuja en papel, en un documento o con una herramienta sencilla este recorrido y agrega una explicación breve a cada paso:

```text
Solicitud
→ transmisión y recopilación de contexto
→ filtros descritos en la unidad
→ generación mediante un LLM
→ procesamiento posterior
→ respuesta entregada
```

Junto a cada etapa, marca una de estas opciones:

- **Lo observé:** por ejemplo, escribí el prompt y recibí una respuesta.
- **Lo explica Learn:** la documentación describe la etapa, aunque yo no la vea funcionar.
- **No lo puedo comprobar en esta actividad:** por ejemplo, no tengo telemetría del servidor para saber qué filtro se ejecutó.

Usa la unidad como fuente para el recorrido. No afirmes que se activó un filtro, que el servicio transmitió un dato específico o que el LLM hizo una operación interna concreta solo porque la respuesta cambió.

## 5. Para cerrar

Guarda las dos respuestas y el diagrama. Escribe un párrafo breve que cuente qué cambió al adjuntar el archivo, cómo relacionas esa diferencia con la recopilación de contexto que vimos en esta unidad y qué partes del flujo no pudiste observar directamente.

## 6. Límites

- La actividad no permite observar HTTPS, filtros del proxy, filtros de toxicidad, coincidencias con código público ni decisiones del servidor.
- Adjuntar `#file` aporta ese archivo a la interacción, pero no garantiza que Copilot use cada línea ni revela todo el contexto que pudo considerar.
- No adjuntes información personal, secretos ni archivos sensibles. No modifiques el repositorio.
