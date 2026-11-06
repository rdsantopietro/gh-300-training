# Ejercicio U5 — De una instrucción a una respuesta: el papel de los LLM

## 1. Objetivo

Como vimos en esta unidad, GitHub Copilot usa modelos de lenguaje grande (LLM) para generar sugerencias a partir de una solicitud y del contexto disponible. También vimos el ajuste fino y LoRA. En esta actividad exploraremos esas ideas con un ejemplo pequeño, sin intentar entrenar un modelo ni adivinar cómo está configurada nuestra cuenta.

## 2. Antes de empezar

Lee la [unidad 5: Modelos de lenguaje grande (LLM) de GitHub Copilot](https://learn.microsoft.com/es-es/training/modules/introduction-prompt-engineering-with-github-copilot/5-github-copilot-large-language-models), prestando atención a qué es un LLM, cómo usa el contexto y en qué consiste LoRA.

## 3. Actividad: cómo cambia una respuesta con contexto

Usaremos el mismo pedido en dos chats nuevos para que la diferencia sea clara. No escribas código en el proyecto: solo copia las respuestas en tus notas.

**Chat A — sin código adjunto:** envía este prompt:

```text
Escribe en Java un método llamado saludo que reciba un nombre y devuelva un saludo breve.
Explica cualquier supuesto que tengas que hacer.
```

Guarda la respuesta y abre un chat nuevo.

**Chat B — con contexto:** antes de enviar el mismo prompt, incluye este fragmento como contexto (puedes pegarlo en el chat o abrirlo en un archivo):

```java
public String saludo(String nombre) {
    // TODO: devolver un saludo breve
}
```

Envía exactamente el mismo prompt del Chat A y guarda la respuesta. Ahora mira ambas: ¿cuál respetó la firma ya escrita?, ¿qué supuestos hizo cada una sobre el texto del saludo?, ¿el segundo resultado fue necesariamente mejor o solo más ajustado a la firma? No hay una respuesta que debas obtener: registra lo que realmente generó Copilot.

Relaciona lo observado con la unidad: el LLM genera una respuesta usando patrones aprendidos y el contexto que tiene disponible; el ejemplo no permite ver sus datos de entrenamiento ni todos sus procesos internos.

## 4. Actividad: explicar ajuste fino y LoRA con un ejemplo

Imagina que un equipo quiere adaptar un modelo de lenguaje preentrenado para que ayude con tareas de un dominio específico. Con la explicación de la unidad como referencia, cuéntale a otra persona:

- qué significa adaptar un modelo preentrenado mediante ajuste fino;
- qué cambia en LoRA respecto de ajustar todos los parámetros;
- por qué añadir componentes entrenables más pequeños puede ahorrar recursos;
- qué cosas no podemos deducir sobre el modelo concreto que responde en nuestra sesión de Copilot.

Puedes usar una analogía propia. Por ejemplo, piensa en conservar un manual base y añadirle un suplemento especializado. Explica también dónde deja de servir la comparación: el modelo no es literalmente un libro y LoRA es una técnica matemática de adaptación.

## 5. Actividad: detectar conclusiones apresuradas

Lee estas frases y decide cuáles están respaldadas por la unidad y cuáles necesitarían otra fuente o no se pueden concluir con esta actividad. Explica brevemente por qué:

- “El contexto puede influir en la sugerencia que genera el LLM”.
- “Copilot vuelve a entrenar todos sus parámetros cada vez que envío un prompt”.
- “LoRA añade componentes entrenables más pequeños sin modificar el modelo original, según la explicación de Learn”.
- “Comparar dos respuestas me permite saber qué modelo o método de ajuste usa mi cuenta”.

## 6. Para cerrar

Guarda las dos respuestas de Copilot y escribe un párrafo breve que una las tres ideas de la unidad: el LLM genera, el contexto condiciona la respuesta y las técnicas de adaptación ajustan modelos para tareas. Indica también qué conclusión de tu experimento es observable y qué afirmación requeriría información adicional.

Explica las ideas con tus palabras y evita atribuir a la prueba algo que no demuestra.
