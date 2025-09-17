package com.restaurante.controller;

import com.restaurante.daos.PlatoDAO;
import com.restaurante.daos.PlatoDAOImpl;
import com.restaurante.model.Plato;

import java.util.List;

public class PlatoControlador {
    private final PlatoDAO platoDAO = new PlatoDAOImpl();

    public void agregarPlato(String nombre, double precio, int stock) {
    Plato nuevo = new Plato(0, nombre, precio, stock);
    platoDAO.agregarPlato(nuevo);
}

    public void eliminarPlato(int id) {
        platoDAO.eliminarPlato(id);
    }

    public List<Plato> listarPlatos() {
        return platoDAO.listarPlatos();
    }
    
    public void actualizarPlato(int id, String nombre, double precio, int stock) {
    Plato actualizado = new Plato(id, nombre, precio, stock);
    platoDAO.actualizarPlato(actualizado);
}
}