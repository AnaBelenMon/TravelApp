package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Alojamiento;
import com.example.travelapp.model.Usuario;
import com.example.travelapp.model.Viaje;
import com.example.travelapp.model.enums.TipoViaje;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO encargado de gestionar las operaciones CRUD relacionadas con la entidad
 * {@link Viaje}. Utiliza JDBC para interactuar con la base de datos y
 * convierte los registros obtenidos en objetos del modelo.
 *
 * Este DAO implementa la interfaz {@link GenericDAO} y proporciona métodos
 * adicionales de búsqueda por usuario.
 *
 * Cada método utiliza consultas preparadas para garantizar seguridad,
 * evitar inyecciones SQL y asegurar un acceso eficiente a la base de datos.
 *
 * Además, este DAO colabora con {@link UsuarioDAO} y {@link AlojamientoDAO}
 * para reconstruir completamente las relaciones del modelo.
 */
public class ViajeDAO implements GenericDAO<Viaje> {
    private final static String SQL_ALL = "SELECT * FROM viaje";
    private final static String SQL_FIND_BY_ID = "SELECT * FROM viaje WHERE idViaje = ?";
    private final static String SQL_FIND_BY_USUARIO = "SELECT * FROM viaje WHERE idUsuario = ?";

    private final static String SQL_INSERT = "INSERT INTO viaje (idUsuario, idAlojamiento, nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen) " + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private final static String SQL_UPDATE = "UPDATE viaje SET idUsuario=?, idAlojamiento=?, nombre=?, destino=?, fechaInicio=?, fechaFin=?, presupuesto=?, notas=?, tipo=?, imagen=? " + "WHERE idViaje=?";
    private final static String SQL_DELETE = "DELETE FROM viaje WHERE idViaje=?";

    private UsuarioDAO usuarioDAO = new UsuarioDAO();
    private AlojamientoDAO alojamientoDAO = new AlojamientoDAO();

    /**
     * Obtiene todos los viajes registrados en la base de datos.
     *
     * @return lista de viajes
     */
    @Override
    public List<Viaje> findAll() {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;
        try (ResultSet rs = ConnectionBD.getConnection().createStatement().executeQuery(SQL_ALL)) {
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                int idUsuario = rs.getInt("idUsuario");
                Usuario usuario = usuarioDAO.findById(idUsuario);
                int idAlojamiento = rs.getInt("idAlojamiento");
                Alojamiento alojamiento = alojamientoDAO.findById(idAlojamiento);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fechaFin").toLocalDate();
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = TipoViaje.valueOf(rs.getString("tipo"));
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viajes;
    }

    /**
     * Busca un viaje por su identificador único.
     *
     * @param id identificador del viaje
     * @return viaje encontrado o null si no existe
     */
    @Override
    public Viaje findById(int id) {
        Viaje viaje = null;
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                int idUsuario = rs.getInt("idUsuario");
                Usuario usuario = usuarioDAO.findById(idUsuario);
                int idAlojamiento = rs.getInt("idAlojamiento");
                Alojamiento alojamiento = alojamientoDAO.findById(idAlojamiento);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fechaFin").toLocalDate();
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = TipoViaje.valueOf(rs.getString("tipo"));
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento,nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viaje;
    }

    /**
     * Busca todos los viajes asociados a un usuario concreto.
     *
     * @param idUsuario identificador del usuario
     * @return lista de viajes del usuario
     */
    public List<Viaje> findByIdUsuario(int idUsuario) {
        List<Viaje> viajes = new ArrayList<>();
        Viaje viaje = null;

        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_FIND_BY_USUARIO)) {
            ps.setInt(1, idUsuario);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int idViaje = rs.getInt("idViaje");
                int idUsuario2 = rs.getInt("idUsuario");
                Usuario usuario = usuarioDAO.findById(idUsuario2);
                int idAlojamiento = rs.getInt("idAlojamiento");
                Alojamiento alojamiento = alojamientoDAO.findById(idAlojamiento);
                String nombre = rs.getString("nombre");
                String destino = rs.getString("destino");
                LocalDate fechaInicio = rs.getDate("fechaInicio").toLocalDate();
                LocalDate fechaFin = rs.getDate("fechaFin").toLocalDate();
                double presupuesto = rs.getDouble("presupuesto");
                String notas = rs.getString("notas");
                TipoViaje tipo = TipoViaje.valueOf(rs.getString("tipo"));
                String imagen = rs.getString("imagen");
                viaje = new Viaje(idViaje, usuario, alojamiento, nombre, destino, fechaInicio, fechaFin, presupuesto, notas, tipo, imagen);
                viajes.add(viaje);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return viajes;
    }

    /**
     * Inserta un nuevo viaje en la base de datos.
     * Tras la inserción, se recupera el ID generado automáticamente.
     *
     * @param v viaje a insertar
     * @return viaje con su ID actualizado
     */
    @Override
    public Viaje add(Viaje v) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            if (v.getUsuario() == null) {
                throw new IllegalArgumentException("El viaje debe tener un usuario asignado antes de insertarlo.");
            }
            ps.setInt(1, v.getUsuario().getIdUsuario());

            if (v.getAlojamiento() != null) {
                ps.setInt(2, v.getAlojamiento().getIdAlojamiento());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setString(3, v.getNombre());
            ps.setString(4, v.getDestino());
            ps.setDate(5, Date.valueOf(v.getFechaInicio()));
            ps.setDate(6, Date.valueOf(v.getFechaFin()));
            ps.setDouble(7, v.getPresupuesto());
            ps.setString(8, v.getNotas());
            ps.setString(9, v.getTipo().name());
            ps.setString(10, v.getImagen());
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                v.setIdViaje(rs.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return v;
    }

    /**
     * Actualiza los datos de un viaje existente.
     *
     * @param v viaje con los datos actualizados
     * @return true si la actualización fue exitosa, false en caso contrario
     */
    @Override
    public boolean update(Viaje v) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_UPDATE)) {
            if (v.getUsuario() == null) {
                throw new IllegalArgumentException("El viaje debe tener un usuario asignado antes de actualizarlo.");
            }
            ps.setInt(1, v.getUsuario().getIdUsuario());

            if (v.getAlojamiento() != null) {
                ps.setInt(2, v.getAlojamiento().getIdAlojamiento());
            } else {
                ps.setNull(2, Types.INTEGER);
            }

            ps.setString(3, v.getNombre());
            ps.setString(4, v.getDestino());
            ps.setDate(5, Date.valueOf(v.getFechaInicio()));
            ps.setDate(6, Date.valueOf(v.getFechaFin()));
            ps.setDouble(7, v.getPresupuesto());
            ps.setString(8, v.getNotas());
            ps.setString(9, v.getTipo().name());
            ps.setString(10, v.getImagen());
            ps.setInt(11, v.getIdViaje());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Elimina un viaje de la base de datos.
     *
     * @param viaje viaje a eliminar
     * @return true si la eliminación fue exitosa, false en caso contrario
     */
    @Override
    public boolean delete(Viaje viaje) {
        try (PreparedStatement ps = ConnectionBD.getConnection().prepareStatement(SQL_DELETE)) {
            ps.setInt(1, viaje.getIdViaje());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}