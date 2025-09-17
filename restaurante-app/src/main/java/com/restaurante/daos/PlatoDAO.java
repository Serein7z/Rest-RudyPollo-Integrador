package com.restaurante.daos;

import java.util.List;

import com.restaurante.model.Plato;

public interface PlatoDAO {
    void agregarPlato(Plato plato);
    void actualizarPlato(Plato plato);
    void eliminarPlato(int id);
    Plato obtenerPlatoPorId(int id);
    List<Plato> listarPlatos();
}
