package Main;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Appointment {
	private int appointmentId;
	private String doctorName;
	private String specilizationName;
	private String date;
	private String DateTime;
	private Time time;
	private Time endTime;
	private int appCount;
	private String patientName;
	private String patientId;
	private String doctorId;
	private Boolean Passed;
	
	
	public Appointment(){

	}
	public Appointment(int appointmentId, String doctorName, String specilizationName, String date, Time time) throws ParseException {
		super();
		this.appointmentId = appointmentId;
		this.doctorName = doctorName;
		this.specilizationName = specilizationName;
		this.date = date;
		this.time = time;
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		this.DateTime = date + sdf.format(time);
		setPassed();
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
	public String getDateTime() {
		return this.DateTime;
	}
	public void setDateTime(String date, Time time) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		this.DateTime = date + sdf.format(time);
	}
	public Boolean getPassed() {
		return this.Passed;
	}
	public void setPassed() throws ParseException {
		checkTime();
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
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public void checkTime() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-ddHH:mm:ss");
		Date dateObject = new Date();
		Date currentTimeDate = sdf.parse(sdf.format(dateObject));
		Date appointmentTimeDate = sdf.parse(DateTime);
		this.Passed = appointmentTimeDate.before(currentTimeDate);
	}
}
