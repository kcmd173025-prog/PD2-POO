# Diagrama de Clases - Sistema de Stands

Este diagrama representa la arquitectura actual del sistema, dividida en capas lógicas (Datos, Negocios y UI), soportando el cálculo dinámico y la interfaz visual.

```mermaid
classDiagram
    class EstadoStand {
        <<enumeration>>
        DISPONIBLE
        OCUPADO
        RESERVADO
        BLOQUEADO
        -Color color
        +getColor() Color
    }

    class Stand {
        -String id
        -double metrosCuadrados
        -double costoPorMetroCuadrado
        -EstadoStand estado
        -String propietario
        +getPrecioTotal() double
        +setEstado(EstadoStand)
        +setCostoPorMetroCuadrado(double)
        +setMetrosCuadrados(double)
        +setPropietario(String)
    }

    class GestorStands {
        -List~Stand~ stands
        +registrarStand(Stand)
        +buscarStand(String) Stand
        +bloquearStand(String) boolean
        +transferirStand(String, String) boolean
        +cambiarPrecioGlobalPorM2(double) boolean
        +cambiarTamano(String, double) boolean
        +reservarPorEditorial(String, String) String
        +pagarReserva(String, String) String
        +obtenerNotificaciones(String) List~String~
    }

    class PanelStands {
        +actualizar(List~Stand~)
    }

    class VentanaPrincipal {
        -GestorStands gestor
        -PanelStands panelStands
        +crearPanelAdministrador() JPanel
        +crearPanelEditorial() JPanel
        -refrescarVisual()
    }

    Stand "1" --> "1" EstadoStand : estado actual >
    GestorStands "1" --> "0..*" Stand : administra >
    VentanaPrincipal "1" --> "1" GestorStands : utiliza lógica de >
    VentanaPrincipal "1" --> "1" PanelStands : renderiza >
```
