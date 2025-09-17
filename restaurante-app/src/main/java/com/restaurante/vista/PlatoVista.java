package com.restaurante.vista;

import com.restaurante.controller.PlatoControlador; 
import com.restaurante.model.Plato;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PlatoVista extends JFrame {

    private final PlatoControlador controlador;
    private final JTable tablaPlatos;
    private final DefaultTableModel modeloTabla;

    public PlatoVista(PlatoControlador controlador) {
        this.controlador = controlador;

        setTitle("Gestión de Platos - Restaurante");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Precio", "Stock"}, 0);
        tablaPlatos = new JTable(modeloTabla);
        add(new JScrollPane(tablaPlatos), BorderLayout.CENTER);

        // Botones
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");
        JButton btnEditar = new JButton("Editar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);
        panelBotones.add(btnEditar);
        add(panelBotones, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(e -> {
            String nombre = JOptionPane.showInputDialog(this, "Nombre del plato:");
                    if (nombre == null || nombre.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
                    return;
                }

            String precioStr = JOptionPane.showInputDialog(this, "Precio:");
            String stockStr = JOptionPane.showInputDialog(this, "Stock:");

            try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

                    if (precio <= 0 || stock < 0) {
                    JOptionPane.showMessageDialog(this, "Precio debe ser > 0 y Stock >= 0");
                    return;
                    }

            controlador.agregarPlato(nombre, precio, stock);
            JOptionPane.showMessageDialog(this, "Plato agregado correctamente ✅");
            actualizarTabla();

            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: precio o stock inválido");
        }
        });

        btnEliminar.addActionListener(e -> {
            int fila = tablaPlatos.getSelectedRow();
                if (fila != -1) {
            int id = (int) modeloTabla.getValueAt(fila, 0);

            int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Estás seguro de eliminar este plato?",
            "Confirmar eliminación",
            JOptionPane.YES_NO_OPTION
        );

            if (confirm == JOptionPane.YES_OPTION) {
            controlador.eliminarPlato(id);
            JOptionPane.showMessageDialog(this, "Plato eliminado correctamente ❌");
            actualizarTabla();
            }
                } else {
                JOptionPane.showMessageDialog(this, "Selecciona un plato para eliminar.");
                }
        });

        btnRefrescar.addActionListener(e -> actualizarTabla());

        btnEditar.addActionListener(e -> {
                int fila = tablaPlatos.getSelectedRow();
        if (fila != -1) {
        int id = (int) modeloTabla.getValueAt(fila, 0);

        String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", modeloTabla.getValueAt(fila, 1));
        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.");
            return;
        }

        String precioStr = JOptionPane.showInputDialog(this, "Nuevo precio:", modeloTabla.getValueAt(fila, 2));
        String stockStr = JOptionPane.showInputDialog(this, "Nuevo stock:", modeloTabla.getValueAt(fila, 3));

        try {
            double precio = Double.parseDouble(precioStr);
            int stock = Integer.parseInt(stockStr);

            if (precio <= 0 || stock < 0) {
                JOptionPane.showMessageDialog(this, "Precio debe ser > 0 y Stock >= 0");
                return;
            }

            controlador.actualizarPlato(id, nombre, precio, stock);
            JOptionPane.showMessageDialog(this, "Plato actualizado correctamente ✅");
            actualizarTabla();

            } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: precio o stock inválido");
            }
        } else {
        JOptionPane.showMessageDialog(this, "Selecciona un plato para editar.");
        }
        });

    
        actualizarTabla();

        setVisible(true);
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<Plato> platos = controlador.listarPlatos();
        for (Plato p : platos) {
            modeloTabla.addRow(new Object[]{p.getId(), p.getNombre(), p.getPrecio(), p.getStock()});
        }
    }
}
