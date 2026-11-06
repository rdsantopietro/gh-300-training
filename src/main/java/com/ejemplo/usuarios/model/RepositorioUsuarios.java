package com.ejemplo.usuarios.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Almacenamiento en memoria de los usuarios. Forma parte del modelo: no sabe
 * nada de consola ni de menús.
 */
public class RepositorioUsuarios {

    private final Map<Integer, Usuario> usuarios = new LinkedHashMap<>();
    private int siguienteId = 1;

    public Usuario crear(String nombre, String email) {
        Usuario usuario = new Usuario(siguienteId++, nombre, email);
        usuarios.put(usuario.id(), usuario);
        return usuario;
    }

    public List<Usuario> listar() {
        return new ArrayList<>(usuarios.values());
    }

    public Optional<Usuario> buscar(int id) {
        return Optional.ofNullable(usuarios.get(id));
    }

    public boolean actualizar(int id, String nombre, String email) {
        if (!usuarios.containsKey(id)) {
            return false;
        }
        usuarios.put(id, new Usuario(id, nombre, email));
        return true;
    }

    public boolean eliminar(int id) {
        return usuarios.remove(id) != null;
    }
}
