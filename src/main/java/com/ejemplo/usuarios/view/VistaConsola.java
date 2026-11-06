package com.ejemplo.usuarios.view;

import com.ejemplo.usuarios.model.Usuario;

import java.util.List;
import java.util.Scanner;

/**
 * Todo lo que se muestra al usuario y toda la lectura de datos pasa por aquí.
 */
public class VistaConsola {

    private final Scanner scanner;

    public VistaConsola(Scanner scanner) {
        this.scanner = scanner;
    }

    public void mostrarMenu() {
        System.out.println();
        System.out.println("=== ABM de usuarios ===");
        System.out.println("1. Alta de usuario");
        System.out.println("2. Listar usuarios");
        System.out.println("3. Modificar usuario");
        System.out.println("4. Baja de usuario");
        System.out.println("0. Salir");
    }

    public String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine().trim();
    }

    public int leerEntero(String mensaje) {
        while (true) {
            String texto = leerTexto(mensaje);
            try {
                return Integer.parseInt(texto);
            } catch (NumberFormatException e) {
                mostrarMensaje("Ingresa un número válido.");
            }
        }
    }

    public void mostrarUsuarios(List<Usuario> usuarios) {
        if (usuarios.isEmpty()) {
            mostrarMensaje("No hay usuarios registrados.");
            return;
        }
        for (Usuario usuario : usuarios) {
            System.out.printf("%d. %s <%s>%n", usuario.id(), usuario.nombre(), usuario.email());
        }
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
