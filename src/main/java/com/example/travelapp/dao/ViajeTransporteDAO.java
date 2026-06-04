package com.example.travelapp.dao;

import com.example.travelapp.model.ViajeTransporte;

import java.sql.SQLException;
import java.util.List;

public class ViajeTransporteDAO implements GenericDAO<ViajeTransporte> {
    @Override
    public List<ViajeTransporte> findAll() {
        return List.of();
    }

    @Override
    public ViajeTransporte findById(int id) {
        return null;
    }

    @Override
    public ViajeTransporte add(ViajeTransporte entity) {

        return entity;
    }

    @Override
    public boolean update(ViajeTransporte entity) {

        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
