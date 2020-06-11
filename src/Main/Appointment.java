package Main;

import java.sql.Time;

public class Appointment {
	private int appointmentId;
	private String doctorName;
	private String specilizationName;
	private String date;
	private Time time;
	private Time endTime;
	private int appCount;
	private String patientName;
	
	
	public Appointment(){
		
	}
	public Appointment(int appointmentId, String doctorName, String specilizationName, String date, Time time) {
		super();
		this.appointmentId = appointmentId;
		this.doctorName = doctorName;
		this.specilizationName = specilizationName;
		this.date = date;
		this.time = time;
	}
	public int getAppointmentId() {
		return appointmentId;
	}
	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getSpecilizationName() {
		return specilizationName;
	}
	public void setSpecilizationName(String specilizationName) {
		this.specilizationName = specilizationName;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}

	public Time getEndTime() {
		return endTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public int getAppCount() {
		return appCount;
	}
	public void setAppCount(int appCount) {
		this.appCount = appCount;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
}
