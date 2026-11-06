package com.ejemplo.usuarios;

import com.ejemplo.usuarios.controller.UsuarioController;
import com.ejemplo.usuarios.model.RepositorioUsuarios;
import com.ejemplo.usuarios.model.Usuario;
import com.ejemplo.usuarios.view.VistaConsola;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Prueba de punta a punta: recorre el ABM completo simulando lo que escribe
 * el usuario en la consola y verificando el estado final del repositorio.
 */
class AbmUsuarioE2ETest {

    @Test
    void recorreAltaListadoModificacionYBaja() {
        String entrada = String.join("\n",
                "1", "Ana", "ana@example.com",
                "1", "Luis", "luis@example.com",
                "2",
                "3", "1", "Ana María", "ana.maria@example.com",
                "2",
                "4", "1",
                "2",
                "0",
                "");

        ByteArrayOutputStream salida = new ByteArrayOutputStream();
        PrintStream salidaOriginal = System.out;
        System.setOut(new PrintStream(salida, true, StandardCharsets.UTF_8));
        try {
            RepositorioUsuarios repositorio = new RepositorioUsuarios();
            VistaConsola vista = new VistaConsola(new Scanner(
                    new ByteArrayInputStream(entrada.getBytes(StandardCharsets.UTF_8)),
                    StandardCharsets.UTF_8));

            new UsuarioController(repositorio, vista).iniciar();

            List<Usuario> restantes = repositorio.listar();
            assertEquals(1, restantes.size());
            assertEquals("Luis", restantes.getFirst().nombre());

            String texto = salida.toString(StandardCharsets.UTF_8);
            assertTrue(texto.contains("Usuario 1 creado."));
            assertTrue(texto.contains("Ana María <ana.maria@example.com>"));
            assertTrue(texto.contains("Usuario 1 actualizado."));
            assertTrue(texto.contains("Usuario 1 eliminado."));
            assertTrue(texto.contains("Hasta luego."));
        } finally {
            System.setOut(salidaOriginal);
        }
    }
}
