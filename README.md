# Laboratorio de la materia: Desarrollo de software para plataformas móviles

---

## 🧰 Requisitos

- Android Studio Electric.
- Dispositivo físico o emulador con Google Play Services.
- Conexión a internet activa.
- Permiso de ubicación habilitado.

---

## 🛠️ Instalación y Ejecución

1. Clona el repositorio o descarga el proyecto:
    ```bash
    git clone https://github.com/tu_usuario/Lab4_GpsWhatsappApp.git
    ```
2. Abre el proyecto en Android Studio.
3. Conecta un dispositivo físico o emulador.
4. Ejecuta la aplicación (Shift + F10 o botón "Run").

---

## ⚠️ Permisos

La aplicación requiere el siguiente permiso:

- `ACCESS_FINE_LOCATION`: para obtener la ubicación GPS del dispositivo.

Si el usuario no otorga el permiso, se muestra una alerta mediante Snackbar y no se envía ubicación.

---

## 🤖 Estructura del Proyecto

- `MainActivity.kt`: lógica principal, UI y permisos.
- `AppUI()`: componente @Composable que muestra el formulario.
- `PreviewAppUI()`: vista previa para diseño en tiempo de desarrollo.
- `themes/`: tema visual personalizado con Material3.

---

## 📦 Mejoras futuras

- Mostrar mapa interactivo antes de enviar.
- Agregar validación para números de teléfono.
- Agregar compatibilidad con más servicios de mensajería.

---

## 📄 Licencia

Este proyecto es de uso académico y no tiene licencia oficial.

---

## 🙋 Autor

Desarrollado por **Ing. Adolfo** como parte de prácticas universitarias de desarrollo móvil.  
