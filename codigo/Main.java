import datos.Stand;
import negocios.GestorStands;
import ui.VentanaPrincipal;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        GestorStands gestor = new GestorStands();
        gestor.registrarStand(new Stand("ST-01", 12.0, 300.0));
        gestor.registrarStand(new Stand("ST-02", 9.5, 300.0));
        gestor.registrarStand(new Stand("ST-03", 20.0, 300.0));
        gestor.registrarStand(new Stand("ST-04", 15.0, 300.0));
        gestor.registrarStand(new Stand("ST-05", 10.0, 300.0));
        SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(gestor);
            ventana.setLocationRelativeTo(null);
            ventana.setVisible(true);
        });
    }
}
