package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.enums.CategoriaGasto;
import com.example.travelapp.model.Gasto;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.EstadoGasto;
import com.example.travelapp.model.enums.MetodoPago;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar el acceso a datos de la entidad Gasto.
 *
 * Permite realizar operaciones CRUD y consultas específicas sobre la tabla
 * gasto de la base de datos.
 */
public class GastoDAO implements GenericDAO<Gasto> {
    private final static String SQL_ALL = "SELECT * FROM gasto";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM gasto WHERE idGasto = ?";
    private final static String SQL_FIND_BY_VIAJE = "SELECT * FROM gasto WHERE idViaje = ?";
    private final static String SQL_FIND_BY_CONCEPTO =  "SELECT * FROM gasto WHERE concepto = ?";
    private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM gasto WHERE categoria = ?";
    private final static String SQL_FIND_BY_IMPORTE = "SELECT * FROM gasto WHERE importe = ?";
    private final static String SQL_FIND_BY_FECHA = "SELECT * FROM gasto WHERE fecha = ?";
    private final static String SQL_FIND_BY_LUGAR = "SELECT * FROM gasto WHERE lugar = ?";
    private final static String SQL_FIND_BY_METODO_PAGO = "SELECT * FROM gasto WHERE metodoPago = ?";
    private final static String SQL_FIND_BY_ESTADO = "SELECT * FROM gasto WHERE estado = ?";
    private final static String SQL_INSERT = "INSERT INTO gasto (viaje,concepto, categoria, importe,fecha,lugar, metodoPago, estado, notas) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE gasto SET viaje=?, concepto=?, categoria=?, importe=?, fecha=?, lugar=?, metodoPago=?, estado=?, notas=? WHERE idGasto=?";
    private final static String SQL_DELETE = "DELETE FROM gasto WHERE idGasto=?";


