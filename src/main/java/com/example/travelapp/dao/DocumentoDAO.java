package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Documento;
import com.example.travelapp.model.TipoDocumento;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DocumentoDAO {
    private final static String SQL_ALL = "SELECT * FROM documento";
    private final static String SQL_FIND_BY_IDDCUMENTO = "SELECT * FROM documento WHERE idDocumento = ?";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM documento WHERE idViaje = ?";
    private final static String SQL_FIND_BY_NAME = "SELECT * FROM documento WHERE nombre = ?";
    private final static String SQL_FIND_BY_TIPE = "SELECT * FROM documento WHERE tipo = ?";
    private final static String SQL_FIND_BY_RUTAARCHIVO = "SELECT * FROM documento WHERE rutaArchivo = ?";

    private final static String SQL_INSERT = "INSERT INTO documento(nombre) VALUES (?)";
    private final static String SQL_UPDATE = "UPDATE documento SET nombre=? WHERE id=?";
    private final static String SQL_DELETE = "DELETE FROM documento WHERE id=?";


    public static List<Documento> findAllDocumentos() throws SQLException {
        List<Documento> documentos = new ArrayList<>();
        Documento documento = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String archivo = rs.getString("archivo");
                documento = new Documento(nombre, tipo, archivo);
                documentos.add(documento);
            }
        }
        return documentos;
    }

    public static Documento findDocumentoByIdDocumento(int idDocumento) throws SQLException {
        Documento documento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDDCUMENTO)) {
            ps.setInt(1, idDocumento);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String archivo = rs.getString("archivo");
                documento = new Documento(nombre, tipo, archivo);
            }
        }
        return documento;
    }

    public static Documento findDocumentoByIdViaje(int idViaje) throws SQLException {
        Documento documento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_IDVIAJE)) {
            ps.setInt(1, idViaje);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String archivo = rs.getString("archivo");
                documento = new Documento(nombre, tipo, archivo);
            }
        }
        return documento;
    }

    public static Documento findDocumentoByName(String nombre) throws SQLException {
        Documento documento = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_NAME)) {
            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String nombre2 = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String archivo = rs.getString("archivo");
                documento = new Documento(nombre2, tipo, archivo);
            }
        }
        return documento;
    }

    public static List<Documento> findDocumentoByTipo(TipoDocumento tipo) throws SQLException {
        Documento documento = null;
        List<Documento> documentos = new ArrayList<>();
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_TIPE)) {
            ps.setString(1, String.valueOf(tipo));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                TipoDocumento tipo2 = TipoDocumento.valueOf(rs.getString("tipo"));
                String archivo = rs.getString("archivo");
                documento = new Documento(nombre, tipo2, archivo);
            }
        }
        return documentos;
    }

    public static List<Documento> findDocumentoByRutaArchivo(String rutaArchivo) throws SQLException {
        Documento documento = null;
        List<Documento> documentos = new ArrayList<>();
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_RUTAARCHIVO)) {
            ps.setString(1, rutaArchivo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String archivo = rs.getString("archivo");
                documento = new Documento(nombre, tipo, archivo);
                documentos.add(documento);
            }
        }
        return documentos;
    }

    public static Documento addDocumento(Documento documento) throws SQLException {
        if (documento != null && findDocumentoByName(documento.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
                ps.setString(1, documento.getNombre());
                ps.setInt(2, documento.getIdViaje());
                ps.executeUpdate();
            }
        }
        return documento;
    }

    public static boolean updateDocumento(Documento documentoNuevo, Documento documentoActual) throws SQLException {
        boolean update = false;
        if (documentoActual != null && documentoNuevo != null && findDocumentoByName(documentoActual.getNombre()) == null && findDocumentoByName(documentoNuevo.getNombre()) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
                ps.setString(1, documentoNuevo.getNombre());
                ps.setInt(2, documentoNuevo.getIdViaje());
                ps.setString(3, documentoNuevo.getNombre());
                ps.executeUpdate();
                update = true;
            }
        }
        return update;
    }

    public static boolean deleteDocumentoById(int idDocumento) throws SQLException {
        boolean delete = false;
        if (findDocumentoByIdDocumento(idDocumento) == null) {
            try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
                ps.setInt(1, idDocumento);
                ps.executeUpdate();
                delete = true;
            }
        }
        return delete;
    }
}