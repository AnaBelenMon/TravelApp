package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Emocion;
import com.example.travelapp.model.Recuerdo;
import com.example.travelapp.model.TipoRecuerdo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RecuerdoDAO {
    private final static String SQL_FIND_ALL = "SELECT * FROM recuerdo";
    private final static String SQL_FIND_BY_IDRECUERDO = "SELECT * FROM recuerdo WHERE idRecuerdo = ?";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM recuerdo WHERE idViaje = ?";
    private final static String SQL_FIND_BY_RUTAARCHIVO = "SELECT * FROM recuerdo WHERE rutaArchivo = ?";
    private final static String SQL_FIND_BY_UBICACION = "SELECT * FROM recuerdo WHERE ubicacion = ?";
    private final static String SQL_FIND_BY_FECHA = "SELECT * FROM recuerdo WHERE fecha = ?";
    private final static String SQL_FIND_BY_EMOCION = "SELECT * FROM recuerdo WHERE emocion = ?";
    private final static String SQL_FIND_BY_TIPO = "SELECT * FROM recuerdo WHERE tipo = ?";
    private final static String SQL_FIND_BY_FAVORITO = "SELECT * FROM recuerdo WHERE favorito = ?";
    private final static String SQL_FIND_BY_RUTAMINIATURA = "SELECT * FROM recuerdo WHERE rutaMiniatura = ?";

    private final static String SQL_INSERT = "INSERT INTO Recuerdo values()";
    private final static String SQL_UPDATE = "UPDATE Recuerdo SET ";
    private final static String SQL_DELETE = "DELETE FROM Recuerdo WHERE idRecuerdo = ?";

    public static List<Recuerdo> findAll() throws SQLException {
        List<Recuerdo> recuerdos = new ArrayList<>();
        Recuerdo recuerdo = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_FIND_ALL)) {
            while (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion, tipoRecuerdo, favorito, rutaMiniatura);
                recuerdos.add(recuerdo);
            }
        }
        return recuerdos;
    }

    public static Recuerdo findById(int idRecuerdo) throws SQLException {
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDRECUERDO)) {
            ps.setInt(1, idRecuerdo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo2 = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo2, idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion, tipoRecuerdo, favorito, rutaMiniatura);
            }
        }
        return recuerdo;
    }

    public static Recuerdo findByIdViaje(int idViaje) throws SQLException {
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje2 = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje2, rutaMiniatura, descripcion, ubicacion, fecha, emocion, tipoRecuerdo, favorito, rutaMiniatura);
            }
        }
        return recuerdo;
    }

    public static Recuerdo findByRutaArchivo(String rutaArchivo) throws SQLException {
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_RUTAARCHIVO)) {
            ps.setString(1, rutaArchivo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo2 = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo2, descripcion, ubicacion, fecha, emocion, tipoRecuerdo, favorito, rutaMiniatura);
            }
        }
        return recuerdo;
    }

    public static List<Recuerdo> findByUbicacion(String ubicacion) throws SQLException {
        List<Recuerdo> recuerdos = null;
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_UBICACION)) {
            ps.setString(1, ubicacion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion2 = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion2, fecha, emocion, tipoRecuerdo, favorito, rutaMiniatura);
                recuerdos.add(recuerdo);
            }
        }
        return recuerdos;
    }

    public static List<Recuerdo> findByFecha(LocalDate fecha) throws SQLException {
        List<Recuerdo> recuerdos = null;
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)) {
            ps.setString(1, fecha.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha2 = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion, fecha2, emocion, tipoRecuerdo, favorito, rutaMiniatura);
                recuerdos.add(recuerdo);
            }
        }
        return recuerdos;
    }


    public static List<Recuerdo> findByEmocion(Emocion emocion) throws SQLException {
        List<Recuerdo> recuerdos = null;
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_EMOCION)) {
            ps.setString(1, emocion.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion2 = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion2, tipoRecuerdo, favorito, rutaMiniatura);
                recuerdos.add(recuerdo);
            }
        }
        return recuerdos;
    }

    public static List<Recuerdo> findByTipoRecuerdo(TipoRecuerdo tipoRecuerdo) throws SQLException {
        List<Recuerdo> recuerdos = new ArrayList<>();
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO)) {
            ps.setString(1, tipoRecuerdo.toString());
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo2 = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion, tipoRecuerdo2, favorito, rutaMiniatura);
                recuerdos.add(recuerdo);
            }
        }
        return recuerdos;
    }

    public static List<Recuerdo> findByFavorito(boolean favorito) throws SQLException {
        List<Recuerdo> recuerdos = new ArrayList<>();
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FAVORITO)) {
            ps.setBoolean(1, favorito);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito2 = rs.getBoolean("favorito");
                String rutaMiniatura = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion, tipoRecuerdo, favorito2, rutaMiniatura);
                recuerdos.add(recuerdo);
            }

        }
        return recuerdos;
    }

    public static Recuerdo findByRutaMiniatura(String rutaMiniatura) throws SQLException {
        Recuerdo recuerdo = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_RUTAMINIATURA)) {
            ps.setString(1, rutaMiniatura);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idRecuerdo = rs.getInt("idRecuerdo");
                int idViaje = rs.getInt("idViaje");
                String rutaArchivo = rs.getString("rutaArchivo");
                String descripcion = rs.getString("descripcion");
                String ubicacion = rs.getString("ubicacion");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                Emocion emocion = Emocion.valueOf(rs.getString("emocion"));
                TipoRecuerdo tipoRecuerdo = TipoRecuerdo.valueOf(rs.getString("tipo"));
                boolean favorito = rs.getBoolean("favorito");
                String rutaMiniatura2 = rs.getString("rutaMiniatura");
                recuerdo = new Recuerdo(idRecuerdo, idViaje, rutaArchivo, descripcion, ubicacion, fecha, emocion, tipoRecuerdo, favorito, rutaMiniatura2);
            }
        }
        return recuerdo;
    }

    public static Recuerdo addRecuerdo(Recuerdo recuerdo) throws SQLException {
        if ((recuerdo != null) && findById(recuerdo.getIdRecuerdo()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, recuerdo.getDescripcion());
                ps.setString(2, recuerdo.getRutaArchivo());
                ps.setString(3, recuerdo.getRutaMiniatura());
                ps.executeUpdate();
            }
        }
        return recuerdo;
    }

    public static boolean updateRecuerdo(Recuerdo recuerdoNuevo, Recuerdo recuerdoActual) throws SQLException {
        boolean updated = false;
        if ((recuerdoActual != null) && (recuerdoNuevo != null) && findById(recuerdoActual.getIdRecuerdo()) != null && findById(recuerdoNuevo.getIdRecuerdo()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, recuerdoNuevo.getDescripcion());
                ps.setString(2, recuerdoNuevo.getRutaArchivo());
                ps.setString(3, recuerdoNuevo.getRutaMiniatura());
                ps.setInt(4, recuerdoActual.getIdRecuerdo());
                ps.executeUpdate();
                updated = true;
            }
        }
        return updated;
    }

    public static boolean deleteRecuerdo(int idRecuerdo) throws SQLException {
        boolean deleted = false;
        if (findById(idRecuerdo) != null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idRecuerdo);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}
