package com.example.travelapp.dao;

public class ViajeMuseoDAO {
    private final static String SQL_FIND_ALL = "SELECT * FROM ViajeMuseo";
    private final static String SQL_FIND_BY_IDVIAJE = "SELECT * FROM ViajeMuseo WHERE idViaje=?";
    private final static String SQL_FIND_BY_IDMUSEO = "SELECT * FROM Museo WHERE idMuseo=?";
    private final static String SQL_FIND_BY_FECHAVISITA =  "SELECT * FROM Museo WHERE fechaVisita=?";

    private final static String INSERT ="";
    private final static String UPDATE ="";
    private final static String DELETE ="";
}
