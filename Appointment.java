package com.hms.model;

import java.time.LocalDate;

public class Appointment {
    private int id;
    private int patientId;
    private int doctorId;
    private LocalDate appointmentDate;

    public Appointment() {}

    public Appointment(int id, int patientId, int doctorId, LocalDate appointmentDate) {
        this.id = id; this.patientId = patientId; this.doctorId = doctorId; this.appointmentDate = appointmentDate;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getPatientId() { return patientId; }
    public void setPatientId(int patientId) { this.patientId = patientId; }

    public int getDoctorId() { return doctorId; }
    public void setDoctorId(int doctorId) { this.doctorId = doctorId; }

    public LocalDate getAppointmentDate() { return appointmentDate; }
    public void setAppointmentDate(LocalDate appointmentDate) { this.appointmentDate = appointmentDate; }

    @Override
    public String toString() {
        return id + ": patient=" + patientId + ", doctor=" + doctorId + ", date=" + appointmentDate;
    }
}
