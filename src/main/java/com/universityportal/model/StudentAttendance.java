package main.java.com.universityportal.model;

import java.sql.Date;

public class StudentAttendance {

    private int enrollmentId;
    private String status;
    private Date attendanceDate;

    public StudentAttendance(int enrollmentId,String status, Date attendanceDate) {
        this.enrollmentId=enrollmentId;
        this.status = status;
        this.attendanceDate = attendanceDate;
    }

    public int getEnrollmentId(){
        return enrollmentId;
    }
    public String getStatus() { return status; }
    public Date getAttendanceDate() { return attendanceDate; }

}


