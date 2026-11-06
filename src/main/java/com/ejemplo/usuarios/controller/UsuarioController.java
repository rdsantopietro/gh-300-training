package com.ejemplo.usuarios.controller;

import com.ejemplo.usuarios.model.RepositorioUsuarios;
import com.ejemplo.usuarios.model.Usuario;
import com.ejemplo.usuarios.view.VistaConsola;

/**
 * Coordina el modelo y la vista. No imprime ni lee directamente: todo pasa
 * por la vista.
 */
public class UsuarioController {

    private final RepositorioUsuarios repositorio;
    private final VistaConsola vista;

    public UsuarioController(RepositorioUsuarios repositorio, VistaConsola vista) {
        this.repositorio = repositorio;
        this.vista = vista;
    }

    public void iniciar() {
        boolean salir = false;
        while (!salir) {
            vista.mostrarMenu();
            int opcion = vista.leerEntero("Opción: ");
            switch (opcion) {
                case 1 -> alta();
                case 2 -> listar();
                case 3 -> modificar();
                case 4 -> baja();
                case 0 -> salir = true;
                default -> vista.mostrarMensaje("Opción no válida.");
            }
        }
        vista.mostrarMensaje("Hasta luego.");
    }

    private void alta() {
        String nombre = vista.leerTexto("Nombre: ");
        String email = vista.leerTexto("Email: ");
        if (nombre.isEmpty() || email.isEmpty()) {
            vista.mostrarMensaje("El nombre y el email son obligatorios.");
            return;
        }
        Usuario usuario = repositorio.crear(nombre, email);
        vista.mostrarMensaje("Usuario " + usuario.id() + " creado.");
    }

    private void listar() {
        vista.mostrarUsuarios(repositorio.listar());
    }

    private void modificar() {
        int id = vista.leerEntero("Id del usuario a modificar: ");
        if (repositorio.buscar(id).isEmpty()) {
            vista.mostrarMensaje("No existe un usuario con id " + id + ".");
            return;
        }
        String nombre = vista.leerTexto("Nuevo nombre: ");
        String email = vista.leerTexto("Nuevo email: ");
        if (nombre.isEmpty() || email.isEmpty()) {
            vista.mostrarMensaje("El nombre y el email son obligatorios.");
            return;
        }
        repositorio.actualizar(id, nombre, email);
        vista.mostrarMensaje("Usuario " + id + " actualizado.");
    }

    private void baja() {
        int id = vista.leerEntero("Id del usuario a eliminar: ");
        if (repositorio.eliminar(id)) {
            vista.mostrarMensaje("Usuario " + id + " eliminado.");
        } else {
            vista.mostrarMensaje("No existe un usuario con id " + id + ".");
        }
    }
}
