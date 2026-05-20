package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.TipoDocumento;
import com.example.travelapp.model.TipoTransporte;
import com.example.travelapp.model.Transporte;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransporteDAO {
    private final static String SQL_ALL = "SELECT * FROM Transporte";
    private final static String SQL_FIND_BY_IDTRANSPORTE = "SELECT * FROM Transporte WHERE idTransporte = ?";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM Transporte WHERE idViaje = ?";
    private final static String SQL_FIND_BY_TIPO = "SELECT * FROM Transporte WHERE tipo = ?";
    private final static String SQL_FIND_BY_FECHA = "SELECT * FROM Transporte WHERE fecha = ?";
    private final static String SQL_FIND_BY_PRECIO = "SELECT * FROM Transporte WHERE precio = ?";

    private final static String SQL_INSERT = "INSERT INTO Transporte values()";
    private final static String SQL_UPDATE = "UPDATE Transporte SET ";
    private final static String SQL_DELETE = "DELETE FROM Transporte WHERE idTransporte = ?";

    public static List<Transporte> findAll() throws SQLException {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte =  null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)){
            while(rs.next()){

                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double precio = rs.getDouble("precio");
                TipoDocumento tipoDocumento = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                String rutaDocumento = rs.getString("rutaDocumento");
                transporte = new Transporte(tipo,fecha,precio,tipoDocumento,rutaDocumento);
                transportes.add(transporte);
            }
        }
        return transportes;
    }

    public static Transporte findById(int idTransporte) throws SQLException {
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDTRANSPORTE)){
            ps.setInt(1, idTransporte);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){

                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double precio = rs.getDouble("precio");
                TipoDocumento tipoDocumento = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                String rutaDocumento = rs.getString("rutaDocumento");
                transporte = new Transporte(tipo,fecha,precio,tipoDocumento,rutaDocumento);
            }
        }
        return transporte;
    }

    public static Transporte findByIdViaje(int idViaje) throws SQLException {
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)){
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double precio = rs.getDouble("precio");
                TipoDocumento tipoDocumento = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                String rutaDocumento = rs.getString("rutaDocumento");
                transporte = new Transporte(tipo,fecha,precio,tipoDocumento,rutaDocumento);
            }
        }
        return transporte;
    }

    public static List<Transporte> findByTipoTransporte(TipoTransporte tipoTransporte) throws SQLException {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPO)){
            ps.setString(1, tipoTransporte.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double precio = rs.getDouble("precio");
                TipoDocumento tipoDocumento = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                String rutaDocumento = rs.getString("rutaDocumento");
                transporte = new Transporte(tipo,fecha,precio,tipoDocumento,rutaDocumento);
                transportes.add(transporte);
            }
        }
        return transportes;
    }

    public static List<Transporte> findByFecha(LocalDate fecha) throws SQLException {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)){
            ps.setString(1, fecha.toString());
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                LocalDate fecha2 = LocalDate.parse(rs.getString("fecha"));
                double precio = rs.getDouble("precio");
                TipoDocumento tipoDocumento = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                String rutaDocumento = rs.getString("rutaDocumento");
                transporte = new Transporte(tipo,fecha2,precio,tipoDocumento,rutaDocumento);
                transportes.add(transporte);
            }
        }
        return transportes;
    }

    public static List<Transporte> findByPrecio(double precio) throws SQLException {
        List<Transporte> transportes = new ArrayList<>();
        Transporte transporte = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_PRECIO)){
            ps.setDouble(1, precio);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                TipoTransporte tipo = TipoTransporte.valueOf(rs.getString("tipo"));
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                double precio2 = rs.getDouble("precio");
                TipoDocumento tipoDocumento = TipoDocumento.valueOf(rs.getString("tipoDocumento"));
                String rutaDocumento = rs.getString("rutaDocumento");
                transporte = new Transporte(tipo,fecha,precio2,tipoDocumento,rutaDocumento);
                transportes.add(transporte);
            }
        }
        return transportes;
    }

    public static Transporte addTransporte(Transporte transporte) throws SQLException {
        if (transporte != null && findById(transporte.getIdTransporte()) == null){
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)){
                ps.setInt(1, transporte.getIdTransporte());
                ps.setInt(2, transporte.getIdViaje());
                ps.executeUpdate();
            }
        }
        return transporte;
    }

    public static boolean updateTransporte(Transporte transporteNuevo, Transporte transporteActual) throws SQLException {
        boolean updated = false;
        if((transporteActual!=null)&&(transporteNuevo!=null)&&findById(transporteActual.getIdTransporte())!=null && findById(transporteNuevo.getIdTransporte())==null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setInt(1, transporteActual.getIdTransporte());
                ps.setInt(2, transporteNuevo.getIdTransporte());
                ps.setInt(3, transporteActual.getIdViaje());
                ps.executeUpdate();
                updated = true;
            }
        }
        return updated;
    }

    public static boolean deleteTransporte(int idTransporte) throws SQLException {
        boolean deleted = false;
        if(findById(idTransporte)!=null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idTransporte);
                ps.executeUpdate();
                deleted = true;
            }
        }
        return deleted;
    }
}
