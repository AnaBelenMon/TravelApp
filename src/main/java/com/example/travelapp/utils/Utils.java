package com.example.travelapp.utils;

import com.example.travelapp.model.Gasto;
import com.example.travelapp.model.Transporte;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.model.enums.EstadoGasto;
import com.example.travelapp.model.enums.TipoTransporte;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;

import java.io.File;
import java.io.FileInputStream;
import java.security.MessageDigest;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;
import java.util.List;

public class Utils {
    private static Usuario usuarioActual;
    public static boolean esEmailValido(String email) {
        if (email == null || email.isBlank()){
            return false;
        }
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    }
    public static boolean esNumero(String texto) {
        if (texto == null || texto.isBlank()){
            return false;
        }
        return texto.matches("-?\\d+(\\.\\d+)?");
    }
    public static boolean esPrecioValido(double precio) {
        return precio >= 0;
    }

    public static boolean esFechaValida(LocalDate fecha) {
        return fecha != null;
    }

    public static boolean noVacio(String texto) {
        return texto != null && !texto.isBlank();
    }

    public static boolean longitudMinima(String texto, int min) {
        return texto != null && texto.trim().length() >= min;
    }

    public static void mostrarInfo(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mostrarWarning(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public static boolean mostrarConfirmacion(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        return alert.showAndWait().isPresent()
                && alert.getResult().getButtonData().isDefaultButton();
    }
    public static LocalDateTime combinar(LocalDate fecha, LocalTime hora) {
        if (fecha == null || hora == null) return null;
        return LocalDateTime.of(fecha, hora);
    }

    public static String formatearFecha(LocalDate fecha) {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public static String formatearFechaHora(LocalDateTime fecha) {
        if (fecha == null) return "";
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    public static boolean esFechaPosterior(LocalDate f1, LocalDate f2) {
        if (f1 == null || f2 == null) return false;
        return f1.isAfter(f2);
    }
    public static Double toDouble(String texto) {
        if (!esNumero(texto)) return null;
        try {
            return Double.parseDouble(texto.replace(",", "."));
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static Integer toInt(String texto) {
        if (texto == null || texto.isBlank()) return null;
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static boolean esDouble(String texto) {
        return toDouble(texto) != null;
    }

    public static boolean esInt(String texto) {
        return toInt(texto) != null;
    }
    public static void setUsuario(Usuario u) {
        usuarioActual = u;
    }

    public static Usuario getUsuario() {
        return usuarioActual;
    }

    public static void cerrarSesion() {
        usuarioActual = null;
    }

    public static Image cargar(String ruta) {
        if (ruta == null || ruta.isBlank()) return null;
        try {
            File file = new File(ruta);
            if (!file.exists()) return null;
            return new Image(new FileInputStream(file));
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean existeImagen(String ruta) {
        if (ruta == null || ruta.isBlank()) return false;
        return new File(ruta).exists();
    }

    public static String copiarImagen(File archivo) {
        // Aquí podrías implementar copia a una carpeta interna del proyecto
        // Por ahora devolvemos la ruta original
        if (archivo == null) return null;
        return archivo.getAbsolutePath();
    }
    public static List<Transporte> filtrarPorTipo(List<Transporte> lista, TipoTransporte tipo) {
        if (lista == null || tipo == null){
            return lista;
        }
        return lista.stream()
                .filter(t -> t.getTipo() == tipo)
                .toList();
    }

    public static List<Gasto> filtrarPorEstado(List<Gasto> lista, EstadoGasto estado) {
        if (lista == null || estado == null) return lista;
        return lista.stream()
                .filter(g -> g.getEstado() == estado)
                .toList();
    }

    public static <T> List<T> buscarPorTexto(List<T> lista, String texto) {
        // Depende de cómo quieras buscar; lo dejo como esqueleto
        return lista;
    }
    private static final String PEPPER = "TRAVELAPP-2024-SECRET";

    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            String combinado = password + PEPPER;
            byte[] hash = md.digest(combinado.getBytes());
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            throw new RuntimeException("Error al hashear contraseña", e);
        }
    }

    public static boolean verificarPassword(String password, String hashGuardado) {
        String hashNuevo = hashPassword(password);
        return hashNuevo.equals(hashGuardado);
    }

    public static void closeQuietly(Connection con, Statement st, ResultSet rs) {
        try { if (rs != null) rs.close(); } catch (Exception ignored) {}
        try { if (st != null) st.close(); } catch (Exception ignored) {}
        try { if (con != null) con.close(); } catch (Exception ignored) {}
    }

    public static void rollback(Connection con) {
        try { if (con != null) con.rollback(); } catch (Exception ignored) {}
    }
    public static String capitalizar(String texto) {
        if (texto == null || texto.isBlank()) return texto;
        texto = texto.trim();
        return texto.substring(0, 1).toUpperCase() + texto.substring(1).toLowerCase();
    }

    public static String normalizarEspacios(String texto) {
        if (texto == null) return null;
        return texto.trim().replaceAll("\\s+", " ");
    }

    public static String limpiar(String texto) {
        if (texto == null) return null;
        return texto.trim();
    }

    public static boolean esVacio(String texto) {
        return texto == null || texto.isBlank();
    }

    public static void info(String msg) {
        System.out.println("[INFO] " + msg);
    }

    public static void warn(String msg) {
        System.out.println("[WARN] " + msg);
    }

    public static void error(String msg, Exception e) {
        System.err.println("[ERROR] " + msg);
        if (e != null) e.printStackTrace();
    }
}
