package com.restaurante;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.restaurante.daos.PlatoDAOImpl;
import com.restaurante.model.Plato;

/**
 * Unit test for simple App.
 */

public class PlatoDAOImplTest {

    private PlatoDAOImpl platoDAO;

    @Before
    public void setUp() {
        platoDAO = new PlatoDAOImpl();
    }

    @Test
    public void testAgregarYListarPlato() {
        Plato plato = new Plato(0, "Test Plato", 15.5, 10);
        platoDAO.agregarPlato(plato);

        List<Plato> platos = platoDAO.listarPlatos();
        assertTrue(platos.stream().anyMatch(p -> "Test Plato".equals(p.getNombre())));
    }

    @Test
    public void testActualizarPlato() {
        // Insertamos primero
        Plato plato = new Plato(0, "Plato Actualizar", 20.0, 5);
        platoDAO.agregarPlato(plato);

        // Obtenemos el ID recién insertado
        List<Plato> platos = platoDAO.listarPlatos();
        Plato ultimo = platos.get(platos.size() - 1);

        // Actualizamos
        ultimo.setNombre("Plato Actualizado");
        ultimo.setPrecio(25.0);
        ultimo.setStock(7);
        platoDAO.actualizarPlato(ultimo);

        // Volvemos a listar
        List<Plato> actualizados = platoDAO.listarPlatos();
        assertTrue(actualizados.stream().anyMatch(p -> "Plato Actualizado".equals(p.getNombre())));
    }

    @Test
    public void testEliminarPlato() {
        Plato plato = new Plato(0, "Plato Eliminar", 12.0, 3);
        platoDAO.agregarPlato(plato);

        List<Plato> platos = platoDAO.listarPlatos();
        Plato ultimo = platos.get(platos.size() - 1);

        platoDAO.eliminarPlato(ultimo.getId());

        List<Plato> despues = platoDAO.listarPlatos();
        assertFalse(despues.stream().anyMatch(p -> "Plato Eliminar".equals(p.getNombre())));
    }
}
