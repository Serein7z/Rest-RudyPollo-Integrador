package com.restaurante.controller;

import java.util.List;

import com.restaurante.daos.ClienteDAO;
import com.restaurante.daos.ClienteDAOImpl;
import com.restaurante.model.Cliente;

public class ClienteControlador {
    private final ClienteDAO clienteDAO;

    public ClienteControlador() {
        this.clienteDAO = new ClienteDAOImpl();
    }

    public void agregarCliente(String nombre, String direccion, String telefono) {
        Cliente nuevo = new Cliente(0, nombre, direccion, telefono);
        clienteDAO.agregarCliente(nuevo);
    }

    public void actualizarCliente(int id, String nombre, String direccion, String telefono) {
        Cliente actualizado = new Cliente(id, nombre, direccion, telefono);
        clienteDAO.actualizarCliente(actualizado);
    }

    public void eliminarCliente(int id) {
        clienteDAO.eliminarCliente(id);
    }

    public Cliente obtenerClientePorId(int id) {
        return clienteDAO.obtenerClientePorId(id);
    }

    public List<Cliente> listarClientes() {
        return clienteDAO.listarClientes();
    }
}
