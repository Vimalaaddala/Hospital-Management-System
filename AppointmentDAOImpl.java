package com.hms.dao.impl;

import com.hms.dao.AppointmentDAO;
import com.hms.model.Appointment;
import com.hms.util.Database;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAOImpl implements AppointmentDAO {
    private final Connection conn = Database.getConnection();

    @Override
    public void addAppointment(Appointment a) throws Exception {
        String sql = "INSERT INTO appointments (id, patient_id, doctor_id, appointment_date) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, a.getId());
            ps.setInt(2, a.getPatientId());
            ps.setInt(3, a.getDoctorId());
            ps.setDate(4, Date.valueOf(a.getAppointmentDate()));
            ps.executeUpdate();
        }
    }

    @Override
    public Appointment viewAppointment(int id) throws Exception {
        String sql = "SELECT id, patient_id, doctor_id, appointment_date FROM appointments WHERE id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Appointment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate());
            }
            return null;
        }
    }

    @Override
    public List<Appointment> listAppointments() throws Exception {
        List<Appointment> list = new ArrayList<>();
        String sql = "SELECT id, patient_id, doctor_id, appointment_date FROM appointments";
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                list.add(new Appointment(rs.getInt(1), rs.getInt(2), rs.getInt(3), rs.getDate(4).toLocalDate()));
            }
        }
        return list;
    }
}
