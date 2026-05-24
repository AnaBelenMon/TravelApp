package com.example.travelapp.dao;

import com.example.travelapp.dataAccess.ConnectionBD;
import com.example.travelapp.model.Emocion;
import com.example.travelapp.model.ViajeMuseo;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * DAO de la entidad ViajeMuseo.
 *
 * Esta clase gestiona la relación entre Viaje y Museo,
 * representando las visitas realizadas a museos dentro de un viaje.
 *
 * Permite realizar operaciones CRUD y consultas específicas
 * sobre la tabla intermedia viajeMuseo.
 *
 * Clave primaria compuesta: (idViaje, idMuseo)
 */
public class ViajeMuseoDAO {

    private final static String SQL_ALL =
            "SELECT * FROM viajeMuseo";

    private final static String SQL_FIND_BY_IDVIAJE =
            "SELECT * FROM viajeMuseo WHERE idViaje = ?";

    private final static String SQL_FIND_BY_IDMUSEO =
            "SELECT * FROM viajeMuseo WHERE idMuseo = ?";

    private final static String SQL_FIND_BY_FECHAVISITA =
            "SELECT * FROM viajeMuseo WHERE fechaVisita = ?";

    private final static String SQL_INSERT =
            "INSERT INTO viajeMuseo (idViaje, idMuseo, fechaVisita, emocion) VALUES (?, ?, ?, ?)";

    private final static String SQL_UPDATE =
            "UPDATE viajeMuseo SET fechaVisita=?, emocion=? WHERE idViaje=? AND idMuseo=?";

    private final static String SQL_DELETE =
            "DELETE FROM viajeMuseo WHERE idViaje=? AND idMuseo=?";


// ---------------------------------------------------------
// MAPEO
// ---------------------------------------------------------
/**
 * Convierte un ResultSet en un objeto ViajeMuseo.
 *
 * @param rs el ResultSet obtenido de la consulta SQL
 * @return un objeto ViajeMuseo con los datos del ResultSet
 */
}