package com.hms.dao;

import com.hms.model.Appointment;
import java.util.List;

public interface AppointmentDAO {
    void addAppointment(Appointment a) throws Exception;
    Appointment viewAppointment(int id) throws Exception;
    List<Appointment> listAppointments() throws Exception;
}
