package com.hms.dao;

import com.hms.model.Patient;
import java.util.List;

public interface PatientDAO {
    void addPatient(Patient p) throws Exception;
    Patient viewPatient(int id) throws Exception;
    boolean checkPatient(int id) throws Exception;
    List<Patient> listPatients() throws Exception;
}
