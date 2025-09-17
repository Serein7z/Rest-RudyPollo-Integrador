package com.restaurante;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.restaurante.daos.ClienteDAOImpl;
import com.restaurante.model.Cliente;

public class ClienteDAOImplTest {

private ClienteDAOImpl clienteDAO;

    @Before
    public void setUp() {
        clienteDAO = new ClienteDAOImpl();
    }

    @Test
    public void testAgregarYListarCliente() {
        Cliente cliente = new Cliente(0, "Test Cliente", "Calle Falsa 123", "987654321");
        clienteDAO.agregarCliente(cliente);

        List<Cliente> clientes = clienteDAO.listarClientes();
        assertTrue(clientes.stream().anyMatch(c -> "Test Cliente".equals(c.getNombre())));
    }

    @Test
    public void testActualizarCliente() {
        Cliente cliente = new Cliente(0, "Cliente Editar", "Av Siempre Viva", "999888777");
        clienteDAO.agregarCliente(cliente);

        List<Cliente> clientes = clienteDAO.listarClientes();
        Cliente ultimo = clientes.get(clientes.size() - 1);

        ultimo.setNombre("Cliente Actualizado");
        ultimo.setDireccion("Nueva Direccion 456");
        ultimo.setTelefono("123456789");
        clienteDAO.actualizarCliente(ultimo);

        List<Cliente> actualizados = clienteDAO.listarClientes();
        assertTrue(actualizados.stream().anyMatch(c -> "Cliente Actualizado".equals(c.getNombre())));
    }

    @Test
    public void testEliminarCliente() {
        Cliente cliente = new Cliente(0, "Cliente Eliminar", "Direccion X", "111222333");
        clienteDAO.agregarCliente(cliente);

        List<Cliente> clientes = clienteDAO.listarClientes();
        Cliente ultimo = clientes.get(clientes.size() - 1);

        clienteDAO.eliminarCliente(ultimo.getId());

        List<Cliente> despues = clienteDAO.listarClientes();
        assertFalse(despues.stream().anyMatch(c -> "Cliente Eliminar".equals(c.getNombre())));
    }
}
