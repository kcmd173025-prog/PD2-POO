# Casos de Uso e Historias de Usuario - Sistema de Stands

## 1. Historias de Usuario (HU)

* **HU01 - Visualización de Estado de Stands**
  * *Como* administrador o editorial, *quiero* ver una cuadrícula visual con códigos de colores de los stands, *para* identificar rápidamente cuáles están disponibles, ocupados, reservados o bloqueados.
* **HU02 - Gestión y Bloqueo de Stands**
  * *Como* administrador, *quiero* bloquear stands específicos, *para* reservarlos administrativamente e impedir que estén disponibles para compra o selección pública.
* **HU03 - Transferencia de Stands a Editoriales**
  * *Como* administrador, *quiero* transferir o asignar un stand a una editorial, *para* cambiar su estado a reservado bajo su titularidad.
* **HU04 - Configuración de Tarifa Global**
  * *Como* administrador, *quiero* modificar la tarifa base por metro cuadrado, *para* actualizar de manera automática el precio total de todos los stands del recinto.
* **HU05 - Reserva y Simulación de Pago**
  * *Como* representante de una editorial, *quiero* reservar un stand disponible y simular su pago, *para* completar el proceso de ocupación del espacio y recibir notificaciones de confirmación o avisos de vencimiento.

---

## 2. Casos de Uso (CU)

### CU01: Bloquear Stand (Administrador)
* **Actor principal:** Administrador.
* **Precondición:** El stand debe estar registrado en el sistema.
* **Flujo principal:**
  1. El administrador accede a las opciones de gestión.
  2. Selecciona la opción de bloquear stand e ingresa el ID correspondiente.
  3. El sistema localiza el stand y cambia su estado a `BLOQUEADO` (Color Gris).
  4. El sistema muestra un mensaje de éxito.

### CU02: Reservar y Pagar Stand (Editorial)
* **Actor principal:** Editorial.
* **Precondición:** El stand debe encontrarse en estado `DISPONIBLE` (Color Verde).
* **Flujo principal:**
  1. La editorial ingresa su nombre y selecciona la opción de reservar un stand mediante su ID.
  2. El sistema valida la disponibilidad, cambia el estado a `RESERVADO` (Color Naranja) y lo asigna a la editorial.
  3. La editorial selecciona la opción de realizar el pago simulado.
  4. El sistema procesa el monto total calculado por sus metros cuadrados, cambia el estado del stand a `OCUPADO` (Color Rojo) y genera la notificación de pago exitoso en el buzón.