    /**
     * Obtiene todos los gastos almacenados en la base de datos.
     *
     * @return lista de gastos
     * @throws SQLException si ocurre un error en la consulta
     */
    public List<Gasto> findAll() {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idGasto = rs.getInt("idGasto");
                Viaje viaje = rs.getObject("viaje", Viaje.class);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoriaGasto = rs.getObject("categoria", CategoriaGasto.class);
                double importe = rs.getDouble("importe");
                LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = rs.getObject("metodoPago", MetodoPago.class);
                EstadoGasto estado = rs.getObject("estado", EstadoGasto.class);
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje,concepto, categoriaGasto, importe,fecha, lugar, metodoPago, estado, notas);
                gastos.add(gasto);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gastos;
    }

    /**
     * Busca un gasto por su identificador.
     *
     * @param id identificador del gasto
     * @return gasto encontrado o null si no existe
     * @throws SQLException si ocurre un error en la consulta
     */
    public Gasto findById(int id) {
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int idGasto = rs.getInt("idGasto");
                    Viaje viaje = rs.getObject("viaje", Viaje.class);
                    String concepto = rs.getString("concepto");
                    CategoriaGasto categoriaGasto = rs.getObject("categoria", CategoriaGasto.class);
                    double importe = rs.getDouble("importe");
                    LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                    String lugar = rs.getString("lugar");
                    MetodoPago metodoPago = rs.getObject("metodoPago", MetodoPago.class);
                    EstadoGasto estado = rs.getObject("estado", EstadoGasto.class);
                    String notas = rs.getString("notas");
                    gasto = new Gasto(idGasto, viaje,concepto, categoriaGasto, importe,fecha, lugar, metodoPago, estado, notas);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gasto;
    }

    /**
     * Busca todos los gastos asociados a un viaje.
     *
     * @param viaje viaje del cual se quieren obtener los gastos
     * @return lista de gastos del viaje
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findByViaje(Viaje viaje) {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_VIAJE)) {

            ps.setObject(1, viaje);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idGasto = rs.getInt("idGasto");
                    Viaje viaje2 = rs.getObject("viaje", Viaje.class);
                    String concepto = rs.getString("concepto");
                    CategoriaGasto categoriaGasto = rs.getObject("categoria", CategoriaGasto.class);
                    double importe = rs.getDouble("importe");
                    LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                    String lugar = rs.getString("lugar");
                    MetodoPago metodoPago = rs.getObject("metodoPago", MetodoPago.class);
                    EstadoGasto estado = rs.getObject("estado", EstadoGasto.class);
                    String notas = rs.getString("notas");
                    gasto = new Gasto(idGasto, viaje2,concepto, categoriaGasto, importe,fecha, lugar, metodoPago, estado, notas);
                    gastos.add(gasto);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gastos;
    }

    /**
     * Busca gastos por categoría.
     *
     * @param categoria categoría del gasto
     * @return lista de gastos filtrados
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findByCategoria(CategoriaGasto categoria) {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_CATEGORIA)) {

            ps.setString(1, categoria.name());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idGasto = rs.getInt("idGasto");
                    Viaje viaje = rs.getObject("viaje", Viaje.class);
                    String concepto = rs.getString("concepto");
                    CategoriaGasto categoriaGasto = rs.getObject("categoria", CategoriaGasto.class);
                    double importe = rs.getDouble("importe");
                    LocalDate fecha = LocalDate.parse(rs.getString("fecha"));
                    String lugar = rs.getString("lugar");
                    MetodoPago metodoPago = rs.getObject("metodoPago", MetodoPago.class);
                    EstadoGasto estado = rs.getObject("estado", EstadoGasto.class);
                    String notas = rs.getString("notas");
                    gasto = new Gasto(idGasto, viaje,concepto, categoriaGasto, importe,fecha, lugar, metodoPago, estado, notas);
                    gastos.add(gasto);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gastos;
    }

    /**
     * Busca gastos por fecha.
     *
     * @param fecha fecha del gasto
     * @return lista de gastos en esa fecha
     * @throws SQLException si ocurre un error en la consulta
     */
    public static List<Gasto> findByFecha(LocalDate fecha) {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_FECHA)) {

            ps.setString(1, fecha.toString());

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int idGasto = rs.getInt("idGasto");
                    Viaje viaje = rs.getObject("viaje", Viaje.class);
                    String concepto = rs.getString("concepto");
                    CategoriaGasto categoriaGasto = rs.getObject("categoria", CategoriaGasto.class);
                    double importe = rs.getDouble("importe");
                    LocalDate fecha2 = LocalDate.parse(rs.getString("fecha"));
                    String lugar = rs.getString("lugar");
                    MetodoPago metodoPago = rs.getObject("metodoPago", MetodoPago.class);
                    EstadoGasto estado = rs.getObject("estado", EstadoGasto.class);
                    String notas = rs.getString("notas");
                    gasto = new Gasto(idGasto, viaje,concepto, categoriaGasto, importe,fecha2, lugar, metodoPago, estado, notas);
                    gastos.add(gasto);
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gastos;
    }

    /**
     * Inserta un nuevo gasto en la base de datos.
     *
     * @param gasto objeto a insertar
     * @return true si se insertó correctamente
     */
    public Gasto add(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT)) {
            ps.setInt(1, gasto.getIdGasto());
            ps.setString(2, gasto.getViaje().getNombre());
            ps.setString(3, gasto.getConcepto());
            ps.setString(4, gasto.getCategoria().name());
            ps.setDouble(5, gasto.getImporte());
            ps.setString(6, gasto.getFecha().toString());
            ps.setString(7, gasto.getLugar());
            ps.setString(8, gasto.getMetodoPago().name());
            ps.setString(9, gasto.getEstado().name());
            ps.setString(10, gasto.getNotas());
        }catch (SQLException e){
            e.printStackTrace();
        }
        return gasto;
    }

    /**
     * Actualiza un gasto existente en la base de datos.
     *
     * @param gasto objeto con los nuevos datos
     * @return true si se actualizó correctamente
     */
    public boolean update(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, gasto.getIdGasto());
            ps.setString(2, gasto.getViaje().getNombre());
            ps.setString(3, gasto.getConcepto());
            ps.setString(4, gasto.getCategoria().name());
            ps.setDouble(5, gasto.getImporte());
            ps.setString(6, gasto.getFecha().toString());
            ps.setString(7, gasto.getLugar());
            ps.setString(8, gasto.getMetodoPago().name());
            ps.setString(9, gasto.getEstado().name());
            ps.setString(10, gasto.getNotas());
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un gasto por su identificador.
     *
     * @param idGasto identificador del gasto
     * @return true si se eliminó correctamente
     * @throws SQLException si ocurre un error en la base de datos
     */
    public boolean delete(int idGasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, idGasto);
            ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}