package com.restaurante.daos;

import com.restaurante.util.conexionSingleton;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.restaurante.model.Plato;

public class PlatoDAOImpl implements PlatoDAO{

@Override
    public void agregarPlato(Plato plato) {
        String sql = "INSERT INTO plato (nombre, precio, stock) VALUES (?, ?, ?)";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, plato.getNombre());
            stmt.setDouble(2, plato.getPrecio());
            stmt.setInt(3, plato.getStock());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

@Override
    public void actualizarPlato(Plato plato) {
        String sql = "UPDATE plato SET nombre = ?, precio = ?, stock = ? WHERE id = ?";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, plato.getNombre());
            stmt.setDouble(2, plato.getPrecio());
            stmt.setInt(3, plato.getStock());
            stmt.setInt(4, plato.getId());
            stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    @Override
    public void eliminarPlato(int id) {
        String sql = "DELETE FROM plato WHERE id=?";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Plato obtenerPlatoPorId(int id) {
        String sql = "SELECT * FROM plato WHERE id=?";
        try (Connection conn = conexionSingleton.getConexion();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Plato(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Plato> listarPlatos() {
        List<Plato> platos = new ArrayList<>();
        String sql = "SELECT * FROM plato";
        try (Connection conn = conexionSingleton.getConexion();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                platos.add(new Plato(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getInt("stock")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return platos;
    }

}
