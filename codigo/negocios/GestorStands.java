package negocios;
import datos.EstadoStand;
import datos.Stand;
import java.util.ArrayList;
import java.util.List;

public class GestorStands {
    private List<Stand> stands;
    public GestorStands() { this.stands = new ArrayList<>(); }
    public void registrarStand(Stand stand) { stands.add(stand); }
    public List<Stand> obtenerTodosLosStands() { return stands; }
    public Stand buscarStand(String id) {
        for (Stand s : stands) { if (s.getId().equalsIgnoreCase(id)) return s; }
        return null;
    }

    public boolean bloquearStand(String id) {
        Stand s = buscarStand(id);
        if (s != null) {
            s.setEstado(EstadoStand.BLOQUEADO);
            s.setPropietario("Admin");
            return true;
        }
        return false;
    }
    public boolean transferirStand(String id, String nombreEditorial) {
        Stand s = buscarStand(id);
        if (s != null && s.getEstado() != EstadoStand.BLOQUEADO) {
            s.setEstado(EstadoStand.RESERVADO);
            s.setPropietario(nombreEditorial);
            return true;
        }
        return false;
    }
    public boolean cambiarPrecioGlobalPorM2(double nuevoCostoM2) {
        if (stands.isEmpty()) return false;
        for (Stand s : stands) { s.setCostoPorMetroCuadrado(nuevoCostoM2); }
        return true;
    }
    public boolean cambiarTamano(String id, double nuevosMetros) {
        Stand s = buscarStand(id);
        if (s != null) { s.setMetrosCuadrados(nuevosMetros); return true; }
        return false;
    }
    public String reservarPorEditorial(String id, String editorial) {
        Stand s = buscarStand(id);
        if (s == null) return "Error: El stand no existe.";
        if (s.getEstado() != EstadoStand.DISPONIBLE) return "Error: El stand no est\u00E1 disponible.";
        s.setEstado(EstadoStand.RESERVADO);
        s.setPropietario(editorial);
        return "\u00C9XITO: Stand reservado a nombre de " + editorial + ". Pago total requerido: $" + String.format("%.2f", s.getPrecioTotal());
    }
    public String pagarReserva(String id, String editorial) {
        Stand s = buscarStand(id);
        if (s == null) return "Error: El stand no existe.";
        if (s.getEstado() != EstadoStand.RESERVADO) return "Error: El stand debe estar reservado para pagarlo.";
        if (!s.getPropietario().equalsIgnoreCase(editorial)) return "Error: Esta reserva pertenece a otra editorial.";
        s.setEstado(EstadoStand.OCUPADO);
        return "\u00C9XITO: Pago de $" + String.format("%.2f", s.getPrecioTotal()) + " procesado. El stand ahora es tuyo (OCUPADO).";
    }
    public List<String> obtenerNotificaciones(String editorial) {
        List<String> notificaciones = new ArrayList<>();
        for (Stand s : stands) {
            if (s.getPropietario().equalsIgnoreCase(editorial)) {
                if (s.getEstado() == EstadoStand.RESERVADO) {
                    notificaciones.add("URGENTE: Tu reserva [" + s.getId() + "] est\u00E1 pr\u00F3xima a vencer. Monto a pagar: $" + String.format("%.2f", s.getPrecioTotal()));
                } else if (s.getEstado() == EstadoStand.OCUPADO) {
                    notificaciones.add("CONFIRMADO: El pago de [" + s.getId() + "] por $" + String.format("%.2f", s.getPrecioTotal()) + " fue exitoso.");
                }
            }
        }
        if(notificaciones.isEmpty()) { notificaciones.add("No tienes notificaciones ni stands a tu nombre."); }
        return notificaciones;
    }
}
