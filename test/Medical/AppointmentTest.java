package Medical;

import org.junit.Test;

import java.sql.Time;
import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppointmentTest {
    @Test
    public void testGetTime() throws ParseException {

        Appointment app1 = new Appointment(1, "John", "Dermatology", "2020-08-08", Time.valueOf("12:00:00"));
        assertEquals(Time.valueOf("12:00:00"), app1.getTime());
        System.out.println(app1.getTime());

        Appointment app2 = new Appointment(2, "John", "Dermatology", "2020-08-08", Time.valueOf("13:20:00"));
        assertEquals(Time.valueOf("13:20:00"), app2.getTime());
        System.out.println(app2.getTime());

        Appointment app3 = new Appointment(3, "John", "Dermatology", "2020-08-08", Time.valueOf("12:50:00"));
        assertEquals(Time.valueOf("12:50:00"), app3.getTime());
        System.out.println(app3.getTime());

    }

    @Test
    public void testGetDoctorName() throws ParseException {

        Appointment app5 = new Appointment(5, "Testing", "Oncology", "2020-08-08", Time.valueOf("12:00:00"));
        assertEquals(Time.valueOf("12:00:00"), app5.getTime());
        System.out.println(app5.getDoctorName());

        Appointment app6 = new Appointment(6, "Tester", "Oncology", "2020-08-08", Time.valueOf("13:20:00"));
        assertEquals(Time.valueOf("13:20:00"), app6.getTime());
        System.out.println(app6.getDoctorName());

        Appointment app7 = new Appointment(7, "Odin", "Oncology", "2020-08-08", Time.valueOf("12:50:00"));
        assertEquals(Time.valueOf("12:50:00"), app7.getTime());
        System.out.println(app7.getDoctorName());

    }

    @Test
    public void testCheckTime() throws ParseException {

        Appointment app2 = new Appointment(2, "John", "Dermatology", "2020-04-06", Time.valueOf("12:00:00"));
        Appointment app3 = new Appointment(3, "John", "Dermatology", "2030-08-08", Time.valueOf("17:00:00"));

        Appointment app4 = new Appointment(4, "Jacky", "Oncology", "2019-04-06", Time.valueOf("13:00:00"));
        Appointment app5 = new Appointment(5, "Jones", "Oncology", "2022-03-04", Time.valueOf("14:00:00"));

        Appointment app6 = new Appointment(6, "Dino", "Radiology", "2018-04-06", Time.valueOf("15:00:00"));
        Appointment app7 = new Appointment(7, "Rick", "Radiology", "2027-02-02", Time.valueOf("16:00:00"));

        assertEquals(true, app2.getPassed(), "This test has passed!");
        assertEquals(false, app3.getPassed(), "This test has passed!");
        assertEquals(true, app4.getPassed(), "This test has passed!");
        assertEquals(false, app5.getPassed(), "This test has passed!");
        assertEquals(true, app6.getPassed(), "This test has passed!");
        assertEquals(false, app7.getPassed(), "This test has passed!");

    }

}

