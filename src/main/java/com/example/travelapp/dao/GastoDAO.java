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
 * DAO encargado de gestionar las operaciones CRUD relacionadas con la entidad
 * {@link Gasto}. Utiliza JDBC para interactuar con la base de datos y
 * convierte los registros obtenidos en objetos del modelo.
 *
 * Este DAO implementa la interfaz {@link GenericDAO} y proporciona métodos
 * específicos de búsqueda por viaje y categoría.
 *
 * Cada método utiliza consultas preparadas para evitar inyecciones SQL y
 * garantizar un acceso seguro y eficiente a la base de datos.
 */
public class GastoDAO implements GenericDAO<Gasto> {
    private final static String SQL_ALL = "SELECT * FROM gasto";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM gasto WHERE idGasto = ?";
    private final static String SQL_FIND_BY_VIAJE = "SELECT * FROM gasto WHERE idViaje = ?";
    private final static String SQL_FIND_BY_CATEGORIA = "SELECT * FROM gasto WHERE categoria = ?";

    private final static String SQL_INSERT = "INSERT INTO gasto (idViaje, concepto, categoria, importe, fecha, lugar, metodoPago, estado, notas) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE gasto SET idViaje=?, concepto=?, categoria=?, importe=?, fecha=?, lugar=?, metodoPago=?, estado=?, notas=? " + "WHERE idGasto=?";
    private final static String SQL_DELETE = "DELETE FROM gasto WHERE idGasto=?";

    private ViajeDAO viajeDAO = new ViajeDAO();

    /**
     * Obtiene todos los gastos registrados en la base de datos.
     *
     * @return lista de gastos
     */
    public List<Gasto> findAll() {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;

        try (Statement st = ConnectionBD.getConnection().createStatement();
             ResultSet rs = st.executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                double importe = rs.getDouble("importe");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = MetodoPago.valueOf(rs.getString("metodoPago"));
                EstadoGasto estado = EstadoGasto.valueOf(rs.getString("estado"));
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje,concepto, categoria, importe,fecha, lugar, metodoPago, estado, notas);
                gastos.add(gasto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

    /**
     * Busca un gasto por su identificador único.
     *
     * @param id identificador del gasto
     * @return gasto encontrado o null si no existe
     */
    public Gasto findById(int id) {
        Gasto gasto = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje = viajeDAO.findById(idViaje);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                double importe = rs.getDouble("importe");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = MetodoPago.valueOf(rs.getString("metodoPago"));
                EstadoGasto estado = EstadoGasto.valueOf(rs.getString("estado"));
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje,concepto, categoria, importe,fecha, lugar, metodoPago, estado, notas);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gasto;
    }

    /**
     * Busca todos los gastos asociados a un viaje concreto.
     *
     * @param viaje viaje del que se desean obtener los gastos
     * @return lista de gastos del viaje
     */
    public List<Gasto> findByViaje(Viaje viaje) {
        List<Gasto> gastos = new ArrayList<>();
        Gasto gasto = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_VIAJE)) {
            ps.setInt(1, viaje.getIdViaje());
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                int idGasto = rs.getInt("idGasto");
                int idViaje = rs.getInt("idViaje");
                Viaje viaje2 = viajeDAO.findById(idViaje);
                String concepto = rs.getString("concepto");
                CategoriaGasto categoria = CategoriaGasto.valueOf(rs.getString("categoria"));
                double importe = rs.getDouble("importe");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                String lugar = rs.getString("lugar");
                MetodoPago metodoPago = MetodoPago.valueOf(rs.getString("metodoPago"));
                EstadoGasto estado = EstadoGasto.valueOf(rs.getString("estado"));
                String notas = rs.getString("notas");
                gasto = new Gasto(idGasto, viaje2,concepto, categoria, importe,fecha, lugar, metodoPago, estado, notas);
                gastos.add(gasto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gastos;
    }

    /**
     * Inserta un nuevo gasto en la base de datos.
     * Tras la inserción, se recupera el ID generado automáticamente.
     *
     * @param gasto gasto a insertar
     * @return gasto con su ID actualizado
     */
    public Gasto add(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, gasto.getViaje().getIdViaje());
            ps.setString(2, gasto.getConcepto());
            ps.setString(3, gasto.getCategoria().name());
            ps.setDouble(4, gasto.getImporte());
            ps.setDate(5, Date.valueOf(gasto.getFecha()));
            ps.setString(6, gasto.getLugar());
            ps.setString(7, gasto.getMetodoPago().name());
            ps.setString(8, gasto.getEstado().name());
            ps.setString(9, gasto.getNotas());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()){
                gasto.setIdGasto(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gasto;
    }

    /**
     * Actualiza los datos de un gasto existente.
     *
     * @param gasto gasto con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    public boolean update(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            ps.setInt(1, gasto.getViaje().getIdViaje());
            ps.setString(2, gasto.getConcepto());
            ps.setString(3, gasto.getCategoria().name());
            ps.setDouble(4, gasto.getImporte());
            ps.setDate(5, Date.valueOf(gasto.getFecha()));
            ps.setString(6, gasto.getLugar());
            ps.setString(7, gasto.getMetodoPago().name());
            ps.setString(8, gasto.getEstado().name());
            ps.setString(9, gasto.getNotas());
            ps.setInt(10, gasto.getIdGasto());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un gasto de la base de datos.
     *
     * @param gasto gasto a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    public boolean delete(Gasto gasto) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, gasto.getIdGasto());
            return ps.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}