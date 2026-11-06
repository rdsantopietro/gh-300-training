# Instrucciones para GitHub Copilot en este repositorio

Estas instrucciones son contexto permanente para Copilot Chat: son un ejemplo
real del principio **Surround**. Al agregar o cambiar reglas aquí, se observa
cómo cambian las sugerencias sin tocar el prompt de cada ejercicio.

## Estilo de código

- Python 3.10+.
- Type hints en todas las funciones públicas.
- Docstrings en español, estilo Google (`Args:`, `Returns:`, `Raises:`).
- Nombres de funciones y variables en español, `snake_case`.
- Sin dependencias externas en el código de los laboratorios; solo `pytest` en las pruebas.
- Sin `print` ni código de depuración en las funciones entregables.

## Manejo de errores

- Validar los argumentos al inicio de la función.
- Lanzar `ValueError` con un mensaje claro cuando el argumento no sea válido.
- No silenciar excepciones con `except Exception: pass`.

## Seguridad

- Nunca almacenar contraseñas en texto plano.
- No inventar primitivas criptográficas: usar `hashlib` y `secrets` de la biblioteca estándar.
- Validar y normalizar toda entrada que venga del usuario.

## Pruebas

- Cada función nueva debe poder probarse con `pytest`.
- Preferir pruebas parametrizadas para los casos borde.
- No modificar los archivos `test_*.py` para hacer que pasen: si fallan, se corrige la implementación.

## Documentación

- Actualizar el `RESULTADOS.md` del laboratorio luego de cada experimento.
- Responder en español neutro y sin emojis.
