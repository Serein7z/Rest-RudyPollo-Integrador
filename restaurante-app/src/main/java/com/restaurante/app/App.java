package com.restaurante.app;

import com.restaurante.controller.ClienteControlador;
//import com.restaurante.controller.PlatoControlador;
//import com.restaurante.vista.PlatoVista;
import com.restaurante.vista.ClienteVista;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main(String[] args) {

  //      PlatoControlador controlador = new PlatoControlador();
  //    new PlatoVista(controlador);
  //}

      // ⚡ Crear el controlador
        ClienteControlador controlador = new ClienteControlador();

        // 🚀 Abrir la vista
        new ClienteVista(controlador);
}
}
