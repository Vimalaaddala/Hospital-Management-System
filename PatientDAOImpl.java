package com.hms.dao.impl;

import com.hms.dao.PatientDAO;
import com.hms.model.Patient;
import com.hms.util.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAOImpl implements PatientDAO {
    private final Connection conn = Database.getConnection();

    @Override
    public void addPatient(Patient p) throws Exception {
        String sql = "INSERT INTO patients (id, name, age, gender) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, p.getId());
            ps.setString(2, p.getName());
            ps.setInt(3, p.getAge());
            ps.setString(4, p.getGender());
            ps.executeUpdate();
        }
    }

    @Override
    public Patient viewPatient(int id) throws Exception {
        String sql = "SELECT id, name, age, gender FROM patients WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Patient(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4));
            }
            return null;
        }
    }

    @Override
    public boolean checkPatient(int id) throws Exception {
        String sql = "SELECT 1 FROM patients WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        }
    }

    @Override
    public List<Patient> listPatients() throws Exception {
        List<Patient> list = new ArrayList<>();
        String sql = "SELECT id, name, age, gender FROM patients";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Patient(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4)));
            }
        }
        return list;
    }
}
