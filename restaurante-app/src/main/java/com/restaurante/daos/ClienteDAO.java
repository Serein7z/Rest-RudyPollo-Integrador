package com.restaurante.daos;

import java.util.List;

import com.restaurante.model.Cliente;

public interface ClienteDAO {
void agregarCliente(Cliente cliente);
    void actualizarCliente(Cliente cliente);
    void eliminarCliente(int id);
    Cliente obtenerClientePorId(int id);
    List<Cliente> listarClientes();
}
