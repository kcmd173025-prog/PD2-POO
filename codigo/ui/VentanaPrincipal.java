package ui;
import negocios.GestorStands;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VentanaPrincipal extends JFrame {
    private GestorStands gestor;
    private PanelStands panelStands;
    public VentanaPrincipal(GestorStands gestor) {
        this.gestor = gestor;
        setTitle("Sistema de Stands - C\u00E1lculo Din\u00E1mico");
        setSize(880, 680);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        panelStands = new PanelStands();
        add(new JScrollPane(panelStands), BorderLayout.CENTER);

        JTabbedPane pestanas = new JTabbedPane();
        pestanas.addTab("Opciones de Administrador", crearPanelAdministrador());
        pestanas.addTab("Panel de la Editorial (Cliente)", crearPanelEditorial());
        
        add(pestanas, BorderLayout.SOUTH);
        refrescarVisual();
    }

    private JPanel crearPanelAdministrador() {
        JPanel panelAdmin = new JPanel();
        panelAdmin.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        JButton btnBloquear = new JButton("Bloquear Stand");
        JButton btnTransferir = new JButton("Transferir Stand a...");
        JButton btnPrecio = new JButton("Fijar Tarifa Global (por m2)");
        JButton btnTamano = new JButton("Editar Tama\u00F1o (m2)");

        btnBloquear.addActionListener(e -> {
            String id = solicitarInput("Ingrese el ID del Stand a BLOQUEAR:");
            if (id != null) {
                if(gestor.bloquearStand(id)) mostrarMensaje("Stand bloqueado.");
                else mostrarError("Stand no encontrado.");
                refrescarVisual();
            }
        });
        btnTransferir.addActionListener(e -> {
            String id = solicitarInput("Ingrese el ID del Stand a TRANSFERIR:");
            if (id != null) {
                String editorial = solicitarInput("Nombre de la Editorial destino:");
                if (editorial != null && !editorial.trim().isEmpty()) {
                    if(gestor.transferirStand(id, editorial)) mostrarMensaje("Stand transferido (RESERVADO).");
                    else mostrarError("No se pudo transferir.");
                    refrescarVisual();
                }
            }
        });
        btnPrecio.addActionListener(e -> {
            try {
                String p = solicitarInput("Nueva tarifa base global ($ por Metro Cuadrado):");
                if(p != null) {
                    if(gestor.cambiarPrecioGlobalPorM2(Double.parseDouble(p))) {
                        mostrarMensaje("Tarifa global actualizada. Los totales se recalcularon autom\u00E1ticamente.");
                    } else { mostrarError("No hay stands registrados."); }
                    refrescarVisual();
                }
            } catch (Exception ex) { mostrarError("Tarifa inv\u00E1lida."); }
        });
        btnTamano.addActionListener(e -> {
            String id = solicitarInput("Ingrese el ID del Stand:");
            if (id != null) {
                try {
                    String t = solicitarInput("Nuevos Metros Cuadrados (m2):");
                    if(t != null) {
                        if(gestor.cambiarTamano(id, Double.parseDouble(t))) {
                            mostrarMensaje("Tama\u00F1o actualizado. El precio total se recalcul\u00F3.");
                        } else { mostrarError("Stand no encontrado."); }
                        refrescarVisual();
                    }
                } catch (Exception ex) { mostrarError("Tama\u00F1o inv\u00E1lido."); }
            }
        });
        panelAdmin.add(btnBloquear); panelAdmin.add(btnTransferir);
        panelAdmin.add(btnPrecio); panelAdmin.add(btnTamano);
        return panelAdmin;
    }

    private JPanel crearPanelEditorial() {
        JPanel panelEditorial = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelEditorial.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel lblNombre = new JLabel("Mi Editorial:");
        JTextField txtNombre = new JTextField("Editorial Planeta", 15);
        JButton btnReservar = new JButton("Reservar Stand");
        JButton btnPagar = new JButton("Realizar Pago (Simular)");
        JButton btnNotificaciones = new JButton("Buz\u00F3n de Notificaciones");

        btnReservar.addActionListener(e -> {
            String id = solicitarInput("\u00BFQu\u00E9 ID de Stand deseas reservar?");
            if (id != null) {
                String msg = gestor.reservarPorEditorial(id, txtNombre.getText().trim());
                if(msg.startsWith("Error")) mostrarError(msg); else mostrarMensaje(msg);
                refrescarVisual();
            }
        });
        btnPagar.addActionListener(e -> {
            String id = solicitarInput("ID del Stand que deseas pagar:");
            if (id != null) {
                String msg = gestor.pagarReserva(id, txtNombre.getText().trim());
                if(msg.startsWith("Error")) mostrarError(msg); else mostrarMensaje(msg);
                refrescarVisual();
            }
        });
        btnNotificaciones.addActionListener(e -> {
            List<String> alertas = gestor.obtenerNotificaciones(txtNombre.getText().trim());
            StringBuilder sb = new StringBuilder("--- BANDEJA DE ENTRADA ---\n\n");
            for(String a : alertas) sb.append(a).append("\n\n");
            JOptionPane.showMessageDialog(this, sb.toString(), "Notificaciones", JOptionPane.INFORMATION_MESSAGE);
        });

        panelEditorial.add(lblNombre); panelEditorial.add(txtNombre);
        panelEditorial.add(new JSeparator(SwingConstants.VERTICAL));
        panelEditorial.add(btnReservar); panelEditorial.add(btnPagar);
        panelEditorial.add(btnNotificaciones);
        return panelEditorial;
    }

    private void refrescarVisual() { panelStands.actualizar(gestor.obtenerTodosLosStands()); }
    private String solicitarInput(String msj) { return JOptionPane.showInputDialog(this, msj); }
    private void mostrarMensaje(String msj) { JOptionPane.showMessageDialog(this, msj, "\u00C9xito", JOptionPane.INFORMATION_MESSAGE); }
    private void mostrarError(String msj) { JOptionPane.showMessageDialog(this, msj, "Error", JOptionPane.ERROR_MESSAGE); }
}
