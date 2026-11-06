package com.ejemplo.usuarios.model;

/**
 * Un usuario del sistema. Es inmutable: para modificarlo se crea otra instancia.
 */
public record Usuario(int id, String nombre, String email) {

    public Usuario {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }
        if (email == null || email.isBlank()) {
            throw new IllegalArgumentException("El email no puede estar vacío");
        }
    }
}
