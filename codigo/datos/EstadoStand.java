package datos;
import java.awt.Color;
public enum EstadoStand {
    DISPONIBLE(Color.GREEN),
    OCUPADO(Color.RED),
    RESERVADO(Color.ORANGE),
    BLOQUEADO(Color.GRAY);

    private final Color color;
    EstadoStand(Color color) { this.color = color; }
    public Color getColor() { return color; }
}
