package com.restaurante.daos;

import com.restaurante.model.Cliente;
import com.restaurante.util.conexionSingleton;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ClienteDAOImpl implements ClienteDAO {

    @Override
    public void agregarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes(nombre, direccion, telefono) VALUES (?, ?, ?)";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        String sql = "UPDATE clientes SET nombre=?, direccion=?, telefono=? WHERE id=?";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNombre());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefono());
            stmt.setInt(4, cliente.getId());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarCliente(int id) {
        String sql = "DELETE FROM clientes WHERE id=?";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Cliente obtenerClientePorId(int id) {
        String sql = "SELECT * FROM clientes WHERE id=?";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        try (Connection conn = conexionSingleton.getConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                lista.add(new Cliente(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("direccion"),
                        rs.getString("telefono")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

}
