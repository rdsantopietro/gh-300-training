package com.ejemplo.usuarios.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RepositorioUsuariosTest {

    @Test
    void creaYListaUsuarios() {
        RepositorioUsuarios repositorio = new RepositorioUsuarios();

        Usuario primero = repositorio.crear("Ana", "ana@example.com");
        Usuario segundo = repositorio.crear("Luis", "luis@example.com");

        assertEquals(1, primero.id());
        assertEquals(2, segundo.id());
        assertEquals(2, repositorio.listar().size());
    }

    @Test
    void actualizaUnUsuarioExistente() {
        RepositorioUsuarios repositorio = new RepositorioUsuarios();
        Usuario usuario = repositorio.crear("Ana", "ana@example.com");

        assertTrue(repositorio.actualizar(usuario.id(), "Ana María", "ana.maria@example.com"));

        Usuario actualizado = repositorio.buscar(usuario.id()).orElseThrow();
        assertEquals("Ana María", actualizado.nombre());
        assertEquals("ana.maria@example.com", actualizado.email());
    }

    @Test
    void noActualizaUnUsuarioInexistente() {
        RepositorioUsuarios repositorio = new RepositorioUsuarios();

        assertFalse(repositorio.actualizar(99, "Nadie", "nadie@example.com"));
    }

    @Test
    void eliminaUnUsuario() {
        RepositorioUsuarios repositorio = new RepositorioUsuarios();
        Usuario usuario = repositorio.crear("Ana", "ana@example.com");

        assertTrue(repositorio.eliminar(usuario.id()));
        assertTrue(repositorio.buscar(usuario.id()).isEmpty());
    }

    @Test
    void rechazaDatosObligatoriosVacios() {
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1, "   ", "ana@example.com"));
        assertThrows(IllegalArgumentException.class, () -> new Usuario(1, "Ana", "   "));
    }
}
