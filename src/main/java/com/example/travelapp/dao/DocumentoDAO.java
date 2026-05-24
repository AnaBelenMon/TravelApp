package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Documento;
import com.example.travelapp.model.TipoDocumento;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO (Data Access Object) encargado de gestionar el acceso a datos
 * de la entidad Documento.
 *
 * Permite realizar operaciones CRUD y consultas específicas
 * sobre la tabla "documento".
 */
public class DocumentoDAO {

    // =========================
    // CONSULTAS SQL
    // =========================

    private final static String SQL_ALL = "SELECT * FROM documento";
    private final static String SQL_FIND_BY_IDDOCUMENTO = "SELECT * FROM documento WHERE idDocumento = ?";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM documento WHERE idViaje = ?";
    private final static String SQL_FIND_BY_NOMBRE = "SELECT * FROM documento WHERE nombre = ?";
    private final static String SQL_FIND_BY_TIPO = "SELECT * FROM documento WHERE tipo = ?";
    private final static String SQL_FIND_BY_RUTAARCHIVO = "SELECT * FROM documento WHERE rutaArchivo = ?";

    private final static String SQL_INSERT =
            "INSERT INTO documento(idViaje, nombre, tipo, rutaArchivo) VALUES (?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE documento SET idViaje=?, nombre=?, tipo=?, rutaArchivo=? WHERE idDocumento=?";

    private final static String SQL_DELETE =
            "DELETE FROM documento WHERE idDocumento=?";

    // =========================
    // CONSULTA: FIND ALL
    // =========================

    /**
     * Obtiene todos los documentos almacenados en la base de datos.
     *
     * @return lista de documentos
     */
    public static List<Documento> findAll() throws SQLException {

        List<Documento> documentos = new ArrayList<>();

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {

            while (rs.next()) {

                int idDocumento = rs.getInt("idDocumento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String rutaArchivo = rs.getString("rutaArchivo");

                Documento documento =
                        new Documento(idDocumento, idViaje, nombre, tipo, rutaArchivo);

                documentos.add(documento);
            }
        }
        return documentos;
    }

    // =========================
    // CONSULTA: FIND BY ID
    // =========================

    /**
     * Busca un documento por su ID.
     *
     * @param idDocumento identificador del documento
     * @return Documento si existe, null si no
     */
    public static Documento findDocumentoByIdDocumento(int idDocumento) throws SQLException {

        Documento documento = null;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_IDDOCUMENTO)) {

            ps.setInt(1, idDocumento);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String rutaArchivo = rs.getString("rutaArchivo");

                documento = new Documento(idDocumento, idViaje, nombre, tipo, rutaArchivo);
            }
        }
        return documento;
    }

    // =========================
    // CONSULTA: FIND BY ID VIAJE
    // =========================

    /**
     * Obtiene todos los documentos asociados a un viaje.
     *
     * @param idViaje identificador del viaje
     * @return lista de documentos
     */
    public static List<Documento> findByIdViaje(int idViaje) throws SQLException {

        List<Documento> documentos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_IDVIAJE)) {

            ps.setInt(1, idViaje);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int idDocumento = rs.getInt("idDocumento");
                int idViaje2 = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String rutaArchivo = rs.getString("rutaArchivo");

                Documento documento =
                        new Documento(idDocumento, idViaje2, nombre, tipo, rutaArchivo);

                documentos.add(documento);
            }
        }
        return documentos;
    }

    // =========================
    // CONSULTA: FIND BY NOMBRE
    // =========================

    /**
     * Busca un documento por nombre exacto.
     */
    public static Documento findByNombre(String nombre) throws SQLException {

        Documento documento = null;

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_NOMBRE)) {

            ps.setString(1, nombre);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int idDocumento = rs.getInt("idDocumento");
                int idViaje = rs.getInt("idViaje");
                String nombre2 = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String rutaArchivo = rs.getString("rutaArchivo");

                documento = new Documento(idDocumento, idViaje, nombre2, tipo, rutaArchivo);
            }
        }
        return documento;
    }

    // =========================
    // CONSULTA: FIND BY TIPO
    // =========================

    /**
     * Obtiene documentos filtrados por tipo.
     */
    public static List<Documento> findByTipo(TipoDocumento tipo) throws SQLException {

        List<Documento> documentos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_TIPO)) {

            ps.setString(1, tipo.name());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int idDocumento = rs.getInt("idDocumento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                TipoDocumento tipo2 = TipoDocumento.valueOf(rs.getString("tipo"));
                String rutaArchivo = rs.getString("rutaArchivo");

                Documento documento =
                        new Documento(idDocumento, idViaje, nombre, tipo2, rutaArchivo);

                documentos.add(documento);
            }
        }
        return documentos;
    }

    // =========================
    // CONSULTA: FIND BY RUTA
    // =========================

    /**
     * Busca documentos por ruta de archivo.
     */
    public static List<Documento> findByRutaArchivo(String rutaArchivo) throws SQLException {

        List<Documento> documentos = new ArrayList<>();

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_FIND_BY_RUTAARCHIVO)) {

            ps.setString(1, rutaArchivo);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                int idDocumento = rs.getInt("idDocumento");
                int idViaje = rs.getInt("idViaje");
                String nombre = rs.getString("nombre");
                TipoDocumento tipo = TipoDocumento.valueOf(rs.getString("tipo"));
                String rutaArchivo2 = rs.getString("rutaArchivo");

                Documento documento =
                        new Documento(idDocumento, idViaje, nombre, tipo, rutaArchivo2);

                documentos.add(documento);
            }
        }
        return documentos;
    }

    // =========================
    // CONSULTA: INSERT
    // =========================

    /**
     * Inserta un nuevo documento en la base de datos.
     */
    public static boolean insert(Documento documento) throws SQLException {

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_INSERT)) {

            ps.setInt(1, documento.getIdViaje());
            ps.setString(2, documento.getNombre());
            ps.setString(3, documento.getTipo().name());
            ps.setString(4, documento.getRutaArchivo());

            return ps.executeUpdate() > 0;
        }
    }

    // =========================
    // CONSULTA: UPDATE
    // =========================

    /**
     * Actualiza un documento existente.
     */
    public static boolean update(Documento documento) throws SQLException {

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_UPDATE)) {

            ps.setInt(1, documento.getIdViaje());
            ps.setString(2, documento.getNombre());
            ps.setString(3, documento.getTipo().name());
            ps.setString(4, documento.getRutaArchivo());
            ps.setInt(5, documento.getIdDocumento());

            return ps.executeUpdate() > 0;
        }
    }

    // =========================
    // CONSULTA: DELETE
    // =========================

    /**
     * Elimina un documento por su ID.
     */
    public static boolean delete(int idDocumento) throws SQLException {

        try (PreparedStatement ps = ConnectionBD.getConnection()
                .prepareStatement(SQL_DELETE)) {

            ps.setInt(1, idDocumento);
            return ps.executeUpdate() > 0;
        }
    }
}