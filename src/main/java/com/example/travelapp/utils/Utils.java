package com.example.travelapp.utils;

import javafx.scene.control.Alert;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Clase de utilidades generales para validaciones, conversión de datos,
 * manejo de fechas, carga de imágenes, filtrados y operaciones auxiliares
 * usadas en toda la aplicación TravelApp.
 *
 * <p>Incluye:</p>
 * <ul>
 *     <li>Validaciones de email, números, fechas y textos.</li>
 *     <li>Conversión segura de String a tipos numéricos.</li>
 *     <li>Formateo de fechas y horas.</li>
 *     <li>Gestión de alertas JavaFX.</li>
 *     <li>Carga y verificación de imágenes.</li>
 *     <li>Filtrado de listas de transportes y gastos.</li>
 *     <li>Hashing y verificación de contraseñas.</li>
 *     <li>Cierre seguro de conexiones JDBC.</li>
 *     <li>Normalización y limpieza de texto.</li>
 * </ul>
 */
public class Utils {

    /**
     * Comprueba si un texto representa un número válido.
     *
     * @param texto texto a validar
     * @return true si es un número entero o decimal
     */
    public static boolean esNumero(String texto) {
        if (texto == null || texto.isBlank()) return false;
        return texto.matches("-?\\d+(\\.\\d+)?");
    }

    /**
     * Muestra una alerta informativa.
     */
    public static void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de advertencia.
     */
    public static void mostrarWarning(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Muestra una alerta de error.
     */
    public static void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    /**
     * Formatea una fecha en dd/MM/yyyy.
     */
    public static String formatearFecha(LocalDate fecha) {
        return fecha == null ? "" : fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    /**
     * Formatea una fecha y hora en dd/MM/yyyy HH:mm.
     */
    public static String formatearFechaHora(LocalDateTime fecha) {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }


    /**
     * Convierte un texto a Double, aceptando coma o punto.
     *
     * @return número o null si no es válido
     */
    public static Double toDouble(String texto) {
        if (!esNumero(texto)) return null;
        try {
            return Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
