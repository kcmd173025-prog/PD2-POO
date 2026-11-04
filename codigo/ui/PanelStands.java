package ui;
import datos.EstadoStand;
import datos.Stand;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelStands extends JPanel {
    public PanelStands() {
        setLayout(new GridLayout(0, 3, 15, 15));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }
    public void actualizar(List<Stand> stands) {
        removeAll();
        for (Stand stand : stands) {
            JPanel cuadro = new JPanel(new BorderLayout());
            cuadro.setBackground(stand.getEstado().getColor());
            cuadro.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            cuadro.setPreferredSize(new Dimension(170, 170));
            String colorTexto = (stand.getEstado() == EstadoStand.OCUPADO || stand.getEstado() == EstadoStand.BLOQUEADO) ? "white" : "black";
            String htmlInfo = String.format(
                "<html><center><h1 style='color:%s; margin:0;'>%s</h1>" +
                "<span style='color:%s; font-size:11px;'><b>Tama\u00F1o:</b> %.2f m2</span><br>" +
                "<span style='color:%s; font-size:11px;'><b>Costo:</b> $%.2f /m2</span><br>" +
                "<span style='color:%s; font-size:12px; background-color:rgba(255,255,255,0.4);'><b> Total: $%.2f </b></span><br>" +
                "<span style='color:%s; font-size:11px;'><b>De:</b> %s</span><br>" +
                "<b style='color:%s; font-size:13px;'>[%s]</b></center></html>",
                colorTexto, stand.getId(), colorTexto, stand.getMetrosCuadrados(),
                colorTexto, stand.getCostoPorMetroCuadrado(), colorTexto, stand.getPrecioTotal(),
                colorTexto, stand.getPropietario(), colorTexto, stand.getEstado().name()
            );
            JLabel lblInfo = new JLabel(htmlInfo, SwingConstants.CENTER);
            cuadro.add(lblInfo, BorderLayout.CENTER);
            add(cuadro);
        }
        revalidate();
        repaint();
    }
}
