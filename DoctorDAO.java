package com.hms.dao;

import com.hms.model.Doctor;
import java.util.List;

public interface DoctorDAO {
    void addDoctor(Doctor d) throws Exception;
    Doctor viewDoctor(int id) throws Exception;
    boolean checkDoctor(int id) throws Exception;
    List<Doctor> listDoctors() throws Exception;
}
