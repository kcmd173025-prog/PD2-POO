package datos;
public class Stand {
    private String id;
    private double metrosCuadrados;
    private double costoPorMetroCuadrado;
    private EstadoStand estado;
    private String propietario;

    public Stand(String id, double metrosCuadrados, double costoPorMetroCuadrado) {
        this.id = id;
        this.metrosCuadrados = metrosCuadrados;
        this.costoPorMetroCuadrado = costoPorMetroCuadrado;
        this.estado = EstadoStand.DISPONIBLE;
        this.propietario = "Ninguno";
    }

    public String getId() { return id; }
    public double getMetrosCuadrados() { return metrosCuadrados; }
    public double getCostoPorMetroCuadrado() { return costoPorMetroCuadrado; }
    public EstadoStand getEstado() { return estado; }
    public String getPropietario() { return propietario; }

    public double getPrecioTotal() { return metrosCuadrados * costoPorMetroCuadrado; }

    public void setEstado(EstadoStand estado) { this.estado = estado; }
    public void setCostoPorMetroCuadrado(double costoPorMetroCuadrado) { this.costoPorMetroCuadrado = costoPorMetroCuadrado; }
    public void setMetrosCuadrados(double metrosCuadrados) { this.metrosCuadrados = metrosCuadrados; }
    public void setPropietario(String propietario) { this.propietario = propietario; }
}
