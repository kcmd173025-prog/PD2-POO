# Catálogo de Requisitos del Sistema

## Requisitos Funcionales (RF)
| ID | Descripción |
| :--- | :--- |
| **RF01** | El sistema debe mostrar una cuadrícula visual de stands con un código de colores (Verde=Disponible, Rojo=Ocupado, Naranja=Reservado, Gris=Bloqueado). |
| **RF02** | El sistema debe calcular el precio total del stand dinámicamente multiplicando sus metros cuadrados por la tarifa base global. |
| **RF03** | El administrador debe poder bloquear un stand, impidiendo su venta o reserva. |
| **RF04** | El administrador debe poder transferir (reservar) un stand a favor de una editorial específica. |
| **RF05** | El administrador debe poder modificar la tarifa base global por metro cuadrado, recalculando automáticamente el precio de todos los stands. |
| **RF06** | El administrador debe poder editar el tamaño (metros cuadrados) de un stand individual, afectando su precio total. |
| **RF07** | Las editoriales deben poder reservar stands disponibles a su nombre mediante un panel dedicado. |
| **RF08** | Las editoriales deben poder simular el pago del total de su reserva para cambiar el estado a Ocupado. |
| **RF09** | El sistema debe proveer una bandeja de notificaciones que avise a la editorial sobre pagos próximos a vencer y confirmaciones de pago exitoso. |

## Requisitos No Funcionales (RNF)
| ID | Descripción |
| :--- | :--- |
| **RNF01** | **Arquitectura:** El código debe mantener separación de responsabilidades en 3 paquetes principales (`datos`, `negocios`, `ui`). |
| **RNF02** | **Interfaz de Usuario:** La interfaz gráfica (GUI) debe estar desarrollada nativamente en Java Swing. |
| **RNF03** | **Codificación:** El sistema debe emplear escapes Unicode (ej. `ñ`) o UTF-8 para prevenir errores ortográficos en caracteres del español. |
| **RNF04** | **Reatividad:** La interfaz gráfica debe actualizarse en tiempo real (re-renderizado) tras cada acción del usuario sin necesidad de reiniciar la app. |
