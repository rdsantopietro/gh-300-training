package com.ejemplo.usuarios;

import com.ejemplo.usuarios.controller.UsuarioController;
import com.ejemplo.usuarios.model.RepositorioUsuarios;
import com.ejemplo.usuarios.view.VistaConsola;

import java.util.Scanner;

/**
 * Punto de entrada: aquí se conectan modelo, vista y controlador.
 */
public final class Main {

    private Main() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VistaConsola vista = new VistaConsola(scanner);
        RepositorioUsuarios repositorio = new RepositorioUsuarios();

        new UsuarioController(repositorio, vista).iniciar();
    }
}
