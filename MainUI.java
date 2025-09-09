package com.hms.ui;

import com.hms.dao.impl.DoctorDAOImpl;
import com.hms.dao.impl.PatientDAOImpl;
import com.hms.dao.impl.AppointmentDAOImpl;
import com.hms.model.Doctor;
import com.hms.model.Patient;
import com.hms.model.Appointment;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.time.LocalDate;
import java.util.List;

public class MainUI extends JFrame {
    private final PatientDAOImpl patientDAO = new PatientDAOImpl();
    private final DoctorDAOImpl doctorDAO = new DoctorDAOImpl();
    private final AppointmentDAOImpl appointmentDAO = new AppointmentDAOImpl();

    public MainUI() {
        setTitle("Hospital Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Background panel
        try {
            Image bg = ImageIO.read(new File("src/main/resources/background.jpg"));
            setContentPane(new BackgroundPane(bg));
        } catch (Exception e) {
            // ignore - will use default background color
        }

        JPanel top = new JPanel(new FlowLayout(FlowLayout.CENTER));
        top.setOpaque(false);
        JLabel heading = new JLabel("Hospital Management System");
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(Color.WHITE);
        top.add(heading);
        add(top, BorderLayout.NORTH);

        JPanel center = new JPanel();
        center.setOpaque(false);
        center.setBorder(new EmptyBorder(20,20,20,20));
        center.setLayout(new GridLayout(3,3,15,15));

        JButton addPatientBtn = styledButton("Add Patient");
        JButton viewPatientBtn = styledButton("View Patient");
        JButton checkPatientBtn = styledButton("Check Patient");
        JButton addDoctorBtn = styledButton("Add Doctor");
        JButton viewDoctorBtn = styledButton("View Doctors");
        JButton checkDoctorBtn = styledButton("Check Doctor");
        JButton addAppointmentBtn = styledButton("Add Appointment");
        JButton viewAppointmentsBtn = styledButton("View Appointments");
        JButton exitBtn = styledButton("Exit");

        center.add(addPatientBtn);
        center.add(viewPatientBtn);
        center.add(checkPatientBtn);
        center.add(addDoctorBtn);
        center.add(viewDoctorBtn);
        center.add(checkDoctorBtn);
        center.add(addAppointmentBtn);
        center.add(viewAppointmentsBtn);
        center.add(exitBtn);

        add(center, BorderLayout.CENTER);

        // Actions
        addPatientBtn.addActionListener(e -> addPatient());
        viewPatientBtn.addActionListener(e -> viewPatient());
        checkPatientBtn.addActionListener(e -> checkPatient());
        addDoctorBtn.addActionListener(e -> addDoctor());
        viewDoctorBtn.addActionListener(e -> viewDoctors());
        checkDoctorBtn.addActionListener(e -> checkDoctor());
        addAppointmentBtn.addActionListener(e -> addAppointment());
        viewAppointmentsBtn.addActionListener(e -> viewAppointments());
        exitBtn.addActionListener(e -> System.exit(0));
    }

    private JButton styledButton(String text) {
        JButton b = new StyledButton(text);
        b.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        b.setFocusPainted(false);
        b.setBorder(BorderFactory.createRoundedBorder(10));
        b.setPreferredSize(new Dimension(160, 50));
        return b;
    }

    private void addPatient() {
        try {
            String sid = JOptionPane.showInputDialog(this, "Patient ID (int):");
            if (sid == null) return;
            int id = Integer.parseInt(sid);
            String name = JOptionPane.showInputDialog(this, "Name:");
            if (name == null) return;
            int age = Integer.parseInt(JOptionPane.showInputDialog(this, "Age:"));
            String gender = JOptionPane.showInputDialog(this, "Gender:");
            Patient p = new Patient(id, name, age, gender);
            patientDAO.addPatient(p);
            JOptionPane.showMessageDialog(this, "Patient added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, successIcon());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void viewPatient() {
        try {
            String sid = JOptionPane.showInputDialog(this, "Enter patient ID:");
            if (sid == null) return;
            int id = Integer.parseInt(sid);
            Patient p = patientDAO.viewPatient(id);
            JOptionPane.showMessageDialog(this, p == null ? "Not found" : p.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void checkPatient() {
        try {
            String sid = JOptionPane.showInputDialog(this, "Enter patient ID to check:");
            if (sid == null) return;
            int id = Integer.parseInt(sid);
            boolean ok = patientDAO.checkPatient(id);
            JOptionPane.showMessageDialog(this, ok ? "Patient exists" : "Patient not found");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void addDoctor() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Doctor ID (int):"));
            String name = JOptionPane.showInputDialog(this, "Name:");
            String spec = JOptionPane.showInputDialog(this, "Specialization:");
            Doctor d = new Doctor(id, name, spec);
            doctorDAO.addDoctor(d);
            JOptionPane.showMessageDialog(this, "Doctor added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, successIcon());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void viewDoctors() {
        try {
            List<Doctor> list = doctorDAO.listDoctors();
            StringBuilder sb = new StringBuilder();
            for (Doctor d : list) sb.append(d).append("\n");
            JOptionPane.showMessageDialog(this, sb.length()==0?"No doctors":sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void checkDoctor() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter doctor ID to check:" ));
            boolean ok = doctorDAO.checkDoctor(id);
            JOptionPane.showMessageDialog(this, ok?"Doctor exists":"Doctor not found");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void addAppointment() {
        try {
            int id = Integer.parseInt(JOptionPane.showInputDialog(this, "Appointment ID (int):"));
            int pid = Integer.parseInt(JOptionPane.showInputDialog(this, "Patient ID:" ));
            int did = Integer.parseInt(JOptionPane.showInputDialog(this, "Doctor ID:" ));
            String dateStr = JOptionPane.showInputDialog(this, "Date (YYYY-MM-DD):" );
            Appointment a = new Appointment(id, pid, did, LocalDate.parse(dateStr));
            appointmentDAO.addAppointment(a);
            JOptionPane.showMessageDialog(this, "Appointment added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE, successIcon());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void viewAppointments() {
        try {
            List<Appointment> list = appointmentDAO.listAppointments();
            StringBuilder sb = new StringBuilder();
            for (Appointment a : list) sb.append(a).append("\n");
            JOptionPane.showMessageDialog(this, sb.length()==0?"No appointments":sb.toString());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    
    private Icon successIcon() {
        return new ImageIcon("src/main/resources/tick.png");
    }

    // Overridden method
        return UIManager.getIcon("OptionPane.informationIcon");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainUI ui = new MainUI();
            ui.setVisible(true);
        });
    }

    // Background panel inner class
    static class BackgroundPane extends JPanel {
        private final Image bg;
        public BackgroundPane(Image bg) {
            this.bg = bg;
            setLayout(new BorderLayout());
        }
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (bg != null) {
                g.drawImage(bg, 0, 0, getWidth(), getHeight(), this);
            }
        }
    }
}



class StyledButton extends JButton {
    public StyledButton(String text) {
        super(text);
        setFocusPainted(false);
        setBackground(new Color(30, 144, 255)); // Dodger blue
        setForeground(Color.WHITE);
        setFont(new Font("Arial", Font.BOLD, 14));
        setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
        super.paintComponent(g);
        g2.dispose();
    }
}

}