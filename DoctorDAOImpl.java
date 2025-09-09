package com.hms.dao.impl;

import com.hms.dao.DoctorDAO;
import com.hms.model.Doctor;
import com.hms.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAOImpl implements DoctorDAO {
    private final Connection conn = Database.getConnection();

    @Override
    public void addDoctor(Doctor d) throws Exception {
        String sql = "INSERT INTO doctors (id, name, specialization) VALUES (?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, d.getId());
            ps.setString(2, d.getName());
            ps.setString(3, d.getSpecialization());
            ps.executeUpdate();
        }
    }

    @Override
    public Doctor viewDoctor(int id) throws Exception {
        String sql = "SELECT id, name, specialization FROM doctors WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3));
            }
            return null;
        }
    }

    @Override
    public boolean checkDoctor(int id) throws Exception {
        String sql = "SELECT 1 FROM doctors WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    @Override
    public List<Doctor> listDoctors() throws Exception {
        List<Doctor> list = new ArrayList<>();
        String sql = "SELECT id, name, specialization FROM doctors";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Doctor(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
        }
        return list;
    }
}
