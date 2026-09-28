/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.uthh.edd.unidad1.tda;

/**
 *
 * @author melih
 */
/**
 * Autor: Hernandez Hernandez Jesus Meliton
 * Matrícula: 20251054
 * Descripción: Interfaz gráfica interactiva de escritorio para gestionar el TDA de Combos de Restaurante.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AppRestauranteGUI extends JFrame {
    // TDA Principal que actuará como la orden completa o el "Combo Maestro"
    private ComboProducto comboPrincipal;

    // Componentes Gráficos
    private JTextField txtId, txtNombre, txtPrecio;
    private JTextArea txtMenuArea;
    private JLabel lblPrecioFinal;

    public AppRestauranteGUI() {
        // Inicializamos nuestro paquete principal del menú
        comboPrincipal = new ComboProducto("MENU-01", "ORDEN RESTAURANTE", 0.0);

        // Configuración de la ventana
        setTitle("Sistema de Gestión de Menú y Combos - TDA");
        setSize(580, 520);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Encabezado obligatorio de identificación de Autor 
        JPanel panelAutor = new JPanel();
        panelAutor.setBackground(new Color(230, 126, 34)); // Color naranja gastronómico
        JLabel lblAutor = new JLabel("Alumno: Hernandez Hernandez Jesus Meliton | Matrícula: 20251054");
        lblAutor.setForeground(Color.WHITE);
        lblAutor.setFont(new Font("Arial", Font.BOLD, 13));
        panelAutor.add(lblAutor);
        add(panelAutor, BorderLayout.NORTH);

        // Formulario Central para agregar elementos 
        JPanel panelFormulario = new JPanel(new GridLayout(4, 2, 8, 8));
        panelFormulario.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        panelFormulario.add(new JLabel("Código ID del Producto/Combo:"));
        txtId = new JTextField();
        panelFormulario.add(txtId);

        panelFormulario.add(new JLabel("Nombre del Producto (Ej: Hamburguesa, Refresco):"));
        txtNombre = new JTextField();
        panelFormulario.add(txtNombre);

        panelFormulario.add(new JLabel("Precio Base ($):"));
        txtPrecio = new JTextField();
        panelFormulario.add(txtPrecio);

        JButton btnInsertar = new JButton("Agregar al Menú/Producto");
        btnInsertar.setFont(new Font("Arial", Font.BOLD, 12));
        panelFormulario.add(btnInsertar);

        add(panelFormulario, BorderLayout.CENTER);

        // Panel de Resultados e Historial de la Estructura 
        JPanel panelResultados = new JPanel(new BorderLayout(5, 5));
        panelResultados.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        txtMenuArea = new JTextArea(12, 45);
        txtMenuArea.setEditable(false);
        txtMenuArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scroll = new JScrollPane(txtMenuArea);
        panelResultados.add(new JLabel("Desglose del TDA Combo (Estructura de Datos):"), BorderLayout.NORTH);
        panelResultados.add(scroll, BorderLayout.CENTER);

        lblPrecioFinal = new JLabel("Costo Total de la Orden (Recursivo): $0.00");
        lblPrecioFinal.setFont(new Font("Arial", Font.BOLD, 15));
        lblPrecioFinal.setForeground(new Color(211, 84, 0));
        panelResultados.add(lblPrecioFinal, BorderLayout.SOUTH);

        add(panelResultados, BorderLayout.SOUTH);

        // Gestión de Eventos del Botón 
        btnInsertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarItemEnCombo();
            }
        });

        actualizarPanelVisual();
    }
    
    // Extrae la información, valida e interactúa con los procesos del TDA.
     
    private void registrarItemEnCombo() {
        try {
            String id = txtId.getText();
            String nombre = txtNombre.getText();
            double precio = Double.parseDouble(txtPrecio.getText());

            // Instanciamos el TDA aplicando herencia y encapsulamiento
            ComboProducto nuevoItem = new ComboProducto(id, nombre, precio);

            // Invocación al método de lógica de negocio del TDA
            comboPrincipal.agregarAlCombo(nuevoItem);

            // Limpieza de campos
            txtId.setText("");
            txtNombre.setText("");
            txtPrecio.setText("");

            actualizarPanelVisual();
            JOptionPane.showMessageDialog(this, "Producto integrado al TDA de forma correcta.");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor introduce un número decimal válido para el precio.", "Error de Formato", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Validación del TDA", JOptionPane.ERROR_MESSAGE);
        }
    }

     // Sincroniza los componentes visuales mediante los métodos de procesamiento recursivo.
     
    private void actualizarPanelVisual() {
        StringBuilder sb = new StringBuilder();
        sb.append(comboPrincipal.obtenerResumenMenu()).append("\n");

        for (ComboProducto item : comboPrincipal.getElementosIncluidos()) {
            sb.append("   ├── ").append(item.obtenerResumenMenu()).append("\n");
        }

        txtMenuArea.setText(sb.toString());

        // Invocación del algoritmo recursivo del TDA
        double costoTotal = comboPrincipal.calcularPrecioTotalRecursivo();
        lblPrecioFinal.setText(String.format("Costo Total de la Orden (Recursivo): $%.2f", costoTotal));
    }
    
     // Hilo de inicio de la aplicación Swing de escritorio.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AppRestauranteGUI().setVisible(true);
        });
    }
}