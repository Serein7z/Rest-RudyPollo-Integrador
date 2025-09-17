package com.restaurante.vista;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import com.restaurante.controller.ClienteControlador;
import com.restaurante.model.Cliente;

public class ClienteVista  extends JFrame{
    private final ClienteControlador controlador;
    private final JTable tablaClientes;
    private final DefaultTableModel modeloTabla;

    public ClienteVista(ClienteControlador controlador) {
        this.controlador = controlador;

        setTitle("Gestión de Clientes - Restaurante");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Dirección", "Teléfono"}, 0);
        tablaClientes = new JTable(modeloTabla);
        add(new JScrollPane(tablaClientes), BorderLayout.CENTER);

       
        JPanel panelBotones = new JPanel();
        JButton btnAgregar = new JButton("Agregar");
        JButton btnEditar = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnRefrescar = new JButton("Refrescar");

        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);
        panelBotones.add(btnRefrescar);
        add(panelBotones, BorderLayout.SOUTH);

    
        btnAgregar.addActionListener(e -> agregarCliente());
        btnEditar.addActionListener(e -> editarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());
        btnRefrescar.addActionListener(e -> actualizarTabla());

      
        actualizarTabla();

        setVisible(true);
    }

    private void agregarCliente() {
        String nombre = JOptionPane.showInputDialog(this, "Nombre del cliente:");
        String direccion = JOptionPane.showInputDialog(this, "Dirección:");
        String telefono = JOptionPane.showInputDialog(this, "Teléfono:");

        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
        return;
        }
        if (telefono == null || !telefono.matches("\\d{7,9}")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ser numérico y tener entre 7 y 9 dígitos");
        return;
        }

        controlador.agregarCliente(nombre.trim(), direccion, telefono.trim());
        actualizarTabla();
        }

    private void editarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila != -1) {
        int id = (int) modeloTabla.getValueAt(fila, 0);

        String nombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", modeloTabla.getValueAt(fila, 1));
        String direccion = JOptionPane.showInputDialog(this, "Nueva dirección:", modeloTabla.getValueAt(fila, 2));
        String telefono = JOptionPane.showInputDialog(this, "Nuevo teléfono:", modeloTabla.getValueAt(fila, 3));

        if (nombre == null || nombre.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío");
            return;
        }
        if (telefono == null || !telefono.matches("\\d{7,9}")) {
            JOptionPane.showMessageDialog(this, "El teléfono debe ser numérico y tener entre 7 y 9 dígitos");
            return;
        }

        controlador.actualizarCliente(id, nombre.trim(), direccion, telefono.trim());
        actualizarTabla();
        } else {
        JOptionPane.showMessageDialog(this, "Seleccione un cliente para editar");
        }
    }

    private void eliminarCliente() {
        int fila = tablaClientes.getSelectedRow();
        if (fila != -1) {
            int id = (int) modeloTabla.getValueAt(fila, 0);
            controlador.eliminarCliente(id);
            actualizarTabla();
        } else {
            JOptionPane.showMessageDialog(this, "Seleccione un cliente para eliminar");
        }
    }

    public void actualizarTabla() {
        modeloTabla.setRowCount(0);
        List<Cliente> clientes = controlador.listarClientes();
        for (Cliente c : clientes) {
            modeloTabla.addRow(new Object[]{c.getId(), c.getNombre(), c.getDireccion(), c.getTelefono()});
        }
    }
}
